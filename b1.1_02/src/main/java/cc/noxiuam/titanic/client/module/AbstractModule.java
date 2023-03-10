package cc.noxiuam.titanic.client.module;

import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(fluent = true)
public abstract class AbstractModule {

    private final String id;
    private final String name;

    public boolean enabled;
    public final boolean enabledByDefault;

    public AbstractModule(String id, String name, boolean enabledByDefault) {
        this.id = id;
        this.name = name;
        this.enabledByDefault = enabledByDefault;
        this.initialize();
    }

    /**
     * Handles toggling of the module.
     */
    public void toggle() {
        this.enabled = !this.enabled;
        this.onToggle();

        if (this.enabled) {
            this.onEnable();
        } else {
            this.onDisable();
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

}
