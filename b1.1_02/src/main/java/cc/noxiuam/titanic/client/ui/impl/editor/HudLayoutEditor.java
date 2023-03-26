package cc.noxiuam.titanic.client.ui.impl.editor;

import cc.noxiuam.titanic.Ref;
import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.module.impl.hud.AbstractMovableModule;
import cc.noxiuam.titanic.client.ui.impl.GuiScreenWrapper;
import cc.noxiuam.titanic.client.ui.component.type.button.RoundedIconButton;
import cc.noxiuam.titanic.client.ui.util.FontUtil;
import cc.noxiuam.titanic.client.util.chat.ChatColor;
import cc.noxiuam.titanic.client.util.sound.SoundUtil;
import net.minecraft.client.Minecraft;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class HudLayoutEditor extends GuiScreenWrapper {

    private final RoundedIconButton modsButton = new RoundedIconButton("/titanic/pencil.png", true, 17, 17, 4, 4);

    private final Minecraft mc = Ref.getMinecraft();

    private final List<AbstractMovableModule> hudModules = new CopyOnWriteArrayList<>();

    private AbstractMovableModule selectedModule;

    private float xOffset;
    private float yOffset;

    public HudLayoutEditor() {
        for (AbstractModule module : Ref.getModuleManager().getMods()) {
            if (module instanceof AbstractMovableModule) {
                hudModules.add((AbstractMovableModule) module);

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
        modsButton.size(25, 25);
        modsButton.position(width / 2.0F - 12, height / 2.0F - 12);
    }

    @Override
    public void drawScreen(int x, int y) {
        if (selectedModule != null && selectedModule.enabled()) {
            selectedModule.setPosition(x - xOffset, y - yOffset);
        }

        modsButton.draw(x, y);

        String blueText = "HUD";
        String indicator = "You are currently editing the";
        mc.fontRenderer.drawStringWithShadow(blueText + ChatColor.WHITE + ".",
                width / 2 + mc.fontRenderer.getStringWidth(indicator) / 2 - 4, height / 2 + 21, 0xFF00C2FF);
        FontUtil.drawCenteredString(indicator, width / 2 - mc.fontRenderer.getStringWidth(blueText) / 2,
                height / 2 + 21, -1);
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
            mc.displayGuiScreen(new ModSettingsEditor());
        }

        if (button != 0) {
            return;
        }

        for (AbstractMovableModule module : this.hudModules) {
            if (module.mouseInside(x, y)) {
                this.selectedModule = module;
                xOffset = x - module.x();
                yOffset = y - module.y();
            }
        }
    }

}
