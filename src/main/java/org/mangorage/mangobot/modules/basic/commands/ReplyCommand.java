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

package org.mangorage.mangobot.modules.basic.commands;

import net.dv8tion.jda.api.entities.Message;
import org.mangorage.mangobotapi.core.commands.AbstractCommand;
import org.mangorage.mangobotapi.core.commands.Arguments;
import org.mangorage.mangobotapi.core.commands.CommandResult;

import static org.mangorage.mangobot.core.Bot.DEFAULT_SETTINGS;

public class ReplyCommand extends AbstractCommand {
    private final String MESSAGE_RESPONSE;
    private final boolean supress;
    private boolean notifications;

    public ReplyCommand(String message, boolean supress) {
        this.MESSAGE_RESPONSE = message;
        this.supress = supress;
    }

    public ReplyCommand(String message) {
        this(message, true);
    }

    public ReplyCommand notifications(boolean value) {
        this.notifications = !value;
        return this;
    }

    public String getMessage() {
        return MESSAGE_RESPONSE;
    }

    @Override
    public CommandResult execute(Message message, Arguments args) {
        DEFAULT_SETTINGS.apply(message.getChannel().sendMessage(MESSAGE_RESPONSE)).queue();
        return CommandResult.PASS;
    }

    /**
     * @return
     */
    @Override
    public boolean isGuildOnly() {
        return false;
    }
}