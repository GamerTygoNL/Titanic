package net.minecraft.src;

public class CraftingInventoryWorkbenchCB extends CraftingInventoryCB {

    private final World field_20133_c;
    private final int field_20132_h;
    private final int field_20131_i;
    private final int field_20130_j;
    public InventoryCrafting craftMatrix;
    public IInventory craftResult;
    public CraftingInventoryWorkbenchCB(InventoryPlayer inventoryplayer, World world, int i, int j, int k) {
        craftMatrix = new InventoryCrafting(this, 3, 3);
        craftResult = new InventoryCraftResult();
        field_20133_c = world;
        field_20132_h = i;
        field_20131_i = j;
        field_20130_j = k;
        func_20117_a(new SlotCrafting(craftMatrix, craftResult, 0, 124, 35));
        for (int l = 0; l < 3; l++) {
            for (int k1 = 0; k1 < 3; k1++) {
                func_20117_a(new Slot(craftMatrix, k1 + l * 3, 30 + k1 * 18, 17 + l * 18));
            }

        }

        for (int i1 = 0; i1 < 3; i1++) {
            for (int l1 = 0; l1 < 9; l1++) {
                func_20117_a(new Slot(inventoryplayer, l1 + i1 * 9 + 9, 8 + l1 * 18, 84 + i1 * 18));
            }

        }

        for (int j1 = 0; j1 < 9; j1++) {
            func_20117_a(new Slot(inventoryplayer, j1, 8 + j1 * 18, 142));
        }

        onCraftMatrixChanged(craftMatrix);
    }

    public void onCraftMatrixChanged(IInventory iinventory) {
        int[] ai = new int[9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int k = i + j * 3;
                ItemStack itemstack = craftMatrix.getStackInSlot(k);
                if (itemstack == null) {
                    ai[k] = -1;
                } else {
                    ai[k] = itemstack.itemID;
                }
            }

        }

        craftResult.setInventorySlotContents(0, CraftingManager.getInstance().craft(ai));
    }

    public void onCraftGuiClosed(EntityPlayer entityplayer) {
        super.onCraftGuiClosed(entityplayer);
        for (int i = 0; i < 9; i++) {
            ItemStack itemstack = craftMatrix.getStackInSlot(i);
            if (itemstack != null) {
                entityplayer.dropPlayerItem(itemstack);
            }
        }

    }

    public boolean func_20120_b(EntityPlayer entityplayer) {
        if (field_20133_c.getBlockId(field_20132_h, field_20131_i, field_20130_j) != Block.workbench.blockID) {
            return false;
        }
        return entityplayer.getDistanceSq((double) field_20132_h + 0.5D, (double) field_20131_i + 0.5D, (double) field_20130_j + 0.5D) <= 64D;
    }
}
