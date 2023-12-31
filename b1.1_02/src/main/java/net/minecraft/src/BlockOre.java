package net.minecraft.src;

import java.util.Random;

public class BlockOre extends Block {

    public BlockOre(int i, int j) {
        super(i, j, Material.rock);
    }

    public int idDropped(int i, Random random) {
        if (blockID == Block.oreCoal.blockID) {
            return Item.coal.shiftedIndex;
        }
        if (blockID == Block.oreDiamond.blockID) {
            return Item.diamond.shiftedIndex;
        } else {
            return blockID;
        }
    }

    public int quantityDropped(Random random) {
        return 1;
    }
}
