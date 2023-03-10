package cc.noxiuam.titanic.client.module.data.impl;

import cc.noxiuam.titanic.client.module.data.AbstractSetting;

public class BooleanSetting extends AbstractSetting<Boolean> {

    public BooleanSetting(String id, String name, Boolean defaultValue) {
        super(id, name, defaultValue);
    }

}
