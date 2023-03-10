package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet103 extends Packet {

    public int field_20042_a;
    public int field_20041_b;
    public ItemStack field_20043_c;

    public Packet103() {
    }

    public void processPacket(NetHandler nethandler) {
        nethandler.func_20088_a(this);
    }

    public void readPacketData(DataInputStream datainputstream) throws IOException {
        field_20042_a = datainputstream.readByte();
        field_20041_b = datainputstream.readShort();
        short word0 = datainputstream.readShort();
        if (word0 >= 0) {
            byte byte0 = datainputstream.readByte();
            byte byte1 = datainputstream.readByte();
            field_20043_c = new ItemStack(word0, byte0, byte1);
        } else {
            field_20043_c = null;
        }
    }

    public void writePacketData(DataOutputStream dataoutputstream) throws IOException {
        dataoutputstream.writeByte(field_20042_a);
        dataoutputstream.writeShort(field_20041_b);
        if (field_20043_c == null) {
            dataoutputstream.writeShort(-1);
        } else {
            dataoutputstream.writeShort(field_20043_c.itemID);
            dataoutputstream.writeByte(field_20043_c.stackSize);
            dataoutputstream.writeByte(field_20043_c.itemDamage);
        }
    }

    public int getPacketSize() {
        return 7;
    }
}
