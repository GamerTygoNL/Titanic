package cc.noxiuam.titanic.client.ui.component.type.button;

import cc.noxiuam.titanic.client.ui.fade.impl.ColorFade;
import cc.noxiuam.titanic.client.ui.util.RenderUtil;
import org.lwjgl.opengl.GL11;

public class LockButton extends RoundedIconButton {

    private final ColorFade lockedColor = new ColorFade(0x00000000, 0x50FC0303);

    public boolean locked = false;

    public LockButton() {
        super("/titanic/lock.png", true, 11, 11, 4.5F, 4.5F);
    }

    @Override
    public void draw(float x, float y) {
        if (this.showBackground) {
            RenderUtil.drawRoundedRect(
                    this.x,
                    this.y,
                    this.x + this.width,
                    this.y + this.height,
                    5,
                    this.backgroundColor.getColor(this.mouseInside(x, y)).getRGB()
            );
            RenderUtil.drawRoundedRect(
                    this.x,
                    this.y,
                    this.x + this.width,
                    this.y + this.height,
                    5,
                    this.lockedColor.getColor(this.locked).getRGB()
            );
        }

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderUtil.renderIcon(
                this.icon,
                this.x + this.xOffset,
                this.y + this.yOffset,
                this.iconWidth,
                this.iconHeight
        );
    }

}
