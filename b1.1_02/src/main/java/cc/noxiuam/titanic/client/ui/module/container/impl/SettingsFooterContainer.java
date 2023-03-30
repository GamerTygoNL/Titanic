package cc.noxiuam.titanic.client.ui.module.container.impl;

import cc.noxiuam.titanic.Ref;
import cc.noxiuam.titanic.client.ui.component.type.button.LockButton;
import cc.noxiuam.titanic.client.ui.component.type.button.RoundedIconButton;
import cc.noxiuam.titanic.client.ui.module.container.AbstractContainer;
import cc.noxiuam.titanic.client.util.sound.SoundUtil;

public class SettingsFooterContainer extends AbstractContainer {

    private final RoundedIconButton backButton = new RoundedIconButton(
            "/titanic/back.png",
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

    private final LockButton lockButton = new LockButton();

    private final ModuleListContainer container;

    public SettingsFooterContainer(ModuleListContainer container) {
        super("/settings/footer");
        this.container = container;
        this.lockButton.locked = Ref.getModuleManager().getThirdPartyLoader().isLocked();
    }

    @Override
    public void draw(float x, float y) {
        this.backButton.position(this.x, this.y + this.height);
        this.backButton.size(20, 20);
        this.backButton.draw(x, y);

        this.lockButton.position(this.x + this.backButton.getWidth() + 5, this.y + this.height);
        this.lockButton.size(20, 20);
        this.lockButton.draw(x, y);

        this.closeButton.position(this.x + this.width - 20, this.y + this.height);
        this.closeButton.size(20, 20);
        this.closeButton.draw(x, y);
    }

    @Override
    public void mouseClicked(float x, float y) {
        if (this.closeButton.mouseInside(x, y)) {
            SoundUtil.playClick();
            this.mc.displayGuiScreen(null);
        } else if (this.backButton.mouseInside(x, y)) {
            SoundUtil.playClick();
            this.container.setCurrentComponent(new ModuleListContainer());
        } else if (this.lockButton.mouseInside(x, y)) {
            SoundUtil.playClick();
            this.lockButton.locked = !this.lockButton.locked;
            Ref.getModuleManager().getThirdPartyLoader().setLocked(this.lockButton.locked);
        }
    }

}
