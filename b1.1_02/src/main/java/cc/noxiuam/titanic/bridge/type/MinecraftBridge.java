package cc.noxiuam.titanic.bridge.type;

import net.minecraft.client.Minecraft;

/**
 * Used to get the Minecraft instance, since there is no getMinecraft() lol.
 */
public interface MinecraftBridge {

    Minecraft bridge$getMinecraft();

}
