package net.minecraft.src;

import java.util.Random;

public class BlockGravel extends BlockSand {

    public BlockGravel(int i, int j) {
        super(i, j);
    }

    public int idDropped(int i, Random random) {
        if (random.nextInt(10) == 0) {
            return Item.flint.shiftedIndex;
        } else {
            return blockID;
        }
    }
}
