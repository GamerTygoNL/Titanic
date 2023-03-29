package cc.noxiuam.titanic.client.ui.component.type.setting.impl.slider;

import cc.noxiuam.titanic.client.module.data.setting.impl.NumberSetting;
import cc.noxiuam.titanic.client.ui.component.AbstractComponent;
import cc.noxiuam.titanic.client.ui.fade.AbstractFade;
import cc.noxiuam.titanic.client.ui.fade.impl.ColorFade;
import cc.noxiuam.titanic.client.ui.fade.impl.GradualTransition;
import cc.noxiuam.titanic.client.ui.util.RenderUtil;
import net.minecraft.src.FontRenderer;
import org.lwjgl.input.Mouse;

public class SliderComponent extends AbstractComponent {

    private final AbstractFade ease = new GradualTransition(500L);

    private final ColorFade outlineColor = new ColorFade(0x00000000, 0xCCC2C2C2);
    private final ColorFade backgroundColor = new ColorFade(0x80000000, 0xBF000000);

    private Number numericalValue;
    private final NumberSetting setting;

    private boolean using = false;

    public SliderComponent(NumberSetting setting) {
        this.setting = setting;
        this.numericalValue = setting.value();

    }

    @Override
    public void draw(float x, float y) {

        float f3;
        float f4;
        float newValue;
        float finalX;
        float nextX;

        if (!Mouse.isButtonDown(0) && this.using) {
            this.using = false;
        }

        if (this.mouseInside(x, y) && Mouse.isButtonDown(0) && this.using) {
            if (this.ease.isOver()) {
                this.numericalValue = this.setting.value();
                this.ease.startAnimation();
            }

            float minValue = this.setting.getMinValue().floatValue();
            float maxValue = this.setting.getMaxValue().floatValue();

            nextX = x - this.x;

            finalX = nextX / this.width;
            newValue = minValue + finalX * (maxValue - minValue);
            if ((double) finalX <= 0.01) {
                newValue = minValue;
            } else if ((double) finalX >= 0.99) {
                newValue = maxValue;
            }
            this.setting.convertAndSet((double) Math.round((double) newValue * 100.0) / 100.0);
        }

        finalX = this.setting.getMinValue().floatValue();
        newValue = this.setting.getMaxValue().floatValue();

        float f10 = this.setting.value().floatValue();
        float f11 = (f10 - finalX) / (newValue - finalX);
        float f12 = this.width * f11;

        if (this.ease.isRunning()) {
            f4 = (float) (this.numericalValue.doubleValue() - (double) finalX) / (newValue - finalX);
            f3 = this.width * f11;
            float f13 = this.width * f4;
            float f14 = f13 - f3;
            f12 = f13 - f14 * this.ease.getFadeAmount();
        }

        FontRenderer fontRenderer = this.mc.fontRenderer;
        String string = (double) Math.round((double) this.setting.value().floatValue() * 100.0) / 100.0 + "";
        nextX = fontRenderer.getStringWidth(string);

        fontRenderer.drawStringWithShadow(
                string,
                (int) (this.x - 8.0f - nextX),
                (int) (this.y + this.height / 2.0f - (float) (17 / 2) - 2.0f),
                -1
        );

        f4 = this.height / 4.0f;
        RenderUtil.drawRoundedRect(
                this.x, this.y, this.x + this.width, this.y + this.height / 2.0f - f4 / 2.0f, 5F, 0x80000000
        );

        RenderUtil.drawRoundedOutline(
                this.x,
                this.y,
                this.x + this.width,
                this.y + this.height / 2.0f - f4 / 2.0f,
                5.0F,
                3.0F,
                this.outlineColor.getColor(this.mouseInside(x, y)).getRGB()
        );

        RenderUtil.drawRoundedRect(
                this.x, this.y, this.x + f12, this.y + this.height / 2.0f - f4 / 2.0f, 5F, 0xFF00C2FF
        );
    }

    @Override
    public float getHeight() {
        return 14F;
    }

    @Override
    public void mouseClicked(float x, float y) {
        if (this.mouseInside(x, y)) {
            this.using = true;
        }
    }

}
