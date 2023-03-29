package cc.noxiuam.titanic.client.ui.component.type.setting.impl.slider;

import cc.noxiuam.titanic.client.module.data.setting.impl.NumberSetting;
import cc.noxiuam.titanic.client.ui.component.type.setting.AbstractSettingComponent;
import cc.noxiuam.titanic.client.ui.module.component.ModuleSettingsComponent;

public class NumericalSliderComponent extends AbstractSettingComponent<Number> {

    private final SliderComponent slider;

    public NumericalSliderComponent(NumberSetting setting, ModuleSettingsComponent list) {
        super(setting, list);
        this.slider = new SliderComponent(setting);
    }

    @Override
    public void draw(float x, float y) {
        this.mc.fontRenderer.drawStringWithShadow(
                this.setting.name(),
                (int) (this.x + 2.0F),
                (int) (this.y),
                -1
        );

        this.slider.position(this.x + this.width - 73, this.y);
        this.slider.size(75, 20);
        this.slider.draw(x, y);
    }

    @Override
    public void mouseClicked(float x, float y) {
        this.slider.mouseClicked(x, y);
    }

    @Override
    public float getHeight() {
        return 14F;
    }

}
