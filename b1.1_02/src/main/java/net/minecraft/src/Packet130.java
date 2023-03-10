package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet130 extends Packet {

    public int field_20020_a;
    public int field_20019_b;
    public int field_20022_c;
    public String[] field_20021_d;

    public Packet130() {
        isChunkDataPacket = true;
    }

    public Packet130(int i, int j, int k, String[] as) {
        isChunkDataPacket = true;
        field_20020_a = i;
        field_20019_b = j;
        field_20022_c = k;
        field_20021_d = as;
    }

    public void readPacketData(DataInputStream datainputstream) throws IOException {
        field_20020_a = datainputstream.readInt();
        field_20019_b = datainputstream.readShort();
        field_20022_c = datainputstream.readInt();
        field_20021_d = new String[4];
        for (int i = 0; i < 4; i++) {
            field_20021_d[i] = datainputstream.readUTF();
        }

    }

    public void writePacketData(DataOutputStream dataoutputstream) throws IOException {
        dataoutputstream.writeInt(field_20020_a);
        dataoutputstream.writeShort(field_20019_b);
        dataoutputstream.writeInt(field_20022_c);
        for (int i = 0; i < 4; i++) {
            dataoutputstream.writeUTF(field_20021_d[i]);
        }

    }

    public void processPacket(NetHandler nethandler) {
        nethandler.func_20093_a(this);
    }

    public int getPacketSize() {
        int i = 0;
        for (int j = 0; j < 4; j++) {
            i += field_20021_d[j].length();
        }

        return i;
    }
}
