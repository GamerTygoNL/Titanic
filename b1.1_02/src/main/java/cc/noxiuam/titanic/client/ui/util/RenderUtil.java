package cc.noxiuam.titanic.client.ui.util;

import cc.noxiuam.titanic.Ref;
import lombok.experimental.UtilityClass;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Tessellator;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

@UtilityClass
public class RenderUtil {

    public void renderIcon(String resourceLocation, float size, float x, float y) {
        float f4 = size * 2.0f;
        float f5 = size * 2.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;

        Minecraft mc = Ref.getMinecraft();

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
        Minecraft mc = Ref.getMinecraft();

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

    public void drawRectWithOutline(float f, float f2, float f3, float f4, float f5, int n, int n2) {
        drawRect(f + f5, f2 + f5, f3 - f5, f4 - f5, n2);
        drawRect(f, f2 + f5, f + f5, f4 - f5, n);
        drawRect(f3 - f5, f2 + f5, f3, f4 - f5, n);
        drawRect(f, f2, f3, f2 + f5, n);
        drawRect(f, f4 - f5, f3, f4, n);
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

    public void drawRoundedOutline(float x, float y, float width, float height, float radius, float lineWidth, int color) {
        GL11.glPushAttrib(0);
        GL11.glScalef(0.5f, 0.5f, 0.5f);

        x *= 2.0D;
        y *= 2.0D;
        width *= 2.0D;
        height *= 2.0D;

        GL11.glEnable(GL_BLEND);
        GL11.glDisable(GL_TEXTURE_2D);
        setColor(color);
        GL11.glEnable(GL_LINE_SMOOTH);
        GL11.glLineWidth(lineWidth);
        GL11.glBegin(GL_LINE_LOOP);

        int i;
        for (i = 0; i <= 90; i += 3) {
            glVertex2d(x + radius + Math.sin(i * Math.PI / 180.0D) * radius * -1.0D, y + radius + Math.cos(i * Math.PI / 180.0D) * radius * -1.0D);
        }
        for (i = 90; i <= 180; i += 3) {
            glVertex2d(x + radius + Math.sin(i * Math.PI / 180.0D) * radius * -1.0D, height - radius + Math.cos(i * Math.PI / 180.0D) * radius * -1.0D);
        }
        for (i = 0; i <= 90; i += 3) {
            glVertex2d(width - radius + Math.sin(i * Math.PI / 180.0D) * radius, height - radius + Math.cos(i * Math.PI / 180.0D) * radius);
        }
        for (i = 90; i <= 180; i += 3) {
            glVertex2d(width - radius + Math.sin(i * Math.PI / 180.0D) * radius, y + radius + Math.cos(i * Math.PI / 180.0D) * radius);
        }

        GL11.glEnd();
        GL11.glEnable(GL_TEXTURE_2D);
        GL11.glDisable(GL_BLEND);
        GL11.glDisable(GL_LINE_SMOOTH);
        GL11.glDisable(GL_BLEND);
        GL11.glEnable(GL_TEXTURE_2D);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        GL11.glPopAttrib();
        GL11.glLineWidth(1);
        setColor(color);
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

    public void startScissorBox(int x, int y, int width, int height, float scaledWidth, int scaledHeight) {
        int sY = height - y;
        int sX = width - x;
        int n8 = scaledHeight - height;
        GL11.glScissor((int) ((float) x * scaledWidth), (int) ((float) n8 * scaledWidth), (int) ((float) sX * scaledWidth), (int) ((float) sY * scaledWidth));
    }

    public void setColor(int color) {
        float a = (color >> 24 & 0xFF) / 255.0F;
        float r = (color >> 16 & 0xFF) / 255.0F;
        float g = (color >> 8 & 0xFF) / 255.0F;
        float b = (color & 0xFF) / 255.0F;

        glColor4f(r, g, b, a);
    }

}
