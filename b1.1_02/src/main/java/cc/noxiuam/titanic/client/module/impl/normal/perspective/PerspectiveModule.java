package cc.noxiuam.titanic.client.module.impl.normal.perspective;

import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.module.data.setting.impl.BooleanSetting;
import cc.noxiuam.titanic.client.module.data.setting.impl.KeybindSetting;
import cc.noxiuam.titanic.client.module.data.setting.impl.NumberSetting;
import cc.noxiuam.titanic.event.impl.keyboard.KeyboardEvent;
import cc.noxiuam.titanic.event.impl.perspective.CameraChangeEvent;
import cc.noxiuam.titanic.event.impl.perspective.ViewBobbingSetupEvent;
import cc.noxiuam.titanic.event.impl.world.fov.PreFOVUpdateEvent;
import net.minecraft.src.EntityPlayerSP;
import net.minecraft.src.MathHelper;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class PerspectiveModule extends AbstractModule {

    public PerspectiveView currentPerspective = PerspectiveView.FIRST;
    private final KeybindSetting switchPerspectiveKey;
    private final BooleanSetting viewBobbingInThirdPerson;
    private final NumberSetting cameraFov/*, handFov*/;

    public PerspectiveModule() {
        super("perspectiveBundle", "Perspective", true);
        this.initSettings(
                this.switchPerspectiveKey = new KeybindSetting("switchPerspectiveKeybind", "Switch Perspective", Keyboard.KEY_R),
                this.viewBobbingInThirdPerson = new BooleanSetting("viewBobbingInThirdPerson", "3rd Person View Bobbing", false),
                this.cameraFov = new NumberSetting("cameraFov", "Camera FOV", 70, 50, 100, 0)/*,
                this.handFov = new NumberSetting("handFov", "Hand FOV", 70, 30, 120, 0)*/
        );

        this.addEvent(PreFOVUpdateEvent.class, this::updateFOV);
        this.addEvent(CameraChangeEvent.class, this::getModernCamera);
        this.addEvent(KeyboardEvent.class, this::updateCurrentPerspective);
        this.addEvent(ViewBobbingSetupEvent.class, this::onViewBob);
    }

    private void onViewBob(ViewBobbingSetupEvent event) {
        event.cancel();
        setupViewBobbing(event.getValue());
    }

    private void updateFOV(PreFOVUpdateEvent event) {
        event.setFov(this.cameraFov.value().floatValue());
    }

    private void setupViewBobbing(float f) {
        if (!this.viewBobbingInThirdPerson.value() && mc.gameSettings.thirdPersonView) {
            return;
        }

        EntityPlayerSP player = mc.thePlayer;
        float distanceWalked = player.distanceWalkedModified - player.prevDistanceWalkedModified;
        float multipliedWalked = player.distanceWalkedModified + distanceWalked * f;
        float f2 = player.field_775_e + (player.field_774_f - player.field_775_e) * f;
        float f3 = player.field_9329_Q + (player.field_9328_R - player.field_9329_Q) * f;
        GL11.glTranslatef(MathHelper.sin(multipliedWalked * 3.141593F) * f2 * 0.5F, -Math.abs(MathHelper.cos(multipliedWalked * 3.141593F) * f2), 0.0F);
        GL11.glRotatef(MathHelper.sin(multipliedWalked * 3.141593F) * f2 * 3F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(Math.abs(MathHelper.cos(multipliedWalked * 3.141593F + 0.2F) * f2) * 5F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(f3, 1.0F, 0.0F, 0.0F);
    }

    private void getModernCamera(CameraChangeEvent event) {
        event.cancel();

        if (currentPerspective == PerspectiveView.THIRD) {
            event.setF2(event.getF2() + 180F);
        }

        if (currentPerspective == PerspectiveView.THIRD) {
            GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
        }
    }

    private void updateCurrentPerspective(KeyboardEvent event) {
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

}