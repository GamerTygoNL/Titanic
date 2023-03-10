package net.minecraft.src;

import net.minecraft.client.Minecraft;
import net.minecraft.client.MinecraftApplet;

import java.awt.*;

public final class MinecraftImpl extends Minecraft {

    final Frame mainFrame; /* synthetic field */

    public MinecraftImpl(Component component, Canvas canvas, MinecraftApplet minecraftapplet, int i, int j, boolean flag, Frame frame) {
        super(component, canvas, minecraftapplet, i, j, flag);
        mainFrame = frame;
    }

    public void displayUnexpectedThrowable(UnexpectedThrowable unexpectedthrowable) {
        mainFrame.removeAll();
        mainFrame.add(new PanelCrashReport(unexpectedthrowable), "Center");
        mainFrame.validate();
    }

    @Override
    public Minecraft bridge$getMinecraft() {
        return gameSettings.mc;
    }

}
