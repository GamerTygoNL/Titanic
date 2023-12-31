package cc.noxiuam.titanic.client.module;

import cc.noxiuam.titanic.Ref;
import cc.noxiuam.titanic.client.module.data.setting.AbstractSetting;
import cc.noxiuam.titanic.event.AbstractEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.minecraft.client.Minecraft;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

@Getter
@Setter
@Accessors(fluent = true)
public abstract class AbstractModule {

    protected final Minecraft mc = Ref.getMinecraft();

    private final List<AbstractSetting<?>> settings;

    private final Map<Class<? extends AbstractEvent>, Consumer> activeEvents = new HashMap<>();

    private final String id;
    private final String name;

    private boolean enabled;
    public final boolean enabledByDefault;

    public AbstractModule(String id, String name, boolean enabledByDefault) {
        this.id = id;
        this.name = name;
        this.enabledByDefault = enabledByDefault;
        if (enabledByDefault) {
            this.enabled = true;
        }
        this.settings = new CopyOnWriteArrayList<>();
        this.initialize();
    }

    protected <T extends AbstractEvent> void addEvent(Class<T> eventClass, Consumer<T> consumer) {
        this.activeEvents.put(eventClass, consumer);
    }

    public void addAllEvents() {
        for (Map.Entry<Class<? extends AbstractEvent>, Consumer> entry : this.activeEvents.entrySet()) {
            Ref.getEventManager().addEvent((Class<? extends AbstractEvent>) entry.getKey(), entry.getValue());
        }
    }

    protected void removeAllEvents() {
        for (Map.Entry<Class<? extends AbstractEvent>, Consumer> entry : this.activeEvents.entrySet()) {
            Ref.getEventManager().removeEvent((Class<? extends AbstractEvent>) entry.getKey(), entry.getValue());
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

    /**
     * In the event we need to write special configs for 3rd party mods.
     */
    public void writeModuleConfig() { }

}
