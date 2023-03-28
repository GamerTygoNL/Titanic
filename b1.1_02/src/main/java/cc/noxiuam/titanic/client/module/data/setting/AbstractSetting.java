package cc.noxiuam.titanic.client.module.data.setting;

import cc.noxiuam.titanic.client.ui.module.component.ModuleSettingsComponent;
import cc.noxiuam.titanic.client.ui.component.type.setting.AbstractSettingComponent;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Objects;
import java.util.function.Consumer;

@Getter
@ToString
@Accessors(fluent = true)
public abstract class AbstractSetting<T> {

    private final String id;
    private final String name;

    private final T defaultValue;
    private T value;

    @Setter private Consumer<Object> onUpdate;

    public AbstractSetting(String id, String name, T defaultValue) {
        this.id = id;
        this.name = name;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
    }

    public abstract AbstractSettingComponent<T> getComponent(ModuleSettingsComponent list);

    public void value(T newValue) {
        if (!Objects.equals(this.value, newValue)) {
            this.value = newValue;

            if (this.onUpdate != null) {
                this.onUpdate.accept(value);
            }
        }
    }

}
