package net.minecraft.src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

class CanvasMajongLogo extends Canvas {

    private BufferedImage logo;

    public CanvasMajongLogo() {
        try {
            logo = ImageIO.read((PanelCrashReport.class).getResource("/gui/logo.png"));
        } catch (IOException ioexception) {
        }
        byte byte0 = 100;
        setPreferredSize(new Dimension(byte0, byte0));
        setMinimumSize(new Dimension(byte0, byte0));
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(logo, getWidth() / 2 - logo.getWidth() / 2, 32, null);
    }
}
