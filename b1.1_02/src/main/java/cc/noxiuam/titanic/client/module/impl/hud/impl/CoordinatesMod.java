package cc.noxiuam.titanic.client.module.impl.hud.impl;

import cc.noxiuam.titanic.client.module.data.setting.impl.BooleanSetting;
import cc.noxiuam.titanic.client.module.data.setting.impl.MultiOptionSetting;
import cc.noxiuam.titanic.client.module.impl.hud.AbstractMovableModule;
import cc.noxiuam.titanic.client.ui.util.FontUtil;
import cc.noxiuam.titanic.client.ui.util.RenderUtil;
import cc.noxiuam.titanic.event.impl.gui.GuiDrawEvent;
import com.google.common.io.LimitInputStream;
import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.MathHelper;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CoordinatesMod extends AbstractMovableModule {

    private final MultiOptionSetting displayMode;
    private final BooleanSetting showBackground;

    public CoordinatesMod() {
        super("coordinates", "Coordinates", false);

        this.initSettings(
                this.displayMode = new MultiOptionSetting(
                        "displayMode",
                        "Display Mode",
                        "Horizontal",
                        "Horizontal", "Vertical"
                ),
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

        String x = "X: " + playerX;
        String y = " Y: " + playerY;
        String z = " Z: " + playerZ;

        List<String> positions = new ArrayList<>();
        positions.add(x);
        positions.add(y);
        positions.add(z);

        String position = x + y + z;

        if (this.displayMode.value().equalsIgnoreCase("Horizontal")) {
            this.setSize(this.mc.fontRenderer.getStringWidth(position) + 10, 20);
        } else {
            String longest = positions.stream().max(Comparator.comparingInt(String::length)).get();
            this.setSize(this.mc.fontRenderer.getStringWidth(longest) + 6, 44);
        }

        if (this.displayMode.value().equalsIgnoreCase("Horizontal")) {
            if (this.showBackground.value()) {
                RenderUtil.drawRect(this.x(), this.y(), this.x() + this.width(), this.y() + this.height(), 0x6F000000);
            }
            FontUtil.drawCenteredString(position, (int) (this.x() + this.width() / 2F) + 1, (int) this.y() + 6, -1);
        } else {
            if (this.showBackground.value()) {
                RenderUtil.drawRect(this.x(), this.y(), this.x() + this.width(), this.y() + this.height(), 0x6F000000);
            }
            this.mc.fontRenderer.drawStringWithShadow(x, (int) (this.x() + 5), (int) this.y() + 6, -1);
            this.mc.fontRenderer.drawStringWithShadow(y, (int) (this.x() + 1), (int) this.y() + 18, -1);
            this.mc.fontRenderer.drawStringWithShadow(z, (int) (this.x() + 1), (int) this.y() + 30, -1);
        }

        GL11.glPopMatrix();
    }

}
