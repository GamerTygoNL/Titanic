package cc.noxiuam.titanic.client.module.impl.hud;

import cc.noxiuam.titanic.client.module.AbstractModule;
import lombok.Getter;

@Getter
public class AbstractMovableModule extends AbstractModule {

    private float x;
    private float y;

    private float width;
    private float height;

    public AbstractMovableModule(String id, String name, boolean enabledByDefault) {
        super(id, name, enabledByDefault);
    }

}
