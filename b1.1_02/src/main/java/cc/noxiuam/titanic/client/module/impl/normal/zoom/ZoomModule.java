package cc.noxiuam.titanic.client.module.impl.normal.zoom;

import org.lwjgl.input.Keyboard;

import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.module.data.setting.impl.BooleanSetting;
import cc.noxiuam.titanic.client.module.data.setting.impl.KeybindSetting;
import cc.noxiuam.titanic.event.impl.keyboard.KeyDownEvent;
import cc.noxiuam.titanic.event.impl.keyboard.KeyUpEvent;
import cc.noxiuam.titanic.event.impl.mouse.ScrollEvent;
import cc.noxiuam.titanic.event.impl.world.FovEvent;

public class ZoomModule extends AbstractModule {

    private boolean yes;
    private int much;
    private final KeybindSetting zoomKey;
    private final BooleanSetting scrolling;

    public ZoomModule() {
        super("zoom", "Zoom", true);
        this.initSettings(this.zoomKey = new KeybindSetting("zoom", "Key", Keyboard.KEY_C),
                this.scrolling = new BooleanSetting("scrolling", "Adjust with scroll wheel", true));
        this.addEvent(FovEvent.class, this::onFov);
        this.addEvent(KeyDownEvent.class, this::onKeyDown);
        this.addEvent(KeyUpEvent.class, this::onKeyUp);
        this.addEvent(ScrollEvent.class, this::onScroll);
    }

    private void onFov(FovEvent event) {
        if (!(yes && mc.currentScreen == null)) {
            return;
        }

        event.setFov(event.getFov() / much);
    }

    private void onKeyDown(KeyDownEvent event) {
        if (event.getKey() == zoomKey.value()) {
            yes = true;
            much = 4;
        }
    }

    private void onKeyUp(KeyUpEvent event) {
        if (event.getKey() == zoomKey.value()) {
            yes = false;
        }
    }

    private void onScroll(ScrollEvent event) {
        if (!scrolling.value())
            return;

        event.cancel();
        much += event.getD() / Math.abs(event.getD());

        if (much < 2)
            much = 2;
    }
}
