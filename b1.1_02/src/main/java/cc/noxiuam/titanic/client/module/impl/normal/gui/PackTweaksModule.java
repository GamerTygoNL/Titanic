package cc.noxiuam.titanic.client.module.impl.normal.gui;

import cc.noxiuam.titanic.Ref;
import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.module.data.setting.impl.BooleanSetting;
import cc.noxiuam.titanic.client.module.data.setting.impl.KeybindSetting;
import cc.noxiuam.titanic.client.module.data.setting.impl.MultiOptionSetting;
import cc.noxiuam.titanic.client.module.data.setting.impl.StringSetting;
import cc.noxiuam.titanic.event.impl.font.DrawStringEvent;
import cc.noxiuam.titanic.event.impl.gui.DebugDrawEvent;
import cc.noxiuam.titanic.event.impl.gui.MainMenuLogoDrawEvent;
import cc.noxiuam.titanic.event.impl.gui.PortalOverlayDrawEvent;
import cc.noxiuam.titanic.event.impl.keyboard.KeyboardEvent;
import cc.noxiuam.titanic.event.impl.world.block.PortalOverlayEvent;
import cc.noxiuam.titanic.event.impl.world.player.OnLivingUpdateEvent;
import cc.noxiuam.titanic.event.impl.world.player.PlayerBlockCollideEvent;
import cc.noxiuam.titanic.event.impl.world.player.PlayerWorldChangeEvent;
import net.minecraft.src.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class PackTweaksModule extends AbstractModule {

    private final KeybindSetting debugKeybind;
    private final StringSetting watermarkString;
    private final BooleanSetting showPortalOverlay;
    private final MultiOptionSetting mainMenuLogo;
    
    private final Random rand = new Random();
    
    public boolean showDebugInfo = false;

    private boolean inPortal;

    private float timeInPortal;
    private float prevTimeInPortal;

    public PackTweaksModule() {
        super("packTweaks", "Pack Tweaks", false);

        this.initSettings(
                this.debugKeybind = new KeybindSetting("debugKeybind", "Toggle Debug Info", Keyboard.KEY_F3),
                this.mainMenuLogo = new MultiOptionSetting(
                        "mainMenuLogo",
                        "Main Menu Logo",
                        "Classic",
                        "Classic", "Modern"
                ),
                this.showPortalOverlay = new BooleanSetting("showPortalOverlay", "Show Portal Overlay", false),
                this.watermarkString = new StringSetting("watermarkString", "Watermark String", Ref.MC_VERSION, 35)
        );

        this.addEvent(KeyboardEvent.class, this::keyTyped);
        this.addEvent(DebugDrawEvent.class, this::onDebugDraw);
        this.addEvent(MainMenuLogoDrawEvent.class, this::onMainMenuLogoDraw);

        this.addEvent(OnLivingUpdateEvent.class, this::onLivingUpdate);
        this.addEvent(PlayerBlockCollideEvent.class, this::onBlockCollision);
        this.addEvent(PortalOverlayEvent.class, this::onPortalOverlay);
        this.addEvent(PortalOverlayDrawEvent.class, this::onPortalDraw);

        this.addEvent(DrawStringEvent.class, event -> {
            if (event.getString().equalsIgnoreCase(Ref.MC_VERSION)) {
                event.cancel();
                event.setString(this.watermarkString.value());
            }
        });
    }

    private void onLivingUpdate(OnLivingUpdateEvent event) {
        if (event.getPlayer() != this.mc.thePlayer) {
            return;
        }

        this.prevTimeInPortal = this.timeInPortal;

        if (this.inPortal) {
            if (this.timeInPortal == 0.0F) {
                this.mc.sndManager.func_337_a("portal.trigger", 1.0F, rand.nextFloat() * 0.4F + 0.8F);
            }
            this.timeInPortal += 0.0125F;

            if (this.timeInPortal >= 1.0F) {
                this.timeInPortal = 1.0F;
                this.inPortal = false;
            }
        } else {
            if (this.timeInPortal > 0.0F) {
                this.timeInPortal -= 0.05F;
            }

            if (this.timeInPortal < 0.0F) {
                this.timeInPortal = 0.0F;
            }
        }
    }

    private void onBlockCollision(PlayerBlockCollideEvent event) {
        if (event.getEntity() == this.mc.thePlayer && event.getBlock() instanceof BlockPortal) {
            this.inPortal = true;
        }
    }

    private void onPortalOverlay(PortalOverlayEvent event) {
        if (this.showPortalOverlay.value() && this.inPortal) {
            event.cancel();
        }
    }

    private void onPortalDraw(PortalOverlayDrawEvent event) {
        event.cancel();
        event.prevTimeInPortal = this.prevTimeInPortal;
        event.timeInPortal = this.timeInPortal;
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
