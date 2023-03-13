package cc.noxiuam.titanic.client.module.impl.normal.perspective;

import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.module.data.impl.BooleanSetting;
import cc.noxiuam.titanic.client.module.data.impl.KeybindSetting;
import cc.noxiuam.titanic.event.impl.keyboard.KeyboardEvent;
import cc.noxiuam.titanic.event.impl.perspective.CameraChangeEvent;
import cc.noxiuam.titanic.event.impl.perspective.ViewBobbingSetupEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class PerspectiveBundle extends AbstractModule {

    public PerspectiveView currentPerspective = PerspectiveView.FIRST;
    private final KeybindSetting switchPerspectiveKey;
    private final BooleanSetting viewBobbingInThirdPerson;

    public PerspectiveBundle() {
        super("perspectiveBundle", "Perspective", true);
        this.initSettings(
                switchPerspectiveKey = new KeybindSetting("switchPerspectiveKeybind", "Switch Perspective", Keyboard.KEY_R),
                viewBobbingInThirdPerson = new BooleanSetting("viewBobbingInThirdPerson", "3rd Person View Bobbing", false)
        );
        this.addEvent(CameraChangeEvent.class, this::getModernCamera);
        this.addEvent(KeyboardEvent.class, this::updateCurrentPerspective);
        this.addEvent(ViewBobbingSetupEvent.class, this::setupViewBobbing);
    }

    private void setupViewBobbing(ViewBobbingSetupEvent event) {
        if (this.viewBobbingInThirdPerson.value()
                || mc.gameSettings.thirdPersonView) {
            event.cancel();
        }
    }

    private void getModernCamera(CameraChangeEvent event) {
        event.cancel();

        if (currentPerspective == PerspectiveView.THIRD) {
            event.f2 += 180F;
        }

        if (currentPerspective == PerspectiveView.THIRD) {
            GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
        }
    }

    private void updateCurrentPerspective(KeyboardEvent event) {
        if (event.getKey() == Keyboard.KEY_F5) {
            event.cancel();
            return;
        }

        if (event.getKey() == switchPerspectiveKey.value()) {
            if (currentPerspective == PerspectiveView.FIRST) {
                currentPerspective = PerspectiveView.SECOND;
                mc.gameSettings.thirdPersonView = true;
            } else if (currentPerspective == PerspectiveView.SECOND) {
                currentPerspective = PerspectiveView.THIRD;
                mc.gameSettings.thirdPersonView = true;
            } else if (currentPerspective == PerspectiveView.THIRD) {
                currentPerspective = PerspectiveView.FIRST;
                mc.gameSettings.thirdPersonView = false;
            }
        }
    }

    /**
     * Used to help determine what perspective you're in.
     */
    enum PerspectiveView {
        FIRST,
        SECOND,
        THIRD
    }

}