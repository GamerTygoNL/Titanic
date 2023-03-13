package cc.noxiuam.titanic.client.module.impl.fix;

import cc.noxiuam.titanic.client.module.AbstractModule;

public abstract class AbstractFixModule extends AbstractModule {

    public AbstractFixModule(String name) {
        super(name.toLowerCase().replaceAll(" ", "") + "_fix", name, true);
    }

}