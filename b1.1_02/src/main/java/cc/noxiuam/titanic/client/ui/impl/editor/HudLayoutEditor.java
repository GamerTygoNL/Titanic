package cc.noxiuam.titanic.client.ui.impl.editor;

import cc.noxiuam.titanic.client.Titanic;
import cc.noxiuam.titanic.client.ui.impl.GuiScreenWrapper;
import cc.noxiuam.titanic.client.ui.component.type.button.RoundedIconButton;
import cc.noxiuam.titanic.client.ui.util.FontUtil;
import cc.noxiuam.titanic.client.util.chat.ChatColor;
import cc.noxiuam.titanic.client.util.sound.SoundUtil;
import net.minecraft.client.Minecraft;

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

    @Override
    public void initGui() {
        super.initGui();
        modsButton.size(25, 25);
        modsButton.position(width / 2.0F - 12, height / 2.0F - 12);
    }

    @Override
    public void drawScreen(int x, int y) {
        modsButton.draw(x, y);

        String blueText = "HUD";
        String indicator = "You are currently editing the";
        mc.fontRenderer.drawStringWithShadow(blueText + ChatColor.WHITE + ".", width / 2 + mc.fontRenderer.getStringWidth(indicator) / 2 - 4, height / 2 + 21, 0xFF00C2FF);
        FontUtil.drawCenteredString(indicator, width / 2 - mc.fontRenderer.getStringWidth(blueText) / 2, height / 2 + 21, -1);
    }

    @Override
    protected void mouseClicked(int i, int j, int k) {
        if (this.modsButton.mouseInside(i, j)) {
            SoundUtil.playClick();
            mc.displayGuiScreen(new ModSettingsEditor());
        }
    }

}
