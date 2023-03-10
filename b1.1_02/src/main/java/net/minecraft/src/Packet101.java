package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet101 extends Packet {

    public int field_20034_a;

    public Packet101() {
    }

    public Packet101(int i) {
        field_20034_a = i;
    }

    public void processPacket(NetHandler nethandler) {
        nethandler.func_20092_a(this);
    }

    public void readPacketData(DataInputStream datainputstream) throws IOException {
        field_20034_a = datainputstream.readByte();
    }

    public void writePacketData(DataOutputStream dataoutputstream) throws IOException {
        dataoutputstream.writeByte(field_20034_a);
    }

    public int getPacketSize() {
        return 1;
    }
}
