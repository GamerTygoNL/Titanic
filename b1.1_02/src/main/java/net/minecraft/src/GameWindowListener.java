package net.minecraft.src;

import net.minecraft.client.Minecraft;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class GameWindowListener extends WindowAdapter {

    final Minecraft mc; /* synthetic field */
    final Thread mcThread; /* synthetic field */

    public GameWindowListener(Minecraft minecraft, Thread thread) {
        mc = minecraft;
        mcThread = thread;
    }

    public void windowClosing(WindowEvent windowevent) {
        mc.shutdown();
        try {
            mcThread.join();
        } catch (InterruptedException interruptedexception) {
            interruptedexception.printStackTrace();
        }
        System.exit(0);
    }
}
