package cc.noxiuam.titanic.client.ui.util;

import cc.noxiuam.titanic.Titanic;
import lombok.experimental.UtilityClass;
import net.minecraft.client.Minecraft;

@UtilityClass
public class FontUtil {

    public void drawCenteredString(String string, int x, int y, int color) {
        Minecraft mc = Titanic.getInstance().getBridge().getMinecraftBridge().bridge$getMinecraft();
        mc.fontRenderer.drawStringWithShadow(string, x - mc.fontRenderer.getStringWidth(string) / 2, y, color);
    }

}
