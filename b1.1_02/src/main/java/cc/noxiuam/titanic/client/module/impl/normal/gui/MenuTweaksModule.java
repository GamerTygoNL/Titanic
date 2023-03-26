package cc.noxiuam.titanic.client.module.impl.normal.gui;

import cc.noxiuam.titanic.Ref;
import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.module.data.setting.impl.KeybindSetting;
import cc.noxiuam.titanic.client.module.data.setting.impl.MultiOptionSetting;
import cc.noxiuam.titanic.client.module.data.setting.impl.StringSetting;
import cc.noxiuam.titanic.event.impl.font.DrawStringEvent;
import cc.noxiuam.titanic.event.impl.gui.DebugDrawEvent;
import cc.noxiuam.titanic.event.impl.gui.MainMenuLogoDrawEvent;
import cc.noxiuam.titanic.event.impl.keyboard.KeyboardEvent;
import net.minecraft.src.Tessellator;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

/**
 * Makes it so the debug info is togglable instead of holding the key down.
 */
public class MenuTweaksModule extends AbstractModule {

    public boolean showDebugInfo = false;

    private final KeybindSetting debugKeybind;
    private final StringSetting watermarkString;
    private final MultiOptionSetting mainMenuLogo;

    public MenuTweaksModule() {
        super("menuTweaks", "Menu Tweaks", true);

        this.initSettings(
                this.debugKeybind = new KeybindSetting("debugKeybind", "Toggle Debug Info", Keyboard.KEY_F3),
                this.mainMenuLogo = new MultiOptionSetting(
                        "mainMenuLogo",
                        "Main Menu Logo",
                        "Classic",
                        "Classic", "Modern"
                ),
                this.watermarkString = new StringSetting("watermarkString", "Watermark String", Ref.MC_VERSION, 35)
        );

        this.addEvent(KeyboardEvent.class, this::keyTyped);
        this.addEvent(DebugDrawEvent.class, this::onDebugDraw);
        this.addEvent(MainMenuLogoDrawEvent.class, this::onMainMenuLogoDraw);
        this.addEvent(DrawStringEvent.class, event -> {
            if (event.getString().equalsIgnoreCase(Ref.MC_VERSION)) {
                event.cancel();
                event.setString(this.watermarkString.value());
            }
        });
    }

    private void onMainMenuLogoDraw(MainMenuLogoDrawEvent event) {

        if (this.mainMenuLogo.value().equalsIgnoreCase("Modern")) {
            event.cancel();

            Tessellator var4 = Tessellator.instance;
            short var5 = 274;
            int var6 = event.getMainMenu().width / 2 - var5 / 2;
            byte var7 = 30;
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/title/mclogo.png"));
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            event.getMainMenu().drawTexturedModalRect(var6 + 0, var7 + 0, 0, 0, 155, 44);
            event.getMainMenu().drawTexturedModalRect(var6 + 155, var7 + 0, 0, 45, 155, 44);
            var4.setColorOpaque_I(16777215);
        }

    }

    private void onDebugDraw(DebugDrawEvent event) {
        if (this.showDebugInfo) {
            event.cancel();
        }
    }

    private void keyTyped(KeyboardEvent event) {
        int key = event.getKey();

        // Game Debug (F3)
        if (key == this.debugKeybind.value()) {
            this.showDebugInfo = !this.showDebugInfo;
        }
    }

}
