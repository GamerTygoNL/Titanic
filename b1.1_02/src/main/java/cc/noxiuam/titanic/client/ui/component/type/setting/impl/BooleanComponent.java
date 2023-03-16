package cc.noxiuam.titanic.client.ui.component.type.setting.impl;

import cc.noxiuam.titanic.client.module.data.setting.impl.BooleanSetting;
import cc.noxiuam.titanic.client.ui.component.type.button.TogglableTextButton;
import cc.noxiuam.titanic.client.ui.component.type.module.ModuleSettingsComponent;
import cc.noxiuam.titanic.client.ui.component.type.setting.AbstractSettingComponent;
import cc.noxiuam.titanic.client.util.sound.SoundUtil;

public class BooleanComponent extends AbstractSettingComponent<Boolean> {

    private final TogglableTextButton button = new TogglableTextButton("x");

    public BooleanComponent(BooleanSetting setting, ModuleSettingsComponent list) {
        super(setting, list);
        button.setShowText(setting.value());
    }

    @Override
    public void draw(float x, float y) {

        this.mc.fontRenderer.drawStringWithShadow(
                this.setting.name(),
                (int) (this.x + 2.0F),
                (int) (this.y),
                -1
        );

        this.button.size(12, 12);
        this.button.position(this.x + this.width - 10, this.y);
        this.button.draw(x, y);
    }

    @Override
    public void mouseClicked(float x, float y) {
        if (button.mouseInside(x, y)) {
            setting.value(!setting.value());
            button.setShowText(setting.value());
            SoundUtil.playClick();
        }
    }

    @Override
    public float getHeight() {
        return 10;
    }

}
