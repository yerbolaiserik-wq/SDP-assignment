package com.example.windowsos.bridge;

public class EventLogChannel implements OutputChannel {
    @Override
    public String display(String noticeType, String code) {
        return "Event log saved " + noticeType + " with code " + code;
    }
}
