package com.example.windowsos.bridge;

public class SecurityNotice extends SystemNotice {
    public SecurityNotice(OutputChannel outputChannel, String code) {
        super(outputChannel, code);
    }

    @Override
    public String send() {
        return outputChannel.display("Windows security notice", code);
    }
}
