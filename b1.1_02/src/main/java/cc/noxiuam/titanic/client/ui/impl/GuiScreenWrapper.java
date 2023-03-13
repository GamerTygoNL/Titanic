package cc.noxiuam.titanic.client.ui.impl;

import net.minecraft.src.GuiScreen;

/**
 * Just something to make sense of the local variables between versions.
 */
public class GuiScreenWrapper extends GuiScreen {

    @Override
    public void drawScreen(int i, int j, float f) {
        this.drawScreen(i, j);
    }

    @Override
    protected void mouseClicked(int i, int j, int k) {
        this.onMouseClick(i, j, k);
    }

    /**
     * Handles drawing.
     *
     * @param x Mouse X
     * @param y Mouse Y
     */
    public void drawScreen(int x, int y) { }

    /**
     * Handles mouse clicking.
     *
     * @param x Mouse X
     * @param y Mouse Y
     * @param button Clicked button int
     */
    public void onMouseClick(int x, int y, int button) { }

}
