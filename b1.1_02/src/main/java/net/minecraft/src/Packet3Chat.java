package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet3Chat extends Packet {

    public String message;

    public Packet3Chat() {
    }

    public Packet3Chat(String s) {
        message = s;
    }

    public void readPacketData(DataInputStream datainputstream) throws IOException {
        message = datainputstream.readUTF();
    }

    public void writePacketData(DataOutputStream dataoutputstream) throws IOException {
        dataoutputstream.writeUTF(message);
    }

    public void processPacket(NetHandler nethandler) {
        nethandler.handleChat(this);
    }

    public int getPacketSize() {
        return message.length();
    }
}
