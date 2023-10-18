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

package org.mangorage.mangobot.modules.customvc.commands;

import net.dv8tion.jda.api.entities.Message;
import org.jetbrains.annotations.NotNull;
import org.mangorage.mangobot.core.Bot;
import org.mangorage.mangobot.core.config.BotPermissions;
import org.mangorage.mangobot.modules.customvc.CustomVC;
import org.mangorage.mangobotapi.core.commands.Arguments;
import org.mangorage.mangobotapi.core.commands.CommandResult;
import org.mangorage.mangobotapi.core.commands.IBasicCommand;

public class CustomVCCommand implements IBasicCommand {

    @NotNull
    @Override
    public CommandResult execute(Message message, Arguments args) {
        if (!message.isFromGuild()) return CommandResult.GUILD_ONLY;
        var guild = message.getGuild();
        var member = message.getMember();

        if (member == null) return CommandResult.FAIL;
        if (!BotPermissions.CUSTOM_VC_ADMIN.hasPermission(member)) return CommandResult.NO_PERMISSION;

        String subcmd = args.get(0);
        String channelId = args.get(1);

        if (subcmd.equals("configure")) {
            if (channelId == null) return CommandResult.FAIL;

            CustomVC.configure(guild, channelId);
            Bot.DEFAULT_SETTINGS.apply(message.reply("Configured CustomVC for this guild!")).queue();

            return CommandResult.PASS;
        }


        return CommandResult.PASS;
    }


    @Override
    public String commandId() {
        return "customvc";
    }
}