package com.example.windowsos.bridge;

public class UpdateNotice extends SystemNotice {
    public UpdateNotice(OutputChannel outputChannel, String code) {
        super(outputChannel, code);
    }

    @Override
    public String send() {
        return outputChannel.display("Windows update notice", code);
    }
}
