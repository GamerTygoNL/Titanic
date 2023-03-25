package cc.noxiuam.titanic.client.module.impl.normal.overlay;

import cc.noxiuam.titanic.Ref;
import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.module.data.setting.impl.KeybindSetting;
import cc.noxiuam.titanic.client.module.data.setting.impl.StringSetting;
import cc.noxiuam.titanic.event.impl.font.DrawStringEvent;
import cc.noxiuam.titanic.event.impl.gui.DebugDrawEvent;
import cc.noxiuam.titanic.event.impl.keyboard.KeyboardEvent;
import org.lwjgl.input.Keyboard;

/**
 * Makes it so the debug info is togglable instead of holding the key down.
 */
public class OverlayModule extends AbstractModule {

    public boolean showDebugInfo = false;

    private final KeybindSetting debugKeybind;
    private final StringSetting watermarkString;

    public OverlayModule() {
        super("betaOverlay", "Overlay", true);

        this.initSettings(
                this.debugKeybind = new KeybindSetting("debugKeybind", "Toggle Debug Info", Keyboard.KEY_F3),
                this.watermarkString = new StringSetting("watermarkString", "Watermark String", Ref.MC_VERSION, 35)
        );

        this.addEvent(KeyboardEvent.class, this::keyTyped);
        this.addEvent(DebugDrawEvent.class, this::onDebugDraw);
        this.addEvent(DrawStringEvent.class, event -> {
            if (event.getString().equalsIgnoreCase(Ref.MC_VERSION)) {
                event.cancel();
                event.setString(this.watermarkString.value());
            }
        });
    }

    private void onDebugDraw(DebugDrawEvent event) {
        if (this.showDebugInfo) {
            event.cancel();
        }
    }

    private void keyTyped(KeyboardEvent event) {
        int key = event.getKey();

        // Game Debug (F3)
        if (key == Keyboard.KEY_F3) {
            this.showDebugInfo = !this.showDebugInfo;
        }
    }

}
