package cc.noxiuam.titanic.client.module;

import cc.noxiuam.titanic.client.Titanic;
import cc.noxiuam.titanic.client.module.data.AbstractSetting;
import cc.noxiuam.titanic.event.AbstractEvent;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

@Setter
@Accessors(fluent = true)
public abstract class AbstractModule {

    private final List<AbstractSetting<?>> settings;

    private final Map<Class<? extends AbstractEvent>, Consumer> activeEvents = new HashMap<>();

    private final String id;
    private final String name;

    public boolean enabled;
    public final boolean enabledByDefault;

    public AbstractModule(String id, String name, boolean enabledByDefault) {
        this.id = id;
        this.name = name;
        this.enabledByDefault = enabledByDefault;
        this.settings = new CopyOnWriteArrayList<>();
        this.initialize();
    }

    protected <T extends AbstractEvent> void addEvent(Class<T> eventClass, Consumer<T> consumer) {
        this.activeEvents.put(eventClass, consumer);
    }

    protected void addAllEvents() {
        for (Map.Entry<Class<? extends AbstractEvent>, Consumer> entry : this.activeEvents.entrySet()) {
            Titanic.getInstance()
                    .getEventManager()
                    .addEvent((Class<? extends AbstractEvent>) entry.getKey(), entry.getValue());
        }
    }

    protected void removeAllEvents() {
        for (Map.Entry<Class<? extends AbstractEvent>, Consumer> entry : this.activeEvents.entrySet()) {
            Titanic.getInstance()
                    .getEventManager()
                    .removeEvent((Class<? extends AbstractEvent>) entry.getKey(), entry.getValue());
        }
    }

    /**
     * Handles toggling of the module.
     */
    public void toggle() {
        this.enabled = !this.enabled;
        this.onToggle();

        if (this.enabled) {
            this.onEnable();
            this.addAllEvents();
        } else {
            this.onDisable();
            this.removeAllEvents();
        }
    }

    /**
     * What to do when a module is enabled.
     */
    public void onEnable() { }

    /**
     * What to do when a module is disabled.
     */
    public void onDisable() { }

    /**
     * What to do when a module is either enabled or disabled.
     */
    public void onToggle() { }

    /**
     * What to do on module initialization.
     */
    public void initialize() { }

    /**
     * Adds a new setting to the mod's list.
     * <p>
     * @param settings A list of settings, or just one.
     */
    public void initSettings(AbstractSetting<?>... settings) {
        Collections.addAll(this.settings, settings);
    }

}
