package cc.noxiuam.titanic.client.module.impl.hud.impl;

import cc.noxiuam.titanic.Ref;
import cc.noxiuam.titanic.client.module.data.setting.impl.BooleanSetting;
import cc.noxiuam.titanic.client.module.data.setting.impl.MultiOptionSetting;
import cc.noxiuam.titanic.client.module.impl.hud.AbstractMovableModule;
import cc.noxiuam.titanic.client.ui.util.RenderUtil;
import cc.noxiuam.titanic.client.util.WorldUtil;
import cc.noxiuam.titanic.event.impl.gui.GuiDrawEvent;
import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;

public class LightLevelModule extends AbstractMovableModule {

    private final RenderItem itemRenderer = new RenderItem();

    private final MultiOptionSetting mode;
    private final BooleanSetting showBackground, showIcon, showName;

    public LightLevelModule() {
        super("lightLevel", "Light Level", false);

        this.initSettings(
                this.mode = new MultiOptionSetting("mode", "Mode", "Looking At", "Looking At", "Standing On"),
                this.showBackground = new BooleanSetting("showBackground", "Show Background", true),
                this.showIcon = new BooleanSetting("showIcon", "Show Icon", true),
                this.showName = new BooleanSetting("showName", "Show Name", true)
        );
        this.addEvent(GuiDrawEvent.class, this::drawLightLevel);
    }

    private void drawLightLevel(GuiDrawEvent event) {
        EntityPlayer player = this.mc.thePlayer;
        int blockX = 0;
        int blockY = 0;
        int blockZ = 0;

        if (this.mode.value().equalsIgnoreCase("Standing On")) {
            blockX = MathHelper.floor_double(player.posX);
            blockY = MathHelper.floor_double(player.posY - 2);
            blockZ = MathHelper.floor_double(player.posZ);
        } else if (this.mode.value().equalsIgnoreCase("Looking At") && this.mc.objectMouseOver != null) {
            blockX = this.mc.objectMouseOver.blockX;
            blockY = this.mc.objectMouseOver.blockY;
            blockZ = this.mc.objectMouseOver.blockZ;
        }

        Block block;
        int blockId;
        String lightLevel;

        blockId = this.mc.theWorld.getBlockId(blockX, blockY, blockZ);
        block = WorldUtil.getBlockById(blockId);


        String blockName;
        if (block == null) {
            blockName = "Block: None";
            lightLevel = "Light Level: 0";
        } else {
            lightLevel = "Light Level: " + String.format("%.02f", block.getBlockBrightness(this.mc.theWorld, blockX, blockY, blockZ));
            blockName = "Block: " + StringTranslate.func_20162_a().func_20161_b(block.func_20013_i()).trim();
        }

        if (block == Block.bedrock && this.mc.thePlayer.posY > 6) {
            blockName = "Block: None";
            lightLevel = "Light Level: 0";
        }

        int width = this.mc.fontRenderer.getStringWidth(blockName);
        if (lightLevel.length() > blockName.length()) {
            width = this.mc.fontRenderer.getStringWidth(lightLevel);
        }

        if (this.showBackground.value()) {
            this.setSize(width + (this.showIcon.value() ? 35 : 10), 33);
        }

        if (this.showBackground.value()) {
            RenderUtil.drawRect(this.x(), this.y(), this.x() + this.width(), this.y() + this.height(), 0x6F000000);
        }

        if (this.showName.value()) {
            this.mc.fontRenderer.drawStringWithShadow(
                    blockName,
                    (int) (this.x() + (this.showIcon.value() ? 30 : 5)),
                    (int) this.y() + 6,
                    -1
            );
        }

        this.mc.fontRenderer.drawStringWithShadow(
                lightLevel,
                (int) (this.x() + (this.showIcon.value() ? 30 : 5)),
                (int) this.y() + (this.showName.value() ? 18 : 6),
                -1
        );

        if (block == Block.bedrock && this.mc.thePlayer.posY > 6) {
            return;
        }

        try {
            if (this.showIcon.value() && blockId != 0) {
                GL11.glPushMatrix();

                ItemStack itemStack = new ItemStack(blockId);

                this.itemRenderer.renderItemIntoGUI(mc.fontRenderer, mc.renderEngine, itemStack, (int) (this.x() + 8), (int) (this.y() + 8));
                this.itemRenderer.renderItemOverlayIntoGUI(mc.fontRenderer, mc.renderEngine, itemStack, (int) (this.x() + 8), (int) (this.y() + 8));

                RenderHelper.disableStandardItemLighting();
                GL11.glDisable(32826 /*GL_RESCALE_NORMAL_EXT*/);
                GL11.glPopMatrix();
            }
        } catch (NullPointerException ignored) { }
    }

}
