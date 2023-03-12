package cc.noxiuam.titanic.client.ui.component.type.button;

import cc.noxiuam.titanic.client.ui.component.AbstractComponent;
import cc.noxiuam.titanic.client.ui.fade.impl.ColorFade;
import cc.noxiuam.titanic.client.ui.util.FontUtil;
import cc.noxiuam.titanic.client.ui.util.RenderUtil;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RoundedTextButton extends AbstractComponent {

    private String text;

    private final ColorFade outlineColor = new ColorFade(0x00000000, 0xCCC2C2C2);
    private final ColorFade backgroundColor = new ColorFade(0x80000000, 0xBF000000);

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

        RenderUtil.drawRoundedOutline(
                this.x,
                this.y,
                this.x + this.width,
                this.y + this.height,
                5.0F,
                3.0F,
                outlineColor.getColor(mouseInside(x, y)).getRGB()
        );

        FontUtil.drawCenteredString(this.text, (int) (this.x + this.width / 2F), (int) (this.y + this.height / 2F - 3), -1);
    }

}
