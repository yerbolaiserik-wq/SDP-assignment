package com.example.windowsos.bridge;

public abstract class SystemNotice {
    protected final OutputChannel outputChannel;
    protected final String code;

    protected SystemNotice(OutputChannel outputChannel, String code) {
        this.outputChannel = outputChannel;
        this.code = code;
    }

    public abstract String send();
}
