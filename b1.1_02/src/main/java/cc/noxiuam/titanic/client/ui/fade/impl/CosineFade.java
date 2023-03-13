package cc.noxiuam.titanic.client.ui.fade.impl;

public class CosineFade extends FloatFade {

    public CosineFade(long duration) {
        super(duration, 0.0f);
    }

    @Override
    protected float getValue() {
        float value = super.getValue();
        float f2 = value * 2.0f - 1.0f;
        return (float) (Math.cos((double) f2 * Math.PI) + 1.0) / 2.0f;
    }

}
