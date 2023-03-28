package cc.noxiuam.titanic.client.module.data.setting.impl;

import cc.noxiuam.titanic.client.module.data.setting.AbstractSetting;
import cc.noxiuam.titanic.client.ui.module.component.ModuleSettingsComponent;
import cc.noxiuam.titanic.client.ui.component.type.setting.AbstractSettingComponent;
import cc.noxiuam.titanic.client.ui.component.type.setting.impl.keybind.KeybindComponent;

public class KeybindSetting extends AbstractSetting<Integer> {

    public KeybindSetting(String id, String name, Integer defaultValue) {
        super(id, name, defaultValue);
    }

    @Override
    public AbstractSettingComponent<Integer> getComponent(ModuleSettingsComponent list) {
        return new KeybindComponent(this, list);
    }

}
