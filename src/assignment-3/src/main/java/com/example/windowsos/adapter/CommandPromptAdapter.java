package com.example.windowsos.adapter;

import com.example.windowsos.bridge.OutputChannel;

public class CommandPromptAdapter implements OutputChannel {
    private final LegacyCommandPrompt legacyCommandPrompt;

    public CommandPromptAdapter(LegacyCommandPrompt legacyCommandPrompt) {
        this.legacyCommandPrompt = legacyCommandPrompt;
    }

    @Override
    public String display(String noticeType, String code) {
        return legacyCommandPrompt.showLine(noticeType, code);
    }
}
