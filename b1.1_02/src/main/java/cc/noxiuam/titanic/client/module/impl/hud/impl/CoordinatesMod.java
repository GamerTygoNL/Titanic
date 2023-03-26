package cc.noxiuam.titanic.client.module.impl.hud.impl;

import cc.noxiuam.titanic.client.module.data.setting.impl.BooleanSetting;
import cc.noxiuam.titanic.client.module.impl.hud.AbstractMovableModule;
import cc.noxiuam.titanic.client.ui.util.FontUtil;
import cc.noxiuam.titanic.client.ui.util.RenderUtil;
import cc.noxiuam.titanic.event.impl.gui.GuiDrawEvent;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.MathHelper;
import org.lwjgl.opengl.GL11;

public class CoordinatesMod extends AbstractMovableModule {

    private final BooleanSetting showBackground;

    public CoordinatesMod() {
        super("coordinates", "Coordinates", false);

        this.initSettings(
                this.showBackground = new BooleanSetting("showBackground", "Show Background", true)
        );

        this.addEvent(GuiDrawEvent.class, this::draw);
    }

    private void draw(GuiDrawEvent event) {

        GL11.glPushMatrix();

        EntityPlayer player = this.mc.thePlayer;
        int playerX = MathHelper.floor_double(player.posX);
        int playerY = MathHelper.floor_double(player.posY);
        int playerZ = MathHelper.floor_double(player.posZ);

        String position = "X: " + playerX + " Y: " + playerY + " Z: " + playerZ;

        this.setSize(this.mc.fontRenderer.getStringWidth(position) + 10, 20);
        if (this.showBackground.value()) {
            RenderUtil.drawRect(this.x(), this.y(), this.x() + this.width(), this.y() + this.height(), 0x6F000000);
        }

        FontUtil.drawCenteredString(position, (int) (this.x() + this.width() / 2F) + 1, (int) this.y() + 6, -1);

        GL11.glPopMatrix();
    }

}
