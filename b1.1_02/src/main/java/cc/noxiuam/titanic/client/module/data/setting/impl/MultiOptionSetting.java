package cc.noxiuam.titanic.client.module.data.setting.impl;

import cc.noxiuam.titanic.client.module.data.setting.AbstractSetting;
import cc.noxiuam.titanic.client.ui.module.component.ModuleSettingsComponent;
import cc.noxiuam.titanic.client.ui.component.type.setting.AbstractSettingComponent;
import cc.noxiuam.titanic.client.ui.component.type.setting.impl.MultiOptionComponent;
import lombok.Getter;

@Getter
public class MultiOptionSetting extends AbstractSetting<String> {

    private final String[] acceptedValues;

    public MultiOptionSetting(String id, String name, String defaultValue, String... acceptedValues) {
        super(id, name, defaultValue);
        this.acceptedValues = acceptedValues;
    }

    @Override
    public AbstractSettingComponent<String> getComponent(ModuleSettingsComponent list) {
        return new MultiOptionComponent(this, list);
    }

}
