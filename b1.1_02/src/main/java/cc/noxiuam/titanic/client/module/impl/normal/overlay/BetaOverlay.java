package cc.noxiuam.titanic.client.module.impl.normal.overlay;

import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.event.impl.keyboard.KeyboardEvent;
import org.lwjgl.input.Keyboard;

public class BetaOverlay extends AbstractModule {

    public boolean showDebugInfo = false;
    public boolean showGameOverlay = true;

    public BetaOverlay() {
        super("betaOverlay", "Beta Overlay", true);
        addEvent(KeyboardEvent.class, this::keyTyped);
    }

    private void keyTyped(KeyboardEvent event) {
        int key = event.getKey();

        // Game Overlay (F1)
        if (key == Keyboard.KEY_F1) {
            showGameOverlay = !showGameOverlay;
        }

        // Game Debug (F3)
        if (key == Keyboard.KEY_F3) {
            showDebugInfo = !showDebugInfo;
        }
    }

}
