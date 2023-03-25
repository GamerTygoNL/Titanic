package cc.noxiuam.titanic.client.ui.component.type.setting.impl.string;

import cc.noxiuam.titanic.Ref;
import cc.noxiuam.titanic.client.module.data.setting.impl.StringSetting;
import cc.noxiuam.titanic.client.ui.component.AbstractComponent;
import cc.noxiuam.titanic.client.ui.fade.impl.ColorFade;
import cc.noxiuam.titanic.client.ui.util.RenderUtil;
import cc.noxiuam.titanic.client.util.sound.SoundUtil;
import org.lwjgl.input.Keyboard;

public class TextBoxComponent extends AbstractComponent {

    private final ColorFade outlineColor = new ColorFade(0x00000000, 0xCCC2C2C2);
    private final ColorFade activeOutlineColor = new ColorFade(0x00000000, 0xCC20de16);
    private final ColorFade backgroundColor = new ColorFade(0x80000000, 0xBF000000);

    public boolean using;

    private String text;
    private final StringSetting setting;

    private int counter = 0;

    public TextBoxComponent(StringSetting setting) {
        this.setting = setting;
        this.text = setting.value();
    }

    @Override
    public void handleUpdate() {
        counter--;
    }

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
                (this.using ? this.activeOutlineColor.getColor(true).getRGB() : this.outlineColor.getColor(mouseInside(x, y)).getRGB())
        );

        Ref.getMinecraft().fontRenderer.drawString(this.text + (this.using ? ((counter / 6) % 2 != 0 ? "" : "_") : ""), (int) (this.x + 5), (int) (this.y + 3), -1);
    }

    @Override
    public void keyTyped(char character, int key) {
        if (this.using && key == Keyboard.KEY_RETURN) {
            this.using = false;
            this.setting.value(this.text);
            SoundUtil.playClick();
            return;
        }

        if (key == Keyboard.KEY_BACK && this.text.length() > 0) {
            this.text = this.text.substring(0, this.text.length() - 1);
            return;
        }

        if (this.using && this.text.length() < this.setting.getMaxLength()) {
            this.text += character;
        }
    }

    @Override
    public void mouseClicked(float x, float y) {
        if (mouseInside(x, y)) {
            SoundUtil.playClick();
            this.using = !this.using;
            if (!this.using) {
                this.setting.value(this.text);
            }
        }
    }

}
