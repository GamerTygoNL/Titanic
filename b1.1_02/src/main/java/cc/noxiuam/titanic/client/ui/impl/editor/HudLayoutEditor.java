package cc.noxiuam.titanic.client.ui.impl.editor;

import cc.noxiuam.titanic.Titanic;
import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.module.impl.hud.AbstractMovableModule;
import cc.noxiuam.titanic.client.ui.impl.GuiScreenWrapper;
import cc.noxiuam.titanic.client.ui.component.type.button.RoundedIconButton;
import cc.noxiuam.titanic.client.ui.impl.editor.component.HUDModuleComponent;
import cc.noxiuam.titanic.client.ui.util.FontUtil;
import cc.noxiuam.titanic.client.ui.util.RenderUtil;
import cc.noxiuam.titanic.client.util.MathUtil;
import cc.noxiuam.titanic.client.util.chat.ChatColor;
import cc.noxiuam.titanic.client.util.sound.SoundUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ScaledResolution;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class HudLayoutEditor extends GuiScreenWrapper {

    private final RoundedIconButton modsButton = new RoundedIconButton(
            "/titanic/pencil.png",
            true,
            17,
            17,
            4,
            4
    );
    private final Minecraft mc = Titanic.getInstance().getBridge().getMinecraftBridge().bridge$getMinecraft();

    private final List<AbstractMovableModule> hudModules = new CopyOnWriteArrayList<>();

    private AbstractMovableModule selectedModule;

    private float prevX;
    private float prevY;

    public HudLayoutEditor() {
        for (AbstractModule module : Titanic.getInstance().getModuleManager().getMods()) {
            if (module instanceof AbstractMovableModule) {
                hudModules.add((AbstractMovableModule) module);
                ((AbstractMovableModule) module).setPosition(10, 10);
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

        for (AbstractMovableModule module : this.hudModules) {
            if (module.mouseInside(x, y)) {
                if (Mouse.isButtonDown(0)) {
                    float dragX = (float) x - this.prevX;
                    float dragY = (float) y - this.prevY;

                    this.dragModule(module, dragX, dragY);
                }
            }
        }

        modsButton.draw(x, y);

        String blueText = "HUD";
        String indicator = "You are currently editing the";
        mc.fontRenderer.drawStringWithShadow(blueText + ChatColor.WHITE + ".", width / 2 + mc.fontRenderer.getStringWidth(indicator) / 2 - 4, height / 2 + 21, 0xFF00C2FF);
        FontUtil.drawCenteredString(indicator, width / 2 - mc.fontRenderer.getStringWidth(blueText) / 2, height / 2 + 21, -1);
    }

    private void dragModule(AbstractMovableModule module, float offsetX, float offsetY) {
        if (!module.enabled()) {
            return;
        }

        module.setPosition(offsetX, offsetY);
    }

    @Override
    protected void mouseMovedOrUp(int i, int j, int k) {
        this.selectedModule = null;
    }

    @Override
    protected void mouseClicked(int x, int y, int button) {
        if (this.modsButton.mouseInside(x, y)) {
            SoundUtil.playClick();
            mc.displayGuiScreen(new ModSettingsEditor());
        }

        this.prevX = x;
        this.prevY = y;

        for (AbstractMovableModule module : this.hudModules) {
            if (module.mouseInside(x, y)) {
                this.selectedModule = module;
            }
        }
    }

}
