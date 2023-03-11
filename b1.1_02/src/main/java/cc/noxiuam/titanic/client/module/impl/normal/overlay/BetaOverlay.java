package cc.noxiuam.titanic.client.module.impl.normal.overlay;

import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.module.data.impl.KeybindSetting;
import cc.noxiuam.titanic.event.impl.gui.DebugDrawEvent;
import cc.noxiuam.titanic.event.impl.keyboard.KeyboardEvent;
import org.lwjgl.input.Keyboard;

public class BetaOverlay extends AbstractModule {

    public boolean showDebugInfo = false;

    private final KeybindSetting debugKeybind;

    public BetaOverlay() {
        super("betaOverlay", "Beta Overlay", true);

        initSettings(
                debugKeybind = new KeybindSetting("debugKeybind", "Toggle Debug Info", Keyboard.KEY_F3)
        );

        addEvent(KeyboardEvent.class, this::keyTyped);
        addEvent(DebugDrawEvent.class, this::onDebugDraw);
    }

    private void onDebugDraw(DebugDrawEvent event) {
        if (showDebugInfo) {
            event.cancel();
        }
    }

    private void keyTyped(KeyboardEvent event) {
        int key = event.getKey();

        // Game Debug (F3)
        if (key == Keyboard.KEY_F3) {
            showDebugInfo = !showDebugInfo;
        }
    }

}
