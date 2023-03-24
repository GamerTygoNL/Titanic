package cc.noxiuam.titanic.client.module.data.setting.impl;

import cc.noxiuam.titanic.client.module.data.setting.AbstractSetting;
import cc.noxiuam.titanic.client.ui.component.type.module.ModuleSettingsComponent;
import cc.noxiuam.titanic.client.ui.component.type.setting.AbstractSettingComponent;
import cc.noxiuam.titanic.client.ui.component.type.setting.impl.string.StringComponent;

public class StringSetting extends AbstractSetting<String> {

    public StringSetting(String id, String name, String defaultValue) {
        super(id, name, defaultValue);
    }

    @Override
    public AbstractSettingComponent<String> getComponent(ModuleSettingsComponent list) {
        return new StringComponent(this, list);
    }

}
