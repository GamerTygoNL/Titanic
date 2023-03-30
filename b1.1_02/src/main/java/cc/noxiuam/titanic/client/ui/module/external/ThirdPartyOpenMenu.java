package cc.noxiuam.titanic.client.ui.module.external;

import cc.noxiuam.titanic.Ref;
import cc.noxiuam.titanic.client.util.sound.SoundUtil;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;

public class ThirdPartyOpenMenu extends GuiScreen {

    @Override
    public void initGui() {
        super.initGui();
        controlList.add(new GuiButton(0, width / 2 - 100, height / 6 + 168, "Back to game"));
    }

    @Override
    public void drawScreen(int i, int j, float f) {
        drawDefaultBackground();
        drawCenteredString(fontRenderer, "Lock mode enabled", width / 2, (height / 4 - 60) + 20, -1);
        drawString(fontRenderer, "You currently have a third party executable open", width / 2 - 140, (height / 4 - 60) + 60, -1);
        drawString(fontRenderer, "while locked mode was enabled.", width / 2 - 140, (height / 4 - 60) + 60 + 12, -1);
        drawString(fontRenderer, "You may leave this screen at any time.", width / 2 - 140, (height / 4 - 60) + 60 + 50, -1);
        super.drawScreen(i, j, f);
    }

    @Override
    protected void actionPerformed(GuiButton guibutton) {
        if (guibutton.id == 0) {
            SoundUtil.playClick();
            Ref.getMinecraft().displayGuiScreen(null);
        }
    }

}
