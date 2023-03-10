package net.minecraft.src;

import net.minecraft.client.MinecraftApplet;

import java.awt.*;

public class CanvasMinecraftApplet extends Canvas {

    final MinecraftApplet mcApplet; /* synthetic field */

    public CanvasMinecraftApplet(MinecraftApplet minecraftapplet) {
        mcApplet = minecraftapplet;
    }

    public synchronized void addNotify() {
        super.addNotify();
        mcApplet.func_6233_a();
    }

    public synchronized void removeNotify() {
        mcApplet.shutdown();
        super.removeNotify();
    }
}
