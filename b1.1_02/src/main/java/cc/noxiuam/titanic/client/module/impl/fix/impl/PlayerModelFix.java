package cc.noxiuam.titanic.client.module.impl.fix.impl;

import cc.noxiuam.titanic.client.module.impl.fix.AbstractFixModule;
import cc.noxiuam.titanic.event.impl.world.player.model.CapeRenderEvent;
import cc.noxiuam.titanic.event.impl.world.player.model.PlayerModelRenderEvent;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;

/**
 * Fixes the cape model going inside the chestplate model,
 * as well as the player's head model positions being incorrect when sneaking and unsneaking.
 */
public class PlayerModelFix extends AbstractFixModule {

    public PlayerModelFix() {
        super("Player Model Fix");
        addEvent(CapeRenderEvent.class, this::fixCapeModel);
        addEvent(PlayerModelRenderEvent.class, this::fixPlayerHeadModel);
    }

    public void fixPlayerHeadModel(PlayerModelRenderEvent event) {
        event.getModel().field_1285_b.offsetY = (event.getModel().field_1277_j ? 1.0F : 0.0F);
    }

    public void fixCapeModel(CapeRenderEvent event) {
        EntityPlayer player = event.getPlayer();
        ItemStack chestplate = player.inventory.armorItemInSlot(2);
        ItemStack leggings = player.inventory.armorItemInSlot(3);
        boolean raiseDueToArmor = chestplate != null || leggings != null;

        if (player.isSneaking()) {
            event.cancel();
            event.value += 19.0F;
            if (raiseDueToArmor) {
                event.value += 20.0F;
            }
        }
    }

}
