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

package org.mangorage.mangobot.core.commands.util;

import net.dv8tion.jda.api.entities.Message;

import java.util.function.Consumer;

import static org.mangorage.mangobot.core.Bot.DEFAULT_SETTINGS;

public enum CommandResult {
    PASS,
    FAIL((m) -> DEFAULT_SETTINGS.apply(m.reply("An error occured while executing this command")).queue()),
    NO_PERMISSION((m) -> DEFAULT_SETTINGS.apply(m.reply("You dont have permission to use this command!")).queue()),
    UNDER_MAINTENANCE((m) -> DEFAULT_SETTINGS.apply(m.reply("This is currently under maintenance! Please try again later!")).queue());


    private final Consumer<Message> messageConsumer;

    CommandResult(Consumer<Message> messageConsumer) {
        this.messageConsumer = messageConsumer;
    }

    CommandResult() {
        this((m) -> {
        });
    }

    public void accept(Message message) {
        messageConsumer.accept(message);
    }
}