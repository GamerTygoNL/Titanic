package cc.noxiuam.titanic.client.ui.component.type.setting.impl.keybind;

import cc.noxiuam.titanic.client.module.data.setting.impl.KeybindSetting;
import cc.noxiuam.titanic.client.ui.component.AbstractComponent;
import cc.noxiuam.titanic.client.ui.fade.impl.ColorFade;
import cc.noxiuam.titanic.client.ui.util.FontUtil;
import cc.noxiuam.titanic.client.ui.util.RenderUtil;
import cc.noxiuam.titanic.client.util.CaseUtils;
import cc.noxiuam.titanic.client.util.sound.SoundUtil;
import org.lwjgl.input.Keyboard;

public class KeybindButton extends AbstractComponent {

    private final ColorFade outlineColor = new ColorFade(0x00000000, 0xCCC2C2C2);
    private final ColorFade backgroundColor = new ColorFade(0x80000000, 0xBF000000);

    private boolean listening;

    private String text;

    private final KeybindSetting setting;

    public KeybindButton(KeybindSetting setting) {
        this.setting = setting;
        this.text = CaseUtils.capitalizeFirst(Keyboard.getKeyName(setting.value()));
    }

    @Override
    public void draw(float x, float y) {
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

        FontUtil.drawCenteredString(this.text, (int) (this.x + this.width / 2F), (int) (this.y + this.height / 2F - 3), -1);
    }

    @Override
    public void keyTyped(char character, int key) {
        if (this.listening) {
            this.setting.value(key);
            this.listening = false;
            this.text = CaseUtils.capitalizeFirst(Keyboard.getKeyName(this.setting.value()));
        }
    }

    @Override
    public void mouseClicked(float x, float y) {
        if (mouseInside(x, y) && !this.listening) {
            this.listening = true;
            this.text = "?";
            SoundUtil.playClick();
        }
    }

}
