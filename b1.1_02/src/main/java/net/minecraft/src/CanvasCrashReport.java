package net.minecraft.src;

import java.awt.*;

class CanvasCrashReport extends Canvas {

    public CanvasCrashReport(int i) {
        setPreferredSize(new Dimension(i, i));
        setMinimumSize(new Dimension(i, i));
    }
}
