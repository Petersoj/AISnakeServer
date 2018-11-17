package me.braysen.goodwin.network.packet;

import me.braysen.goodwin.game.entities.Entity;
import me.braysen.goodwin.game.entities.Snake;

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


        // Write Entities list size
        objectOutputStream.writeInt(entities.size());

        // Write Entities
        for (Entity entity : entities) {
            objectOutputStream.writeObject(entity);
        }
    }

    @Override
    public void readPacket(ObjectInputStream objectInputStream) throws Exception {
        PacketSnake packetSnake = new PacketSnake(null);
        packetSnake.readPacket(objectInputStream);
        this.snake = packetSnake.getSnake();
    }

    public Snake getSnake() {
        return snake;
    }
}
