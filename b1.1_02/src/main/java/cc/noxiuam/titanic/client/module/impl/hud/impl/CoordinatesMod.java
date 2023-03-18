package cc.noxiuam.titanic.client.module.impl.hud.impl;

import cc.noxiuam.titanic.client.module.data.anchor.GuiAnchor;
import cc.noxiuam.titanic.client.module.impl.hud.AbstractMovableModule;
import cc.noxiuam.titanic.client.ui.util.FontUtil;
import cc.noxiuam.titanic.client.ui.util.RenderUtil;
import cc.noxiuam.titanic.event.impl.gui.GuiDrawEvent;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.MathHelper;
import org.lwjgl.opengl.GL11;

public class CoordinatesMod extends AbstractMovableModule {

    public CoordinatesMod() {
        super("coordinates", "Coordinates", false);
        this.addEvent(GuiDrawEvent.class, this::draw);
    }

    private void draw(GuiDrawEvent event) {

        GL11.glPushMatrix();
        this.scaleAndTranslate();

        EntityPlayer player = this.mc.thePlayer;
        int playerX = MathHelper.floor_double(player.posX);
        int playerY = MathHelper.floor_double(player.posY);
        int playerZ = MathHelper.floor_double(player.posZ);
        this.setSize(105, 15);

        String position = "X: " + playerX + " Y: " + playerY + " Z: " + playerZ;

        RenderUtil.drawRect(this.x(), this.y(), 125, 25, 0x6F000000);
        FontUtil.drawCenteredString(position, (int) (this.x() + this.width() / 2F) + 1, (int) this.y() + 4, -1);

        GL11.glPopMatrix();
    }

}
