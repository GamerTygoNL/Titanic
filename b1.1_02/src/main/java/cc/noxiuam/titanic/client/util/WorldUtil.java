package cc.noxiuam.titanic.client.util;

import lombok.experimental.UtilityClass;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;

@UtilityClass
public class WorldUtil {

    public int getItemDamage(ItemStack itemStack) {
        return itemStack.itemDamage;
    }

    public Block getBlockById(int targetId) {
        return Block.blocksList[targetId];
    }

}
