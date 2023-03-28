package cc.noxiuam.titanic.client.ui.fade.impl;

import cc.noxiuam.titanic.client.ui.fade.AbstractFade;

/**
 * @author - CheatBreaker, LLC
 */
public class FloatFade extends AbstractFade {

    public FloatFade(long duration) {
        super(duration, 1.0f);
    }

    @Override
    protected float getValue() {
        return (float) (this.duration - this.getTimeLeft()) / (float) this.duration;
    }

}
