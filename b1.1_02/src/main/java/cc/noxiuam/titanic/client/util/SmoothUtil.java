package cc.noxiuam.titanic.client.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

import cc.noxiuam.titanic.Ref;
import cc.noxiuam.titanic.event.impl.mouse.PlayerLookInputEvent;
import cc.noxiuam.titanic.event.impl.world.TickEvent;
import lombok.experimental.UtilityClass;

// ported from 1.8 :p
@UtilityClass
public class SmoothUtil {

    private List<BooleanSupplier> activators = new ArrayList<>();

    private float cursorDx;
    private float cursorDy;
    private float smoothedCursorDx;
    private float smoothedCursorDy;
    private float lastPartialTicks;
    private Axis x = new Axis();
    private Axis y = new Axis();

    public void inject() {
        Ref.getEventManager().addEvent(PlayerLookInputEvent.class, event -> {
            if (active()) {
                cursorDx += event.getDx();
                cursorDy += event.getDy();
                float tickProgression = event.getPartialTicks() - lastPartialTicks;
                lastPartialTicks = event.getPartialTicks();
                event.setDx(smoothedCursorDx * tickProgression);
                event.setDy(smoothedCursorDy * tickProgression);
            }
        });
        Ref.getEventManager().addEvent(TickEvent.class, event -> {
            if (active()) {
                float f = Ref.getMinecraft().gameSettings.mouseSensitivity * 0.6F + 0.2F;
                float g = f * f * f * 8.0F;
                smoothedCursorDx = x.smooth(cursorDx, 0.05F * g);
                smoothedCursorDy = y.smooth(cursorDy, 0.05F * g);
                lastPartialTicks = 0;
                cursorDx = 0;
                cursorDy = 0;
            } else {
                smoothedCursorDx = 0;
                smoothedCursorDy = 0;
                x.reset();
                y.reset();
            }
        });
    }

    public void activateWith(BooleanSupplier activator) {
        activators.add(activator);
    }

    private boolean active() {
        return activators.stream().anyMatch(BooleanSupplier::getAsBoolean);
    }

    private static class Axis {

        private float sum;
        private float smoothSum;
        private float latency;

        public float smooth(float original, float smooth) {
            this.sum += original;
            original = (this.sum - this.smoothSum) * smooth;
            this.latency += (original - this.latency) * 0.5F;
            if (original > 0 && original > this.latency || original < 0 && original < this.latency) {
                original = this.latency;
            }

            this.smoothSum += original;
            return original;
        }

        public void reset() {
            this.sum = 0;
            this.smoothSum = 0;
            this.latency = 0;
        }
    }
}
