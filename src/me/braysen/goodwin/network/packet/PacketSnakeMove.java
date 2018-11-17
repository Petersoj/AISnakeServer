package me.braysen.goodwin.network.packet;

import me.braysen.goodwin.game.entities.Snake;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PacketSnakeMove extends Packet {

    private Snake snake;

    public PacketSnakeMove(Snake snake) {
        super(PacketName.SNAKE_MOVE);
        this.snake = snake;
    }

    @Override
    public void writePacket(ObjectOutputStream objectOutputStream) throws Exception {
        super.writePacket(objectOutputStream);

        // Server should never send a PacketSnakeMove
    }

    @Override
    public void readPacket(ObjectInputStream objectInputStream) throws Exception {
        super.readPacket(objectInputStream);

        // Read UUID
//        UUID snakeUUID = (UUID) objectInputStream.readObject();

        // Read Direction
//        objectOutputStream.writeObject(snake.getDirection());
    }
}
