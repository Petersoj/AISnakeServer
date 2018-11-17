package org.hackusu.aisnakeserver.packet;

import org.hackusu.aisnakeserver.entity.Food;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PacketFood extends Packet {

    private Food food;

    public PacketFood() {
        super(PacketName.FOOD);
    }

    @Override
    public void writePacket(ObjectOutputStream objectOutputStream) throws Exception {
        super.writePacket(objectOutputStream);

        objectOutputStream.writeObject(food);
    }

    @Override
    public void readPacket(ObjectInputStream objectInputStream) throws Exception {
        super.readPacket(objectInputStream);

        this.food = (Food) objectInputStream.readObject();
    }
}
