package cc.noxiuam.titanic.client.module.impl.fix.impl;

import cc.noxiuam.titanic.Ref;
import cc.noxiuam.titanic.client.module.impl.fix.AbstractFixModule;
import cc.noxiuam.titanic.event.impl.font.DrawStringEvent;
import net.minecraft.src.GuiChest;

public class ChestTextFix extends AbstractFixModule {

    public ChestTextFix() {
        super("Chest Text Fix");

        this.addEvent(DrawStringEvent.class, event -> {
            if (event.getString().equals("Large chest") && Ref.getMinecraft().currentScreen instanceof GuiChest) {
                event.cancel();
                event.setString("Large Chest");
            }
        });
    }

}
