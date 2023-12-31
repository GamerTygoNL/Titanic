package net.minecraft.src;

import java.util.Random;

public class BlockIce extends BlockBreakable {

    public BlockIce(int i, int j) {
        super(i, j, Material.ice, false);
        slipperiness = 0.98F;
        setTickOnLoad(true);
    }

    public int getRenderBlockPass() {
        return 1;
    }

    public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return super.shouldSideBeRendered(iblockaccess, i, j, k, 1 - l);
    }

    public void onBlockRemoval(World world, int i, int j, int k) {
        Material material = world.getBlockMaterial(i, j - 1, k);
        if (material.getIsSolid() || material.getIsLiquid()) {
            world.setBlockWithNotify(i, j, k, Block.waterStill.blockID);
        }
    }

    public int quantityDropped(Random random) {
        return 0;
    }

    public void updateTick(World world, int i, int j, int k, Random random) {
        if (world.getSavedLightValue(EnumSkyBlock.Block, i, j, k) > 11 - Block.lightOpacity[blockID]) {
            dropBlockAsItem(world, i, j, k, world.getBlockMetadata(i, j, k));
            world.setBlockWithNotify(i, j, k, Block.waterMoving.blockID);
        }
    }
}
