package com.example.windowsos;

import com.example.windowsos.adapter.CommandPromptAdapter;
import com.example.windowsos.adapter.LegacyCommandPrompt;
import com.example.windowsos.bridge.EventLogChannel;
import com.example.windowsos.bridge.SecurityNotice;
import com.example.windowsos.bridge.SystemNotice;
import com.example.windowsos.bridge.ToastChannel;
import com.example.windowsos.bridge.UpdateNotice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WindowsOsPatternTest {
    @Test
    void bridgeSendsSameNoticeThroughDifferentChannels() {
        SystemNotice toastNotice = new UpdateNotice(new ToastChannel(), "UPDATE-2026");
        SystemNotice logNotice = new UpdateNotice(new EventLogChannel(), "UPDATE-2026");

        assertEquals(
                "Toast notification displayed Windows update notice with code UPDATE-2026",
                toastNotice.send()
        );
        assertEquals(
                "Event log saved Windows update notice with code UPDATE-2026",
                logNotice.send()
        );
    }

    @Test
    void adapterAllowsOldCommandPromptToWorkAsOutputChannel() {
        LegacyCommandPrompt oldPrompt = new LegacyCommandPrompt();
        SystemNotice notice = new SecurityNotice(new CommandPromptAdapter(oldPrompt), "CMD-1-L");

        assertEquals(
                "Old command prompt displayed Windows security notice with code CMD-1-L",
                notice.send()
        );
    }
}
