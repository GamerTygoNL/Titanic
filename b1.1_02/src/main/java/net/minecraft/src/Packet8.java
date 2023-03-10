package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet8 extends Packet {

    public int healthMP;

    public Packet8() {
    }

    public void readPacketData(DataInputStream datainputstream) throws IOException {
        healthMP = datainputstream.readShort();
    }

    public void writePacketData(DataOutputStream dataoutputstream) throws IOException {
        dataoutputstream.writeShort(healthMP);
    }

    public void processPacket(NetHandler nethandler) {
        nethandler.handleHealth(this);
    }

    public int getPacketSize() {
        return 2;
    }
}
