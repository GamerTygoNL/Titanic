package cc.noxiuam.titanic.client.module.impl.hud.impl;

import cc.noxiuam.titanic.client.module.data.setting.impl.BooleanSetting;
import cc.noxiuam.titanic.client.module.data.setting.impl.MultiOptionSetting;
import cc.noxiuam.titanic.client.module.impl.hud.AbstractMovableModule;
import cc.noxiuam.titanic.client.ui.util.FontUtil;
import cc.noxiuam.titanic.client.ui.util.RenderUtil;
import cc.noxiuam.titanic.event.impl.gui.GuiDrawEvent;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.MathHelper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CoordinatesModule extends AbstractMovableModule {

    private final MultiOptionSetting displayMode;
    private final BooleanSetting showBackground, showY;

    public CoordinatesModule() {
        super("coordinates", "Coordinates", false);

        this.initSettings(
                this.displayMode = new MultiOptionSetting(
                        "displayMode",
                        "Display Mode",
                        "Horizontal",
                        "Horizontal", "Vertical"
                ),
                this.showBackground = new BooleanSetting("showBackground", "Show Background", true),
                this.showY = new BooleanSetting("showY", "Show Y Level", true)
        );

        this.addEvent(GuiDrawEvent.class, this::drawCoordinates);
    }

    private void drawCoordinates(GuiDrawEvent event) {
        EntityPlayer player = this.mc.thePlayer;
        int playerX = MathHelper.floor_double(player.posX);
        int playerY = MathHelper.floor_double(player.posY);
        int playerZ = MathHelper.floor_double(player.posZ);

        String x = "X: " + playerX;
        String y = " Y: " + playerY;
        String z = " Z: " + playerZ;

        List<String> positions = new ArrayList<>();
        positions.add(x);
        if (this.showY.value()) {
            positions.add(y);
        }
        positions.add(z);

        String position = x + (this.showY.value() ? y : "") + z;

        if (this.displayMode.value().equalsIgnoreCase("Horizontal")) {
            this.setSize(this.mc.fontRenderer.getStringWidth(position) + 10, 20);
        } else {
            String longest = positions.stream().max(Comparator.comparingInt(String::length)).get();
            this.setSize(this.mc.fontRenderer.getStringWidth(longest) + 9, this.showY.value() ? 44 : 32);
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

            if (this.showY.value()) {
                this.mc.fontRenderer.drawStringWithShadow(y, (int) (this.x() + 1), (int) this.y() + 18, -1);
            }

            this.mc.fontRenderer.drawStringWithShadow(z, (int) (this.x() + 1), (int) this.y() + (this.showY.value() ? 30 : 18), -1);
        }
    }

}
