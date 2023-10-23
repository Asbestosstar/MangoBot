/*
 * Copyright (c) 2023. MangoRage
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.mangorage.mangobot.modules.anticrosspost;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import org.mangorage.mangobot.basicutils.TaskScheduler;
import org.mangorage.mangobotapi.core.events.discord.DMessageRecievedEvent;
import org.mangorage.mboteventbus.impl.IEventBus;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AnticrossPost {
    public record Messages(String content, String userId, String originalChannelId, AtomicInteger count) {
    }

    // guildId -> UserId -> Message -> Messages
    private static final ConcurrentHashMap<String, Messages> MESSAGES = new ConcurrentHashMap<>();


    public static void logMessage(Message message) {
        if (!message.isFromGuild()) return;

        String msg = message.getContentRaw().toLowerCase();
        Guild guild = message.getGuild();
        String guildId = guild.getId();

        MESSAGES.computeIfAbsent("%s-%s-%s".formatted(guildId, message.getAuthor().getId(), msg), g -> new Messages(msg, message.getAuthor().getId(), message.getChannel().getId(), new AtomicInteger(1)));

        Messages MSGS = MESSAGES.get("%s-%s-%s".formatted(guildId, message.getAuthor().getId(), msg));

        if (MSGS.originalChannelId().equals(message.getChannel().getId())) return;

        if (MSGS.count.get() >= 3) {
            message.reply("Please dont crosspost...").queue(m -> {
                m.delete().queueAfter(10, TimeUnit.SECONDS);
            });
        } else {
            MSGS.count.incrementAndGet();
        }
    }

    public static void register(IEventBus bus) {
        bus.addListener(DMessageRecievedEvent.class, e -> logMessage(e.get().getMessage()));
        TaskScheduler.getExecutor().schedule(MESSAGES::clear, 10, TimeUnit.MINUTES);
    }
}
