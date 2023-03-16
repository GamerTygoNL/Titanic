package cc.noxiuam.titanic.client.ui.component.type.setting.impl;

import cc.noxiuam.titanic.client.module.data.setting.AbstractSetting;
import cc.noxiuam.titanic.client.ui.component.type.module.ModuleSettingsComponent;
import cc.noxiuam.titanic.client.ui.component.type.setting.AbstractSettingComponent;

public class StringComponent extends AbstractSettingComponent<String> {

    public StringComponent(AbstractSetting<String> setting, ModuleSettingsComponent list) {
        super(setting, list);
    }

    @Override
    public void draw(float x, float y) {

        this.mc.fontRenderer.drawStringWithShadow(
                this.setting.name(),
                (int) (this.x + 2.0F),
                (int) (this.y),
                -1
        );

    }

    @Override
    public float getHeight() {
        return 10;
    }

}
