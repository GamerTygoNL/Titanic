package cc.noxiuam.titanic.client.ui.util;

import cc.noxiuam.titanic.client.Titanic;
import lombok.experimental.UtilityClass;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Tessellator;
import org.lwjgl.opengl.GL11;

@UtilityClass
public class RenderUtil {

    public void renderIcon(String resourceLocation, float size, float x, float y) {
        float f4 = size * 2.0f;
        float f5 = size * 2.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;

        Minecraft mc = Titanic.getInstance().getBridge().getMinecraftBridge().bridge$getMinecraft();

        GL11.glEnable(3042);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture(resourceLocation));
        GL11.glBegin(7);
        GL11.glTexCoord2d(f6 / size, f7 / size);
        GL11.glVertex2d(x, y);
        GL11.glTexCoord2d(f6 / size, (f7 + size) / size);
        GL11.glVertex2d(x, y + f5);
        GL11.glTexCoord2d((f6 + size) / size, (f7 + size) / size);
        GL11.glVertex2d(x + f4, y + f5);
        GL11.glTexCoord2d((f6 + size) / size, f7 / size);
        GL11.glVertex2d(x + f4, y);
        GL11.glEnd();
        GL11.glDisable(3042);
    }

    public void renderIcon(String resourceLocation, float x, float y, float width, float height) {
        float f5 = width / 2.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        Minecraft mc = Titanic.getInstance().getBridge().getMinecraftBridge().bridge$getMinecraft();

        GL11.glEnable(3042);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture(resourceLocation));
        GL11.glBegin(7);
        GL11.glTexCoord2d(f6 / f5, f7 / f5);
        GL11.glVertex2d(x, y);
        GL11.glTexCoord2d(f6 / f5, (f7 + f5) / f5);
        GL11.glVertex2d(x, y + height);
        GL11.glTexCoord2d((f6 + f5) / f5, (f7 + f5) / f5);
        GL11.glVertex2d(x + width, y + height);
        GL11.glTexCoord2d((f6 + f5) / f5, f7 / f5);
        GL11.glVertex2d(x + width, y);
        GL11.glEnd();
        GL11.glDisable(3042);
    }

    public void drawRoundedRect(double x, double y, double width, double height, double radius, int color) {
        int n2;
        float f = (float)(color >> 24 & 0xFF) / (float)255;
        float f2 = (float)(color >> 16 & 0xFF) / (float)255;
        float f3 = (float)(color >> 8 & 0xFF) / (float)255;
        float f4 = (float)(color & 0xFF) / (float)255;
        GL11.glPushAttrib(0);
        GL11.glScaled(0.5, 0.5, 0.5);
        x *= 2;
        y *= 2;
        width *= 2;
        height *= 2;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glEnable(2848);
        GL11.glBegin(9);
        for (n2 = 0; n2 <= 90; n2 += 3) {
            GL11.glVertex2d(x + radius + Math.sin((double)n2 * (6.5973445528769465 * 0.4761904776096344) / (double)180) * (radius * (double)-1), y + radius + Math.cos((double)n2 * (42.5 * 0.07391982714328925) / (double)180) * (radius * (double)-1));
        }
        for (n2 = 90; n2 <= 180; n2 += 3) {
            GL11.glVertex2d(x + radius + Math.sin((double)n2 * (0.5711986642890533 * 5.5) / (double)180) * (radius * (double)-1), height - radius + Math.cos((double)n2 * (0.21052631735801697 * 14.922564993369743) / (double)180) * (radius * (double)-1));
        }
        for (n2 = 0; n2 <= 90; n2 += 3) {
            GL11.glVertex2d(width - radius + Math.sin((double)n2 * (4.466951941998311 * 0.7032967209815979) / (double)180) * radius, height - radius + Math.cos((double)n2 * (28.33333396911621 * 0.11087973822685955) / (double)180) * radius);
        }
        for (n2 = 90; n2 <= 180; n2 += 3) {
            GL11.glVertex2d(width - radius + Math.sin((double)n2 * ((double)0.6f * 5.2359875479235365) / (double)180) * radius, y + radius + Math.cos((double)n2 * (2.8529412746429443 * 1.1011767685204017) / (double)180) * radius);
        }
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glScaled(2, 2, 2);
        GL11.glPopAttrib();
    }

    public void drawRect(float i, float j, float k, float l, int i1) {
        float f = (float) (i1 >> 24 & 0xff) / 255F;
        float f1 = (float) (i1 >> 16 & 0xff) / 255F;
        float f2 = (float) (i1 >> 8 & 0xff) / 255F;
        float f3 = (float) (i1 & 0xff) / 255F;
        Tessellator tessellator = Tessellator.instance;
        GL11.glEnable(3042 /*GL_BLEND*/);
        GL11.glDisable(3553 /*GL_TEXTURE_2D*/);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(f1, f2, f3, f);
        tessellator.startDrawingQuads();
        tessellator.addVertex(i, l, 0.0D);
        tessellator.addVertex(k, l, 0.0D);
        tessellator.addVertex(k, j, 0.0D);
        tessellator.addVertex(i, j, 0.0D);
        tessellator.draw();
        GL11.glEnable(3553 /*GL_TEXTURE_2D*/);
        GL11.glDisable(3042 /*GL_BLEND*/);
    }

    public void startScissorBox(int x, int y, int width, int height) {
        GL11.glScissor(x, y, width, height);
    }

}
