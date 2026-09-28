package com.example.windowsos.bridge;

public class ToastChannel implements OutputChannel {
    @Override
    public String display(String noticeType, String code) {
        return "Toast notification displayed " + noticeType + " with code " + code;
    }
}
