package net.minecraft.src;

class EnumOptionsMappingHelper {

    static final int[] field_20155_a; /* synthetic field */

    static {
        field_20155_a = new int[EnumOptions.values().length];
        try {
            field_20155_a[EnumOptions.INVERT_MOUSE.ordinal()] = 1;
        } catch (NoSuchFieldError nosuchfielderror) {
        }
        try {
            field_20155_a[EnumOptions.VIEW_BOBBING.ordinal()] = 2;
        } catch (NoSuchFieldError nosuchfielderror1) {
        }
        try {
            field_20155_a[EnumOptions.ANAGLYPH.ordinal()] = 3;
        } catch (NoSuchFieldError nosuchfielderror2) {
        }
        try {
            field_20155_a[EnumOptions.LIMIT_FRAMERATE.ordinal()] = 4;
        } catch (NoSuchFieldError nosuchfielderror3) {
        }
    }
}
