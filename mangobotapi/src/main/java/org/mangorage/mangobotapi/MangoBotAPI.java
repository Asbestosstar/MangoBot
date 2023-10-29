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

package org.mangorage.mangobotapi;


import org.mangorage.mangobotapi.core.util.MessageSettings;

public class MangoBotAPI {

    public final static MessageSettings MESSAGE_SETTINGS = MessageSettings.create().build();
}


/**
public class MangoBotAPI {
    private static final MangoBotAPI INSTANCE = MangoBotAPIBuilder.create();

    public static MangoBotAPI getInstance() {
        return INSTANCE;
    }

    private final IEventBus EVENT_BUS;
    private final String COMMAND_PREFIX;
    private final MessageSettings DEFAULT_MESSAGE_SETTINGS;
    private final Supplier<JDA> JDA_INSTANCE;

    protected MangoBotAPI(MangoBotAPIBuilder builder) {
        this.EVENT_BUS = builder.getEventBus();
        this.COMMAND_PREFIX = builder.getCommandPrefix();
        this.DEFAULT_MESSAGE_SETTINGS = builder.getDefaultMessageSettings();
        this.JDA_INSTANCE = builder.getJDAInstance();
    }

    public IEventBus getEventBus() {
        return EVENT_BUS;
    }

    public String getCommandPrefix() {
        return COMMAND_PREFIX;
    }

    public MessageSettings getDefaultMessageSettings() {
        return DEFAULT_MESSAGE_SETTINGS;
    }

    public JDA getJDA() {
        return JDA_INSTANCE.get();
    }

    public void startup(Consumer<IEventBus> busConsumer) {
        EVENT_BUS.startup();

        EVENT_BUS.addListener(10, StartupEvent.class, event -> {
            switch (event.phase()) {
                case STARTUP -> {
                    CommandRegistry.load(); // Load commands... via @AutoRegister

                    Actions.init();
                }
                case REGISTRATION -> {
                }
                case FINISHED -> {
                    EVENT_BUS.post(new LoadEvent());
                }
            }
        });

        EVENT_BUS.addListener(10, ShutdownEvent.class, event -> {
            switch (event.phase()) {
                case PRE -> {
                }
                case POST -> {
                    EVENT_BUS.post(new SaveEvent());
                }
            }
        });

        busConsumer.accept(EVENT_BUS);
        for (StartupEvent.Phase phase : StartupEvent.Phase.values())
            EVENT_BUS.post(new StartupEvent(phase));
    }

    public void shutdown() {
        for (ShutdownEvent.Phase phase : ShutdownEvent.Phase.values())
            EVENT_BUS.post(new ShutdownEvent(phase));
        EVENT_BUS.shutdown();
    }
}
 **/
