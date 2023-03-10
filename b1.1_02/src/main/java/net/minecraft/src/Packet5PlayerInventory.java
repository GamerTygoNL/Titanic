package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet5PlayerInventory extends Packet {

    public int type;
    public int stacks;
    public int field_20044_c;

    public Packet5PlayerInventory() {
    }

    public void readPacketData(DataInputStream datainputstream) throws IOException {
        type = datainputstream.readInt();
        stacks = datainputstream.readShort();
        field_20044_c = datainputstream.readShort();
    }

    public void writePacketData(DataOutputStream dataoutputstream) throws IOException {
        dataoutputstream.writeInt(type);
        dataoutputstream.writeShort(stacks);
        dataoutputstream.writeShort(field_20044_c);
    }

    public void processPacket(NetHandler nethandler) {
        nethandler.handlePlayerInventory(this);
    }

    public int getPacketSize() {
        return 8;
    }
}
