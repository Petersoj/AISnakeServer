package org.hackusu.aisnakeserver.packet;

import org.hackusu.aisnakeserver.entity.Entity;
import org.hackusu.aisnakeserver.entity.Snake;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PacketServerUpdate extends Packet {

    private Snake snake;
    private ArrayList<Entity> entities;

    public PacketServerUpdate(Snake snake) {
        super(PacketName.SERVER_UPDATE);
        this.snake = snake;
    }

    public PacketServerUpdate(ArrayList<Entity> entities) {
        this((Snake) null);
        this.entities = entities;
    }

    @Override
    public void writePacket(ObjectOutputStream objectOutputStream) throws Exception {
        super.writePacket(objectOutputStream);

        PacketSnake packetSnake = new PacketSnake(snake);
        packetSnake.writePacket(objectOutputStream);
    }

    @Override
    public void readPacket(ObjectInputStream objectInputStream) throws Exception {
        super.readPacket(objectInputStream);

        // Read Entities list size
        int entitiesListSize = objectInputStream.readInt();

        // Read entities
        for (int i = 0; i < entitiesListSize; i++) {
            Entity entity = (Entity) objectInputStream.readObject();
            for (int j = 0; j < entitiesListSize; j++) {
                entities.set(j, entity);
            }
        }
    }
}
