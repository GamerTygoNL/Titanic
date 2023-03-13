package cc.noxiuam.titanic.client.util;

import lombok.experimental.UtilityClass;
import net.minecraft.src.ItemStack;

@UtilityClass
public class ItemStackUtil {

    public int getItemDamage(ItemStack itemStack) {
        return itemStack.itemDamage;
    }

}
