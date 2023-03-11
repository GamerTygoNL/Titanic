package cc.noxiuam.titanic.client.ui.component.type.button;

import cc.noxiuam.titanic.client.ui.component.AbstractComponent;
import cc.noxiuam.titanic.client.ui.fade.impl.ColorFade;
import cc.noxiuam.titanic.client.ui.util.RenderUtil;
import lombok.AllArgsConstructor;
import org.lwjgl.opengl.GL11;

import java.awt.*;

@AllArgsConstructor
public class RoundedIconButton extends AbstractComponent {

    private final ColorFade backgroundColor = new ColorFade(
            new Color(0, 0, 0, 150).getRGB(),
            new Color(42, 42, 42, 150).getRGB()
    );

    private String icon;

    @Override
    public void draw(float x, float y) {

        RenderUtil.drawRoundedRect(
                this.x,
                this.y,
                this.x + this.width,
                this.y + this.height,
                5,
                backgroundColor.getColor(mouseInside(x, y)).getRGB()
        );

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderUtil.renderIcon(
                icon,
                7,
                this.x + 6,
                this.y + 6
        );

    }

}
