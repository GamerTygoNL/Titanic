package net.minecraft.src;

public class EntitySheep extends EntityAnimals {

    public boolean sheared;

    public EntitySheep(World world) {
        super(world);
        sheared = false;
        texture = "/mob/sheep.png";
        setSize(0.9F, 1.3F);
    }

    public boolean attackEntityFrom(Entity entity, int i) {
        if (!worldObj.multiplayerWorld && !sheared && (entity instanceof EntityLiving)) {
            sheared = true;
            int j = 1 + rand.nextInt(3);
            for (int k = 0; k < j; k++) {
                EntityItem entityitem = dropItemWithOffset(Block.cloth.blockID, 1, 1.0F);
                entityitem.motionY += rand.nextFloat() * 0.05F;
                entityitem.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
                entityitem.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
            }

        }
        return super.attackEntityFrom(entity, i);
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setBoolean("Sheared", sheared);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        super.readEntityFromNBT(nbttagcompound);
        sheared = nbttagcompound.getBoolean("Sheared");
    }

    protected String getLivingSound() {
        return "mob.sheep";
    }

    protected String getHurtSound() {
        return "mob.sheep";
    }

    protected String getDeathSound() {
        return "mob.sheep";
    }
}
