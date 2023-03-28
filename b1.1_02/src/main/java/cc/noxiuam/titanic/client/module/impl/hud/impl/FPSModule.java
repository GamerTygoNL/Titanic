package cc.noxiuam.titanic.client.module.impl.hud.impl;

import cc.noxiuam.titanic.Ref;
import cc.noxiuam.titanic.client.module.data.setting.impl.BooleanSetting;
import cc.noxiuam.titanic.client.module.impl.hud.AbstractMovableModule;
import cc.noxiuam.titanic.client.ui.util.FontUtil;
import cc.noxiuam.titanic.client.ui.util.RenderUtil;
import cc.noxiuam.titanic.event.impl.gui.GuiDrawEvent;
import org.apache.commons.lang3.StringUtils;

public class FPSModule extends AbstractMovableModule {

    private final BooleanSetting showBackground;

    public FPSModule() {
        super("fps", "FPS", false);

        this.initSettings(
                this.color(),
                this.showBackground = new BooleanSetting("showBackground", "Show Background", true)
        );

        this.addEvent(GuiDrawEvent.class, this::draw);
    }

    private void draw(GuiDrawEvent event) {
        String text = this.getPrefixedTextColor() + StringUtils.substringBefore(Ref.getMinecraft().debug, " fps, ") + " FPS";

        if (!this.showBackground.value()) {
            text = "[" + text + "]";
        }

        if (this.showBackground.value()) {
            this.setSize(56, 18);
            RenderUtil.drawRect(
                    this.x(),
                    this.y(),
                    this.x() + this.width(),
                    this.y() + this.height(),
                    0x6F000000
            );
        } else {
            this.setSize(this.mc.fontRenderer.getStringWidth(text) + 5, 13);
        }

        FontUtil.drawCenteredString(
                text,
                (int) (this.x() + this.width() / 2F) + 1,
                (int) this.y() + (this.showBackground.value() ? 6 : 3),
                this.getChromaColor()
        );
    }

}
