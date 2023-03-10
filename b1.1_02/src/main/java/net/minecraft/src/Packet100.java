package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet100 extends Packet {

    public int field_20038_a;
    public int field_20037_b;
    public String field_20040_c;
    public int field_20039_d;

    public Packet100() {
    }

    public void processPacket(NetHandler nethandler) {
        nethandler.func_20087_a(this);
    }

    public void readPacketData(DataInputStream datainputstream) throws IOException {
        field_20038_a = datainputstream.readByte();
        field_20037_b = datainputstream.readByte();
        field_20040_c = datainputstream.readUTF();
        field_20039_d = datainputstream.readByte();
    }

    public void writePacketData(DataOutputStream dataoutputstream) throws IOException {
        dataoutputstream.writeByte(field_20038_a);
        dataoutputstream.writeByte(field_20037_b);
        dataoutputstream.writeUTF(field_20040_c);
        dataoutputstream.writeByte(field_20039_d);
    }

    public int getPacketSize() {
        return 3 + field_20040_c.length();
    }
}
