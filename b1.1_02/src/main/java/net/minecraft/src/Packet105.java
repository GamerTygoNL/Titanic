package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet105 extends Packet {

    public int field_20032_a;
    public int field_20031_b;
    public int field_20033_c;

    public Packet105() {
    }

    public void processPacket(NetHandler nethandler) {
        nethandler.func_20090_a(this);
    }

    public void readPacketData(DataInputStream datainputstream) throws IOException {
        field_20032_a = datainputstream.readByte();
        field_20031_b = datainputstream.readShort();
        field_20033_c = datainputstream.readShort();
    }

    public void writePacketData(DataOutputStream dataoutputstream) throws IOException {
        dataoutputstream.writeByte(field_20032_a);
        dataoutputstream.writeShort(field_20031_b);
        dataoutputstream.writeShort(field_20033_c);
    }

    public int getPacketSize() {
        return 5;
    }
}
