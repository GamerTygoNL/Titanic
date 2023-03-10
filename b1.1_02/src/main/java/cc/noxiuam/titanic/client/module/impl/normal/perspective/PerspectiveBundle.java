package cc.noxiuam.titanic.client.module.impl.normal.perspective;

import cc.noxiuam.titanic.client.Titanic;
import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.module.data.impl.KeybindSetting;
import cc.noxiuam.titanic.event.impl.keyboard.KeyboardEvent;
import cc.noxiuam.titanic.event.impl.world.FovChangeEvent;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class PerspectiveBundle extends AbstractModule {

    public PerspectiveView currentPerspective = PerspectiveView.FIRST;
    private final KeybindSetting switchPerspectiveKeybind;

    public PerspectiveBundle() {
        super("perspectiveBundle", "Perspective", true);
        this.initSettings(
                switchPerspectiveKeybind = new KeybindSetting("switchPerspectiveKeybind", "Switch Perspective", Keyboard.KEY_R)
        );
        this.addEvent(FovChangeEvent.class, this::getModernFovModifier);
        this.addEvent(KeyboardEvent.class, this::updateCurrentPerspective);
    }

    private void getModernFovModifier(FovChangeEvent event) {
        event.cancel();

        if (currentPerspective == PerspectiveView.THIRD) {
            event.f2 += 180F;
        }

        if (currentPerspective == PerspectiveView.THIRD) {
            GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
        }

    }

    private void updateCurrentPerspective(KeyboardEvent event) {
        Minecraft mc = Titanic.getInstance().getBridge().getMinecraftBridge().bridge$getMinecraft();

        if (event.getKey() == switchPerspectiveKeybind.value()) {
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