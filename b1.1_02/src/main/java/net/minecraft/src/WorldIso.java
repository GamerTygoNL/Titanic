package net.minecraft.src;

import java.io.File;

class WorldIso extends World {

    final CanvasIsomPreview field_1051_z; /* synthetic field */

    WorldIso(CanvasIsomPreview canvasisompreview, File file, String s) {
        super(file, s);
        field_1051_z = canvasisompreview;
    }

    protected IChunkProvider func_4081_a(File file) {
        return new ChunkProviderIso(this, new ChunkLoader(file, false));
    }
}
