package cc.noxiuam.titanic.client.module.data.setting.impl;

import cc.noxiuam.titanic.client.module.data.setting.AbstractSetting;
import cc.noxiuam.titanic.client.ui.component.type.setting.AbstractSettingComponent;
import cc.noxiuam.titanic.client.ui.component.type.setting.impl.slider.NumericalSliderComponent;
import cc.noxiuam.titanic.client.ui.module.component.ModuleSettingsComponent;
import lombok.Getter;

@Getter
public class NumberSetting extends AbstractSetting<Number> {

    private final Number minValue;
    private final Number maxValue;

    private final int increment;

    public NumberSetting(String id, String name, Number defaultValue, Number minValue, Number maxValue, int increment) {
        super(id, name, defaultValue);
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.increment = increment;
    }

    @Override
    public AbstractSettingComponent<Number> getComponent(ModuleSettingsComponent list) {
        return new NumericalSliderComponent(this, list);
    }

    public void convertAndSet(Double var1) {
        this.setValueFrom(this.convertToNumber(var1));
    }

    public void setValueFrom(Number numIn) {
        int comparedMinValue = ((Comparable) numIn).compareTo(this.minValue);
        int comparedMaxValue = ((Comparable) numIn).compareTo(this.maxValue);

        if (comparedMinValue >= 0 && comparedMaxValue <= 0) {
            if (this.increment != 0 && numIn instanceof Float) {
                String stringifiedNumber = String.valueOf((float) Math.round(numIn.floatValue() * (float) this.increment) / (float) this.increment);
                float convertedValue = this.convertToNumber(Double.parseDouble(stringifiedNumber)).floatValue();

                if (!numIn.toString().equals(stringifiedNumber)) {
                    super.value(convertedValue);
                } else {
                    super.value(numIn);
                }
            } else {
                super.value(numIn);
            }
        }
    }

    private Number convertToNumber(Double valueBeingConverted) {
        Class<?> valueClass = this.value().getClass();
        return valueClass == Integer.class ? (Number) valueBeingConverted.intValue() : (valueClass == Float.class ? (Number) valueBeingConverted.floatValue() : (valueClass == Byte.class ? (Number) valueBeingConverted.byteValue() : (valueClass == Long.class ? (Number) valueBeingConverted.longValue() : (valueClass == Short.class ? (Number) valueBeingConverted.shortValue() : (Number) valueBeingConverted))));
    }

}
