package net.minecraft.src;

import java.util.Random;

public class BlockSnowBlock extends Block {

    protected BlockSnowBlock(int i, int j) {
        super(i, j, Material.builtSnow);
        setTickOnLoad(true);
    }

    public int idDropped(int i, Random random) {
        return Item.snowball.shiftedIndex;
    }

    public int quantityDropped(Random random) {
        return 4;
    }

    public void updateTick(World world, int i, int j, int k, Random random) {
        if (world.getSavedLightValue(EnumSkyBlock.Block, i, j, k) > 11) {
            dropBlockAsItem(world, i, j, k, world.getBlockMetadata(i, j, k));
            world.setBlockWithNotify(i, j, k, 0);
        }
    }
}
