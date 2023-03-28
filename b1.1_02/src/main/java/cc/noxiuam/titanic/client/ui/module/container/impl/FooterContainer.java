package cc.noxiuam.titanic.client.ui.module.container.impl;

import cc.noxiuam.titanic.client.ui.component.type.button.RoundedIconButton;
import cc.noxiuam.titanic.client.ui.module.component.ThirdPartyModsComponent;
import cc.noxiuam.titanic.client.ui.module.editor.HudLayoutEditor;
import cc.noxiuam.titanic.client.ui.module.container.AbstractContainer;
import cc.noxiuam.titanic.client.ui.module.container.impl.ModuleListContainer;
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

    private final RoundedIconButton closeButton = new RoundedIconButton(
            "/titanic/close.png",
            true,
            11,
            11,
            4.5F,
            4.5F
    );

    private final RoundedIconButton thirdPartyButton = new RoundedIconButton(
            "/titanic/thirdparty.png",
            true,
            11,
            11,
            4.5F,
            4.5F
    );

    private final ModuleListContainer container;

    public FooterContainer(ModuleListContainer container) {
        super("/footer");
        this.container = container;
    }

    @Override
    public void draw(float x, float y) {
        this.editHudButton.position(this.x, this.y + this.height);
        this.editHudButton.size(20, 20);
        this.editHudButton.draw(x, y);

        this.thirdPartyButton.position(this.x + this.editHudButton.getWidth() + 5, this.y + this.height);
        this.thirdPartyButton.size(20, 20);
        this.thirdPartyButton.draw(x, y);

        this.closeButton.position(this.x + this.width - 20, this.y + this.height);
        this.closeButton.size(20, 20);
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
        } else if (this.thirdPartyButton.mouseInside(x, y)) {
            SoundUtil.playClick();
            this.container.setCurrentComponent(new ThirdPartyModsComponent(this.container));
        }
    }

}
