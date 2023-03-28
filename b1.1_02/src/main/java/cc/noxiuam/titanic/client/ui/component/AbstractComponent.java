package cc.noxiuam.titanic.client.ui.component;

import cc.noxiuam.titanic.Ref;
import lombok.Getter;
import net.minecraft.client.Minecraft;

@Getter
public abstract class AbstractComponent {

    protected final Minecraft mc = Ref.getMinecraft();

    protected float x;
    protected float y;
    protected float width;
    protected float height;

    /**
     * Draws the component itself, very important!!!
     *
     * @param x GuiScreen's X position.
     * @param y GuiScreen's Y position.
     */
    public abstract void draw(float x, float y);

    /**
     * Sets the width and height of the component.
     *
     * @param width The new width.
     * @param height The new height.
     */
    public void size(float width, float height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Sets the x and y coordinates of the component.
     *
     * @param newX The new X.
     * @param newY The new Y.
     */
    public void position(float newX, float newY) {
        x = newX;
        y = newY;
    }

    /**
     * Called when the user's mouse is clicked while also inside the component
     *
     * @param x The mouse's X position.
     * @param y The mouse's Y position.
     */
    public void mouseClicked(float x, float y) {}

    /**
     * Called when the user's keyboard is used.
     *
     * @param character - Pressed key's char
     * @param key - Pressed key's int
     */
    public void keyTyped(char character, int key) {}

    /**
     * Checks to see if the user's mouse is inside the component.
     *
     * @param mouseX The mouse's current X position.
     * @param mouseY The mouse's current Y position.
     */
    public boolean mouseInside(float mouseX, float mouseY) {
        return mouseX > this.x
                && mouseX < this.x + this.width
                && mouseY > this.y
                && mouseY < this.y + this.height;
    }

    public void handleUpdate() { }

}
