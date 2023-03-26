package cc.noxiuam.titanic.client.module.impl.normal.zoom;

import org.lwjgl.input.Keyboard;

import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.module.data.setting.impl.KeybindSetting;
import cc.noxiuam.titanic.event.impl.keyboard.KeyDownEvent;
import cc.noxiuam.titanic.event.impl.keyboard.KeyUpEvent;
import cc.noxiuam.titanic.event.impl.world.FovEvent;

public class ZoomModule extends AbstractModule {

    public boolean yes;
    private final KeybindSetting zoomKey;

    public ZoomModule() {
        super("zoom", "Zoom", true);
        this.initSettings(
                this.zoomKey = new KeybindSetting("zoom", "Key", Keyboard.KEY_C)
        );
        this.addEvent(FovEvent.class, this::onFov);
        this.addEvent(KeyDownEvent.class, this::onKeyDown);
        this.addEvent(KeyUpEvent.class, this::onKeyUp);
    }

    private void onFov(FovEvent event) {
        if (!(yes && mc.currentScreen == null)) {
            return;
        }

        event.setFov(event.getFov() / 4);
    }

    private void onKeyDown(KeyDownEvent event) {
        if (event.getKey() == zoomKey.value()) {
            yes = true;
        }
    }

    private void onKeyUp(KeyUpEvent event) {
        if (event.getKey() == zoomKey.value()) {
            yes = false;
        }
    }

}
