package cc.noxiuam.titanic.client.module.impl.fix.impl;

import cc.noxiuam.titanic.Ref;
import cc.noxiuam.titanic.client.module.impl.fix.AbstractFixModule;
import cc.noxiuam.titanic.event.impl.font.DrawStringEvent;
import net.minecraft.src.GuiIngameMenu;

/**
 * Removes "Saving level..." in the pause menu when in multiplayer
 */
public class SavingLevelFix extends AbstractFixModule {

    public SavingLevelFix() {
        super("Saving Level Fix");
        this.addEvent(DrawStringEvent.class, event -> {
            if (event.getString().equals("Saving level..")
                    && Ref.getMinecraft().isMultiplayerWorld()
                    && Ref.getMinecraft().currentScreen instanceof GuiIngameMenu) {
                event.cancel();
                event.setString("");
            }
        });
    }

}
