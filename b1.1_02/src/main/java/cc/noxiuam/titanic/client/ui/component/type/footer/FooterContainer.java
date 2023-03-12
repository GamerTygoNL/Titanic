package cc.noxiuam.titanic.client.ui.component.type.footer;

import cc.noxiuam.titanic.client.ui.component.type.button.RoundedIconButton;
import cc.noxiuam.titanic.client.ui.component.type.button.RoundedTextButton;
import cc.noxiuam.titanic.client.ui.impl.editor.HudLayoutEditor;
import cc.noxiuam.titanic.client.ui.impl.module.container.AbstractContainer;
import cc.noxiuam.titanic.client.util.sound.SoundUtil;

public class FooterContainer extends AbstractContainer {

    private final RoundedIconButton editHudButton = new RoundedIconButton(
            "/titanic/pencil.png",
            true,
            11,
            11,
            4.5F,
            4.5F
    );

    private final RoundedTextButton closeButton = new RoundedTextButton("Close");

    public FooterContainer() {
        super("/footer");
    }

    @Override
    public void draw(float x, float y) {
        this.editHudButton.position(this.x, this.y + this.height);
        this.editHudButton.size(20, 20);
        this.editHudButton.draw(x, y);

        this.closeButton.position(this.x + this.width - 47, this.y + this.height);
        this.closeButton.size(189 / 4F, 75 / 4F);
        this.closeButton.draw(x, y);
    }

    @Override
    public void mouseClicked(float x, float y) {
        if (this.closeButton.mouseInside(x, y)) {
            SoundUtil.playClick();
            mc.displayGuiScreen(null);
        } else if (this.editHudButton.mouseInside(x, y)) {
            SoundUtil.playClick();
            mc.displayGuiScreen(new HudLayoutEditor());
        }
    }

}
