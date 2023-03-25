package cc.noxiuam.titanic.client.module.impl.fix.impl;

import cc.noxiuam.titanic.client.module.impl.fix.AbstractFixModule;
import cc.noxiuam.titanic.event.impl.world.entity.PreEntityRenderEvent;

public class DeadEntityRenderFix extends AbstractFixModule {

    public DeadEntityRenderFix() {
        super("Dead Entity Render Fix");
        this.addEvent(PreEntityRenderEvent.class, this::onPreEntityRender);
    }

    private void onPreEntityRender(PreEntityRenderEvent event) {
        if (event.getEntity().isDead) {
            event.cancel();
        }
    }

}
