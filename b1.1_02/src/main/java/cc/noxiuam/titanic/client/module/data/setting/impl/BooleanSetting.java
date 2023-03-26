package cc.noxiuam.titanic.client.module.data.setting.impl;

import cc.noxiuam.titanic.client.module.data.setting.AbstractSetting;
import cc.noxiuam.titanic.client.ui.component.type.module.ModuleSettingsComponent;
import cc.noxiuam.titanic.client.ui.component.type.setting.AbstractSettingComponent;
import cc.noxiuam.titanic.client.ui.component.type.setting.impl.BooleanComponent;

public class BooleanSetting extends AbstractSetting<Boolean> {

    public BooleanSetting(String id, String name, Boolean defaultValue) {
        super(id, name, defaultValue);
    }

    @Override
    public AbstractSettingComponent<Boolean> getComponent(ModuleSettingsComponent list) {
        return new BooleanComponent(this, list);
    }

}
