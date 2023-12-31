package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet255KickDisconnect extends Packet {

    public String reason;

    public Packet255KickDisconnect() {
    }

    public Packet255KickDisconnect(String s) {
        reason = s;
    }

    public void readPacketData(DataInputStream datainputstream) throws IOException {
        reason = datainputstream.readUTF();
    }

    public void writePacketData(DataOutputStream dataoutputstream) throws IOException {
        dataoutputstream.writeUTF(reason);
    }

    public void processPacket(NetHandler nethandler) {
        nethandler.handleKickDisconnect(this);
    }

    public int getPacketSize() {
        return reason.length();
    }
}
