package me.braysen.goodwin.network.packet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class Packet implements Serializable {

    private PacketName packetName;

    public Packet(PacketName packetName) {
        this.packetName = packetName;
    }

    public void writePacket(ObjectOutputStream objectOutputStream) throws Exception {
        objectOutputStream.writeObject(packetName);
    }

    public void readPacket(ObjectInputStream objectInputStream) throws Exception {
        this.packetName = (PacketName) objectInputStream.readObject();
    }

    public enum PacketName implements Serializable {
        FOOD("f"),
        SERVER_UPDATE("su"),
        SNAKE("s"),
        SNAKE_MOVE("sm");

        private final String name;

        PacketName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}
