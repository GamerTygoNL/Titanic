package net.minecraft.src;

public class StepSound {

    public final String field_1678_a;
    public final float field_1677_b;
    public final float field_1679_c;

    public StepSound(String s, float f, float f1) {
        field_1678_a = s;
        field_1677_b = f;
        field_1679_c = f1;
    }

    public float func_1147_b() {
        return field_1677_b;
    }

    public float func_1144_c() {
        return field_1679_c;
    }

    public String func_1146_a() {
        return (new StringBuilder()).append("step.").append(field_1678_a).toString();
    }

    public String func_1145_d() {
        return (new StringBuilder()).append("step.").append(field_1678_a).toString();
    }
}
