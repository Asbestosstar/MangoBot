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

package org.mangorage.mangobot;

import com.mattmalec.pterodactyl4j.PteroBuilder;
import com.mattmalec.pterodactyl4j.client.entities.PteroClient;
import org.mangorage.basicutils.LogHelper;
import org.mangorage.mangobot.core.util.BotSettings;

public class ServerRestart {

    public static void main(String[] args) {
        LogHelper.info("Restarting Discord Bot Server...");
        PteroClient client = PteroBuilder.createClient("https://panel.sodiumhosting.com/", BotSettings.SERVER_TOKEN.get());

        var server = client.retrieveServerByIdentifier("f32263f3").execute();
        if (server != null) {
            if (server.isSuspended()) {
                LogHelper.info("Server is suspended, unsuspending...");
                server.start().execute();
            } else {
                server.restart().execute();
                LogHelper.info("Restarted Discord Bot Server.");
            }
        }
    }
}
