package cc.noxiuam.titanic.client.module.impl.hud.impl;

import cc.noxiuam.titanic.client.module.impl.hud.AbstractMovableModule;
import cc.noxiuam.titanic.client.ui.util.RenderUtil;
import cc.noxiuam.titanic.event.impl.gui.GuiDrawEvent;

public class FofoModule extends AbstractMovableModule {

    public FofoModule() {
        super("fofo", "Fofo", false);
        this.addEvent(GuiDrawEvent.class, this::draw);
    }

    private void draw(GuiDrawEvent event) {
        this.setSize(75, 100);

        String image = "/titanic/fofo.png";
        RenderUtil.renderIcon(
                image,
                this.x(),
                this.y(),
                75,
                100
        );
    }

}
