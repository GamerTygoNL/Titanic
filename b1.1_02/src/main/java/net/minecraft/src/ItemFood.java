package net.minecraft.src;

public class ItemFood extends Item {

    private final int healAmount;

    public ItemFood(int i, int j) {
        super(i);
        healAmount = j;
        maxStackSize = 1;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        itemstack.stackSize--;
        entityplayer.heal(healAmount);
        return itemstack;
    }
}
