package cc.noxiuam.titanic.client.ui.impl.editor;

import cc.noxiuam.titanic.Ref;
import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.module.impl.hud.AbstractMovableModule;
import cc.noxiuam.titanic.client.ui.fade.impl.ColorFade;
import cc.noxiuam.titanic.client.ui.impl.GuiScreenWrapper;
import cc.noxiuam.titanic.client.ui.component.type.button.RoundedIconButton;
import cc.noxiuam.titanic.client.ui.util.FontUtil;
import cc.noxiuam.titanic.client.ui.util.RenderUtil;
import cc.noxiuam.titanic.client.util.chat.ChatColor;
import cc.noxiuam.titanic.client.util.sound.SoundUtil;
import net.minecraft.client.Minecraft;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class HudLayoutEditor extends GuiScreenWrapper {

    private final Minecraft mc = Ref.getMinecraft();
    private final List<AbstractMovableModule> hudModules = new CopyOnWriteArrayList<>();

    private final RoundedIconButton modsButton = new RoundedIconButton("/titanic/pencil.png", true, 17, 17, 4, 4);

    private AbstractMovableModule selectedModule;

    private float xOffset;
    private float yOffset;

    public HudLayoutEditor() {
        for (AbstractModule module : Ref.getModuleManager().getMods()) {
            if (module instanceof AbstractMovableModule) {
                this.hudModules.add((AbstractMovableModule) module);

                if (((AbstractMovableModule) module).x() < -12) {
                    ((AbstractMovableModule) module).x(-12);
                }

                if (((AbstractMovableModule) module).y() < 0) {
                    ((AbstractMovableModule) module).y(0);
                }
            }
        }
    }

    @Override
    public void initGui() {
        super.initGui();
        Ref.getConfigManager().saveConfigs();
        this.modsButton.size(25, 25);
        this.modsButton.position(this.width / 2.0F - 12, this.height / 2.0F - 12);
    }

    @Override
    public void drawScreen(int x, int y) {
        if (this.selectedModule != null && this.selectedModule.enabled()) {
            this.selectedModule.setPosition(x - this.xOffset, y - this.yOffset);
        }

        for (AbstractMovableModule module : this.hudModules) {

            if (!module.enabled()) continue;

            // Draw an overlay rectangle if you're hovering over the mod
            if (module.mouseInside(x, y)) {
                RenderUtil.drawRect(
                        module.x(),
                        module.y(),
                        module.x() + module.width(),
                        module.y() + module.height(),
                        0x7000C2FF
                );
            }

            // Draw an outline around each of them
            RenderUtil.drawRoundedOutline(
                    module.x(),
                    module.y(),
                    module.x() + module.width(),
                    module.y() + module.height(),
                    0,
                    3,
                    0xFF00C2FF
            );
        }

        // mod settings button
        this.modsButton.draw(x, y);

        // Indicator text
        String blueText = "HUD";
        String indicator = "You are currently editing the";
        this.mc.fontRenderer.drawStringWithShadow(blueText + ChatColor.WHITE + ".",
                this.width / 2 + this.mc.fontRenderer.getStringWidth(indicator) / 2 - 4, this.height / 2 + 21, 0xFF00C2FF);

        FontUtil.drawCenteredString(indicator, this.width / 2 - this.mc.fontRenderer.getStringWidth(blueText) / 2,
                this.height / 2 + 21, -1);
    }

    @Override
    protected void mouseMovedOrUp(int i, int j, int k) {
        if (k == 0) {
            this.selectedModule = null;
        }
    }

    @Override
    protected void mouseClicked(int x, int y, int button) {
        if (this.modsButton.mouseInside(x, y)) {
            SoundUtil.playClick();
            this.mc.displayGuiScreen(new ModSettingsEditor());
        }

        if (button != 0) {
            return;
        }

        for (AbstractMovableModule module : this.hudModules) {
            if (module.mouseInside(x, y)) {
                this.selectedModule = module;
                this.xOffset = x - module.x();
                this.yOffset = y - module.y();
            }
        }
    }

}
