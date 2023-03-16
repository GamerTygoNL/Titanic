package cc.noxiuam.titanic.client.module.impl.fix;

import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.util.CaseUtils;

public abstract class AbstractFixModule extends AbstractModule {

    public AbstractFixModule(String name) {
        super(CaseUtils.toCamelCase(name, false), name, true);
    }

}