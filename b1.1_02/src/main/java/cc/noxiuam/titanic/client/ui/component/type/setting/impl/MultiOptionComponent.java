package cc.noxiuam.titanic.client.ui.component.type.setting.impl;

import cc.noxiuam.titanic.client.module.data.setting.impl.MultiOptionSetting;
import cc.noxiuam.titanic.client.ui.component.type.button.RoundedTextButton;
import cc.noxiuam.titanic.client.ui.component.type.module.ModuleSettingsComponent;
import cc.noxiuam.titanic.client.ui.component.type.setting.AbstractSettingComponent;
import cc.noxiuam.titanic.client.ui.util.FontUtil;
import cc.noxiuam.titanic.client.util.sound.SoundUtil;

public class MultiOptionComponent extends AbstractSettingComponent<String> {

    private final MultiOptionSetting setting;

    private final RoundedTextButton leftButton = new RoundedTextButton("<");
    private final RoundedTextButton rightButton = new RoundedTextButton(">");

    public MultiOptionComponent(MultiOptionSetting setting, ModuleSettingsComponent list) {
        super(setting, list);
        this.setting = setting;
    }

    @Override
    public void draw(float x, float y) {
        this.mc.fontRenderer.drawStringWithShadow(
                this.setting.name(),
                (int) (this.x + 2.0F),
                (int) (this.y),
                -1
        );

        this.leftButton.size(12, 12);
        this.leftButton.position(this.x + this.width - 80, this.y);
        this.leftButton.draw(x, y);

        FontUtil.drawCenteredString(
                this.setting.value(),
                (int) (this.x + this.width - 38),
                (int) (this.y + 2),
                -1
        );

        this.rightButton.size(12, 12);
        this.rightButton.position(this.x + this.width - 10, this.y);
        this.rightButton.draw(x, y);
    }

    @Override
    public void mouseClicked(float x, float y) {
        int length = this.setting.getAcceptedValues().length;

        if (this.leftButton.mouseInside(x, y)) {
            SoundUtil.playClick();
            for (int i = 0; i < length; ++i) {
                if (!this.setting.getAcceptedValues()[i].toLowerCase().equalsIgnoreCase(this.setting.value())) {
                    continue;
                }

                if (i - 1 < 0) {
                    this.setting.value(((String[]) this.setting.getAcceptedValues())[length - 1]);
                    break;
                }

                this.setting.value(((String[]) this.setting.getAcceptedValues())[i - 1]);
            }
        }

        if (this.rightButton.mouseInside(x, y)) {
            SoundUtil.playClick();
            for (int i = 0; i < length; ++i) {
                if (!this.setting.getAcceptedValues()[i].toLowerCase().equalsIgnoreCase(this.setting.value())) {
                    continue;
                }

                if (i + 1 >= length) {
                    this.setting.value(this.setting.getAcceptedValues()[0]);
                    break;
                }

                this.setting.value(this.setting.getAcceptedValues()[i + 1]);
                break;
            }
        }
    }

    @Override
    public float getHeight() {
        return 10;
    }

}
