package cc.noxiuam.titanic.client.ui.component.type;

import cc.noxiuam.titanic.client.ui.component.AbstractComponent;
import cc.noxiuam.titanic.client.ui.util.RenderUtil;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class ScrollbarComponent extends AbstractComponent {

    protected double internalScrollAmount;
    protected float position;
    protected float scrollAmount;
    protected boolean hovering;
    protected boolean drawing;
    protected float oldTranslateY;
    private boolean buttonHeld;

    @Override
    public boolean mouseInside(float f, float f2) {
        return f >= this.x && f <= this.x + this.width && f2 > this.y && f2 < this.y + this.height;
    }

    public float getPosition() {
        return this.position;
    }

    public void drawScrollable(float f, float f2, boolean bl) {
        if (this.internalScrollAmount != 0.0) {
            this.internalScrollAmount = 0.0;
        }
        double d = Math.round(this.internalScrollAmount / 5.0);
        this.internalScrollAmount -= d;
        if (this.internalScrollAmount != 0.0) {
            this.position = (float)((double)this.position + d);
        }
        if (this.drawing) {
            if (this.position < -this.scrollAmount + this.height) {
                this.position = -this.scrollAmount + this.height;
                this.internalScrollAmount = 0.0;
            }
            if (this.position > 0.0f) {
                this.position = 0.0f;
                this.internalScrollAmount = 0.0;
            }
        }
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, this.position, 0.0F);
    }

    public void onScroll(float f, float f2, boolean bl) {
        if (this.internalScrollAmount != 0.0) {
            this.internalScrollAmount = 0.0;
        }
        double d = Math.round(this.internalScrollAmount / 5.0);
        this.internalScrollAmount -= d;
        if (this.internalScrollAmount != 0.0) {
            this.position = (float)((double)this.position - d);
        }
        if (this.drawing) {
            if (this.position < -this.scrollAmount + this.height) {
                this.position = -this.scrollAmount + this.height;
                this.internalScrollAmount = 0.0;
            }
            if (this.position > 0.0f) {
                this.position = 0.0f;
                this.internalScrollAmount = 0.0;
            }
        }
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, -this.position, 0.0F);
    }

    public boolean isAtBottom() {
        return this.scrollAmount > this.height;
    }

    public void draw(float mouseX, float mouseY) {
        float f3;
        this.drawing = true;
        GL11.glPopMatrix();
        boolean bl2 = this.isAtBottom();
        if (!(!this.hovering || Mouse.isButtonDown(0) && this.mouseInside(mouseX, mouseY))) {
            this.hovering = false;
        }
        if (this.buttonHeld && !Mouse.isButtonDown(0)) {
            this.buttonHeld = false;
        }
        float f4 = this.height;
        float f5 = this.scrollAmount;
        float f6 = f4 / f5 * 100.0f;
        float f7 = f4 / 100.0f * f6;
        float f8 = this.position / 100.0f * f6;
        if (Mouse.isButtonDown(0) && this.buttonHeld) {
            f3 = mouseY - this.y;
            float f9 = f3 / this.height;
            this.position = -(this.scrollAmount * f9) + f7 / 2.0f + f9;
        }
        if (bl2) {
            boolean bl3;
            f3 = this.height;
            boolean bl4 = bl3 = mouseX >= this.x && mouseX <= this.x + this.width && mouseY > this.y - f8 && mouseY < this.y + f7 - f8;
            if (this.hovering) {
                if (this.position != this.oldTranslateY && this.oldTranslateY != f7 / 2.0f && this.oldTranslateY != f7 / 2.0f + -this.scrollAmount + f3) {
                    if (mouseY > this.y + f7 - f7 / 4.0f - f8) {
                        this.position -= f5 / 7.0f;
                    } else if (mouseY < this.y + f7 / 4.0f - f8) {
                        this.position += f5 / 7.0f;
                    }
                    this.oldTranslateY = this.position;
                } else if (mouseY > this.y + f7 - f7 / 4.0f - f8 || mouseY < this.y + f7 / 4.0f - f8) {
                    this.oldTranslateY = 1.0f;
                }
            }
            if (this.position < -this.scrollAmount + f3) {
                this.position = -this.scrollAmount + f3;
                this.internalScrollAmount = 0.0;
            }
            if (this.position > 0.0f) {
                this.position = 0.0f;
                this.internalScrollAmount = 0.0;
            }
            RenderUtil.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, -13158601);
            RenderUtil.drawRect(this.x, this.y - f8, this.x + this.width, this.y + f7 - f8, hovering && (bl3 || Mouse.isButtonDown(0) && this.buttonHeld) ? -4180940 : -52429);
        }
        if (!bl2 && this.position != 0.0f) {
            this.position = 0.0f;
        }
    }

    @Override
    public void handleElementMouse() {
        int n = Mouse.getEventDWheel();
        if (n != 0 && this.scrollAmount >= this.height) {
            this.internalScrollAmount += (double)(n / 3);
        }
    }

    public void setTextOffset(float f) {
        this.position = f;
    }

    public void setScrollAmount(float f) {
        this.scrollAmount = f;
    }

    public boolean isHovering() {
        return this.hovering;
    }

    public boolean isButtonHeld() {
        return this.buttonHeld;
    }
}
