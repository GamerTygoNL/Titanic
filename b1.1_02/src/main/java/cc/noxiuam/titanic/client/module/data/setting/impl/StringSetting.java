package cc.noxiuam.titanic.client.module.data.setting.impl;

import cc.noxiuam.titanic.client.module.data.setting.AbstractSetting;
import cc.noxiuam.titanic.client.ui.component.type.module.ModuleSettingsComponent;
import cc.noxiuam.titanic.client.ui.component.type.setting.AbstractSettingComponent;
import cc.noxiuam.titanic.client.ui.component.type.setting.impl.string.StringComponent;
import lombok.Getter;

@Getter
public class StringSetting extends AbstractSetting<String> {

    private int maxLength = 10;

    public StringSetting(String id, String name, String defaultValue, int maxLength) {
        super(id, name, defaultValue);
        this.maxLength = maxLength;
    }

    @Override
    public AbstractSettingComponent<String> getComponent(ModuleSettingsComponent list) {
        return new StringComponent(this, list);
    }

}
