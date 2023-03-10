package net.minecraft.src;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagFloat extends NBTBase {

    public float floatValue;

    public NBTTagFloat() {
    }

    public NBTTagFloat(float f) {
        floatValue = f;
    }

    void writeTagContents(DataOutput dataoutput) throws IOException {
        dataoutput.writeFloat(floatValue);
    }

    void readTagContents(DataInput datainput) throws IOException {
        floatValue = datainput.readFloat();
    }

    public byte getType() {
        return 5;
    }

    public String toString() {
        return String.valueOf(floatValue);
    }
}
