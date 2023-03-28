package cc.noxiuam.titanic.client.module.impl.normal.perspective.zoom;

import org.lwjgl.input.Keyboard;

import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.module.data.setting.impl.BooleanSetting;
import cc.noxiuam.titanic.client.module.data.setting.impl.KeybindSetting;
import cc.noxiuam.titanic.client.util.SmoothUtil;
import cc.noxiuam.titanic.event.impl.mouse.ScrollEvent;
import cc.noxiuam.titanic.event.impl.world.FovEvent;
import cc.noxiuam.titanic.event.impl.world.TickEvent;

// thank you Ennui for your amazing mod! <3
// this has inspired all of my zoom implementations
public class ZoomModule extends AbstractModule {

    private boolean wasHeld;
    private float level = 1;

    private float expLevelStart;
    private float expLevelEnd;

    private final KeybindSetting zoomKey;
    private final BooleanSetting scrolling;
    private final BooleanSetting animated;
    private final BooleanSetting smooth;

    public ZoomModule() {
        super("zoom", "Zoom", false);
        this.initSettings(
                this.zoomKey = new KeybindSetting("zoom", "Key", Keyboard.KEY_C),
                this.scrolling = new BooleanSetting("scrolling", "Adjust with scroll wheel", true),
                this.animated = new BooleanSetting("animated", "Animated", true),
                this.smooth = new BooleanSetting("smooth", "Smooth Camera", true)
        );
        this.addEvent(TickEvent.class, this::onTick);
        this.addEvent(FovEvent.class, this::onFov);
        this.addEvent(ScrollEvent.class, this::onScroll);
        SmoothUtil.activateWith(() -> enabled() && smooth.value() && wasHeld);
    }

    private void onTick(TickEvent event) {
        // here we run an exponential transition at 20 fps simply because it's consistent across different framerates

        // swap
        expLevelStart = expLevelEnd;
        // progress towards the target
        expLevelEnd += (getTargetLevel() - expLevelEnd) * 0.85F /* woosh speed */;

        boolean held = Keyboard.isKeyDown(zoomKey.value());
        if (held && !wasHeld) {
            level = 1F / 4;
        } else if (!held && wasHeld) {
            level = 1;
        }
        wasHeld = held;
    }

    private void onFov(FovEvent event) {
        float outputLevel = getTargetLevel();
        if (animated.value()) {
            // interpolate for each frame to make it smoother
            // partial ticks is the progression from the current tick to the next
            // i.e. if the next tick is planned to occur in ticktime / 2 this would be 0.5
            outputLevel = expLevelStart + (expLevelEnd - expLevelStart) * event.getPartialTicks();
        }

        event.setFov(event.getFov() * outputLevel);
    }

    private void onScroll(ScrollEvent event) {
        if (!(scrolling.value() && wasHeld)) {
            return;
        }

        event.cancel();

        float factor = 1 / level;
        // converts the scroll to either -1 or 1
        factor += event.getD() / Math.abs(event.getD());
        level = 1 / factor;

        if (level > 0.5F) {
            level = 0.5F;
        }
    }

    public float getTargetLevel() {
        // if the user has a screen open then they probably aren't trying to zoom
        if (mc.currentScreen != null) {
            return 1;
        }

        return level;
    }
}
