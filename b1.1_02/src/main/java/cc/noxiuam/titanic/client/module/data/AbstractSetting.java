package cc.noxiuam.titanic.client.module.data;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@ToString
@Accessors(fluent = true)
public abstract class AbstractSetting<T> {

    private final String id;
    private final String name;

    private final T defaultValue;
    @Setter private T value;

    // This should be used in most cases.
    public AbstractSetting(String id, String name, T defaultValue) {
        this.id = id;
        this.name = name;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
    }

}
