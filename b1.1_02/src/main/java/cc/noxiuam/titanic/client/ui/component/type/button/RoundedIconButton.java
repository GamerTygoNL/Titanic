package cc.noxiuam.titanic.client.ui.component.type.button;

import cc.noxiuam.titanic.client.ui.component.AbstractComponent;
import cc.noxiuam.titanic.client.ui.fade.impl.ColorFade;
import cc.noxiuam.titanic.client.ui.util.RenderUtil;
import lombok.AllArgsConstructor;
import org.lwjgl.opengl.GL11;

@AllArgsConstructor
public class RoundedIconButton extends AbstractComponent {

    private final ColorFade outlineColor = new ColorFade(0x00000000, 0xCCC2C2C2);
    private final ColorFade backgroundColor = new ColorFade(0x80000000, 0xBF000000);

    private String icon;
    private boolean showBackground;
    private float iconWidth;
    private float iconHeight;
    private float xOffset;
    private float yOffset;

    @Override
    public void draw(float x, float y) {

        if (this.showBackground) {
            RenderUtil.drawRoundedRect(
                    this.x,
                    this.y,
                    this.x + this.width,
                    this.y + this.height,
                    5,
                    this.backgroundColor.getColor(mouseInside(x, y)).getRGB()
            );

            RenderUtil.drawRoundedOutline(
                    this.x,
                    this.y,
                    this.x + this.width,
                    this.y + this.height,
                    5.0F,
                    3.0F,
                    this.outlineColor.getColor(mouseInside(x, y)).getRGB()
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
