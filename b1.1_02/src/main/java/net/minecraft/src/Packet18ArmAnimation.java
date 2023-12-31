package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet18ArmAnimation extends Packet {

    public int entityId;
    public int animate;

    public Packet18ArmAnimation() {
    }

    public Packet18ArmAnimation(Entity entity, int i) {
        entityId = entity.field_620_ab;
        animate = i;
    }

    public void readPacketData(DataInputStream datainputstream) throws IOException {
        entityId = datainputstream.readInt();
        animate = datainputstream.readByte();
    }

    public void writePacketData(DataOutputStream dataoutputstream) throws IOException {
        dataoutputstream.writeInt(entityId);
        dataoutputstream.writeByte(animate);
    }

    public void processPacket(NetHandler nethandler) {
        nethandler.handleArmAnimation(this);
    }

    public int getPacketSize() {
        return 5;
    }
}
