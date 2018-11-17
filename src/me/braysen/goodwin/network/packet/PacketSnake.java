package me.braysen.goodwin.network.packet;

import me.braysen.goodwin.game.entities.Snake;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PacketSnake extends Packet {

    private Snake snake;

    public PacketSnake(Snake snake) {
        super(PacketName.SNAKE);
        this.snake = snake;
    }

    @Override
    public void writePacket(ObjectOutputStream objectOutputStream) throws Exception {
        super.writePacket(objectOutputStream);

        objectOutputStream.writeObject(snake);
    }

    @Override
    public void readPacket(ObjectInputStream objectInputStream) throws Exception {
        super.readPacket(objectInputStream);

        this.snake = (Snake) objectInputStream.readObject();
    }

    public Snake getSnake() {
        return snake;
    }

    //    public PacketSnake(ArrayList<Snake> snakes) {
//        this((Snake) null);
//        this.snakes = snakes;
//    }
//
//    @Override
//    public void writePacket(DataOutputStream dataOutputStream) throws IOException {
//        super.writePacket(dataOutputStream);
//
//        // Write UUID
//        UUID snakeUUID = snake.getUUID();
//        dataOutputStream.writeLong(snakeUUID.getMostSignificantBits());
//        dataOutputStream.writeLong(snakeUUID.getLeastSignificantBits());
//
//        // Write x y
//        dataOutputStream.writeInt(snake.getX());
//        dataOutputStream.writeInt(snake.getY());
//
//        // Write Direction
//        dataOutputStream.writeByte(snake.getDirection().getDataCode());
//
//        // Write Trail
//        ArrayList<Point> snakeTrail = snake.getTrail();
//
//        dataOutputStream.writeInt(snakeTrail.size()); // Write length of snake's x and y map.
//        for (Point point : snakeTrail) {
//            dataOutputStream.writeInt(point.x);
//            dataOutputStream.writeInt(point.y);
//        }
//    }
//
//    @Override
//    public void readPacket(DataInputStream dataInputStream) throws IOException {
//        super.readPacket(dataInputStream);
//
//        // Read UUID
//        long mostSigBits = dataInputStream.readLong();
//        long leastSigBits = dataInputStream.readLong();
//        UUID snakeUUID = new UUID(mostSigBits, leastSigBits);
//
//        for (Snake snake : snakes) {
//            if (snake.getUUID().equals(snakeUUID)) {
//                this.snake = snake;
//            }
//        }
//        if (snake == null) {
//            this.snake = new Snake(0, 0, snakeUUID);
//        }
//
//        // Read x y
//        snake.setX(dataInputStream.readInt());
//        snake.setY(dataInputStream.readInt());
//
//        // Read Direction
//        snake.setDirection(Snake.Direction.fromDataCode(dataInputStream.readByte()));
//
//        // Read Trail
//        ArrayList<Point> snakeTrail = new ArrayList<>();
//        this.snake.setTrail(snakeTrail);
//
//        int trailSize = dataInputStream.readByte(); // Read length of snake's x and y map.
//        for (int i = 0; i < trailSize; i++) {
//            int x = dataInputStream.readInt();
//            int y = dataInputStream.readInt();
//            snakeTrail.add(new Point(x, y));
//        }
//    }
//
//    public Snake getSnake() {
//        return snake;
//    }
}
