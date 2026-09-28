package com.example.windowsos;

import com.example.windowsos.adapter.CommandPromptAdapter;
import com.example.windowsos.adapter.LegacyCommandPrompt;
import com.example.windowsos.bridge.EventLogChannel;
import com.example.windowsos.bridge.OutputChannel;
import com.example.windowsos.bridge.SecurityNotice;
import com.example.windowsos.bridge.SystemNotice;
import com.example.windowsos.bridge.ToastChannel;
import com.example.windowsos.bridge.UpdateNotice;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Bridge Pattern ===");

        OutputChannel toast = new ToastChannel();
        SystemNotice updateNotice = new UpdateNotice(toast, "UPDATE-2026");
        System.out.println(updateNotice.send());

        OutputChannel eventLog = new EventLogChannel();
        SystemNotice securityNotice = new SecurityNotice(eventLog, "SECURITY-401");
        System.out.println(securityNotice.send());

        System.out.println();
        System.out.println("=== Adapter Pattern ===");

        LegacyCommandPrompt oldCommandPrompt = new LegacyCommandPrompt();
        OutputChannel commandPrompt = new CommandPromptAdapter(oldCommandPrompt);
        SystemNotice commandNotice = new UpdateNotice(commandPrompt, "CMD-1-L");
        System.out.println(commandNotice.send());
    }
}
