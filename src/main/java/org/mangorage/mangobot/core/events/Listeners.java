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

package org.mangorage.mangobot.core.events;

import org.mangorage.mangobotapi.core.eventbus.annotations.SubscribeEvent;
import org.mangorage.mangobotapi.core.events.discord.DButtonInteractionEvent;

public class Listeners {

    @SubscribeEvent
    public static void onButtonInteraction(DButtonInteractionEvent event) {
        var dEvent = event.get();
        var interaction = dEvent.getInteraction();
        if (interaction.getComponentId().startsWith("mangobot:trash:")) {
            String userId = interaction.getComponentId().split(":")[2];
            var userClick = dEvent.getUser().getId();
            if (userClick.equals(userId)) {
                dEvent.getInteraction().getMessage().delete().queue();
                dEvent.getInteraction().reply("Deleting...").setEphemeral(true).queue();
            } else
                dEvent.getInteraction().reply("No Permission!").setEphemeral(true).queue();
        }
    }
}