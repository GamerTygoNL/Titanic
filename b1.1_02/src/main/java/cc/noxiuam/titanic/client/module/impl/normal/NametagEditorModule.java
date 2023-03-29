package cc.noxiuam.titanic.client.module.impl.normal;

import cc.noxiuam.titanic.Ref;
import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.module.data.setting.impl.BooleanSetting;
import cc.noxiuam.titanic.client.module.data.setting.impl.KeybindSetting;
import cc.noxiuam.titanic.client.module.impl.hud.AbstractMovableModule;
import cc.noxiuam.titanic.client.ui.util.RenderUtil;
import cc.noxiuam.titanic.event.impl.keyboard.KeyboardEvent;
import cc.noxiuam.titanic.event.impl.world.player.NametagRenderEvent;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.Tessellator;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class NametagEditorModule extends AbstractMovableModule {

    private final KeybindSetting toggleKeybind;
    private final BooleanSetting textShadow, showBackground;

    public boolean showNametags = true;

    public NametagEditorModule() {
        super("nameTagEditor", "Nametag Editor", false);

        this.initSettings(
                this.toggleKeybind = new KeybindSetting("toggleKeybind", "Temp Toggle Keybind", 0),
                this.color(),
                this.textShadow = new BooleanSetting("textShadow", "Text Shadow", false),
                this.showBackground = new BooleanSetting("showBackground", "Show Background", true)
        );

        this.addEvent(KeyboardEvent.class, event -> {
            if (event.getKey() == this.toggleKeybind.value()) {
                this.showNametags = !this.showNametags;
            }
        });

        this.addEvent(NametagRenderEvent.class, this::onNametagRender);
    }

    private void onNametagRender(NametagRenderEvent event) {
        event.cancel();
        this.renderCustomNametag(event);
    }

    public void renderCustomNametag(NametagRenderEvent event) {
        FontRenderer fontRenderer = Ref.getMinecraft().fontRenderer;
        String s = event.getPlayer().field_771_i;
        float f3 = event.getF3();

        if (!this.showNametags) {
            GL11.glEnable(2896 /*GL_LIGHTING*/);
            GL11.glDisable(3042 /*GL_BLEND*/);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glPopMatrix();
            return;
        }

        if (!event.getPlayer().isSneaking()) {
            GL11.glDepthMask(false);
            GL11.glDisable(2929 /*GL_DEPTH_TEST*/);
            GL11.glEnable(3042 /*GL_BLEND*/);
            GL11.glBlendFunc(770, 771);
            Tessellator tessellator = Tessellator.instance;
            byte byte0 = 0;
            if (event.getPlayer().field_771_i.equals("deadmau5")) {
                byte0 = -10;
            }
            GL11.glDisable(3553 /*GL_TEXTURE_2D*/);

            if (this.showBackground.value()) {
                tessellator.startDrawingQuads();
                int j = fontRenderer.getStringWidth(s) / 2;
                tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
                tessellator.addVertex(-j - 1, -1 + byte0, 0.0D);
                tessellator.addVertex(-j - 1, 8 + byte0, 0.0D);
                tessellator.addVertex(j + 1, 8 + byte0, 0.0D);
                tessellator.addVertex(j + 1, -1 + byte0, 0.0D);
                tessellator.draw();
            }

            GL11.glEnable(3553 /*GL_TEXTURE_2D*/);
            GL11.glEnable(2929 /*GL_DEPTH_TEST*/);
            GL11.glDepthMask(true);

            if (this.textShadow.value()) {
                fontRenderer.drawStringWithShadow(this.getPrefixedTextColor() + s, -fontRenderer.getStringWidth(s) / 2, byte0, this.getChromaColor());
            } else {
                fontRenderer.drawString(this.getPrefixedTextColor() + s, -fontRenderer.getStringWidth(s) / 2, byte0, this.getChromaColor());
            }

            GL11.glEnable(2896 /*GL_LIGHTING*/);
            GL11.glDisable(3042 /*GL_BLEND*/);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glPopMatrix();
        } else {
            GL11.glTranslatef(0.0F, 0.25F / f3, 0.0F);
            GL11.glDepthMask(false);
            GL11.glEnable(3042 /*GL_BLEND*/);
            GL11.glBlendFunc(770, 771);
            Tessellator tessellator1 = Tessellator.instance;
            GL11.glDisable(3553 /*GL_TEXTURE_2D*/);

            if (this.showBackground.value()) {
                tessellator1.startDrawingQuads();
                int i = fontRenderer.getStringWidth(s) / 2;
                tessellator1.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
                tessellator1.addVertex(-i - 1, -1D, 0.0D);
                tessellator1.addVertex(-i - 1, 8D, 0.0D);
                tessellator1.addVertex(i + 1, 8D, 0.0D);
                tessellator1.addVertex(i + 1, -1D, 0.0D);
                tessellator1.draw();
            }

            GL11.glEnable(3553 /*GL_TEXTURE_2D*/);
            GL11.glDepthMask(true);

            if (this.textShadow.value()) {
                fontRenderer.drawStringWithShadow(s, -fontRenderer.getStringWidth(s) / 2, 0, 0x20ffffff);
            } else {
                fontRenderer.drawString(s, -fontRenderer.getStringWidth(s) / 2, 0, 0x20ffffff);
            }

            GL11.glEnable(2896 /*GL_LIGHTING*/);
            GL11.glDisable(3042 /*GL_BLEND*/);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glPopMatrix();
        }
    }

}
