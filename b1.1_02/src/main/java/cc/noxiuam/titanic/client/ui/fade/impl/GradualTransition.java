package cc.noxiuam.titanic.client.ui.fade.impl;

public class GradualTransition extends FloatFade {

    public GradualTransition(long duration) {
        super(duration);
    }

    @Override
    protected float getValue() {
        float fadeValue = super.getValue();
        return (double) fadeValue <
                0.5 ? 2F * fadeValue * fadeValue : -1F
                + (4F - 2F * fadeValue) * fadeValue;
    }

}
