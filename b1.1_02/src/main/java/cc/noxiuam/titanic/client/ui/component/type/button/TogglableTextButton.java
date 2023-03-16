package cc.noxiuam.titanic.client.ui.component.type.button;

import cc.noxiuam.titanic.client.ui.component.AbstractComponent;
import cc.noxiuam.titanic.client.ui.fade.impl.ColorFade;
import cc.noxiuam.titanic.client.ui.util.FontUtil;
import cc.noxiuam.titanic.client.ui.util.RenderUtil;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class TogglableTextButton extends AbstractComponent {

    private final String text;

    @Setter private boolean showText;

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

        if (showText) {
            FontUtil.drawCenteredString(this.text, (int) (this.x + this.width / 2F + 0.5F), (int) (this.y + this.height / 2F - 4F), -1);
        }

    }

}
