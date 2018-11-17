package org.hackusu.aisnakeserver.server;

import org.hackusu.aisnakeserver.entity.Entity;
import org.hackusu.aisnakeserver.entity.Snake;
import org.hackusu.aisnakeserver.packet.Packet;
import org.hackusu.aisnakeserver.packet.PacketServerUpdate;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientConnection extends Thread {

    private NetworkManager networkManager;
    private ArrayList<Entity> entities;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public ClientConnection(NetworkManager networkManager, Socket socket) {
        super();
        this.networkManager = networkManager;
        this.socket = socket;
        this.entities = networkManager.getEntityManager().getEntities();
    }

    @Override
    public void run() {
        try {
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());

            this.objectInputStream = new ObjectInputStream(dataInputStream);
            this.objectOutputStream = new ObjectOutputStream(dataOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (socket != null && !socket.isClosed() && socket.isConnected()) {
            try {
                PacketServerUpdate packetServerUpdate = new PacketServerUpdate(
                        networkManager.getEntityManager().getEntities());
                packetServerUpdate.readPacket(objectInputStream);
                Snake newSnake = packetServerUpdate.getSnake();
                for (int i = 0; i < entities.size(); i++) {
                    if (entities.get(i).getUUID().equals(newSnake.getUUID())) {
                        entities.set(i, newSnake);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void writePacket(Packet packet) {
        try {
            packet.writePacket(objectOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
