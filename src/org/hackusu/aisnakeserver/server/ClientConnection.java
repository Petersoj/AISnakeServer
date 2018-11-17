package org.hackusu.aisnakeserver.server;

import me.braysen.goodwin.game.entities.Entity;
import me.braysen.goodwin.game.entities.Snake;
import me.braysen.goodwin.network.packet.Packet;
import me.braysen.goodwin.network.packet.PacketSnake;

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
    private Snake clientSnake;

    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public ClientConnection(NetworkManager networkManager, Socket socket) {
        this.networkManager = networkManager;
        this.socket = socket;
        this.entities = networkManager.getEntityManager().getEntities();
    }

    @Override
    public void run() {
        try {
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            this.dataInputStream = new DataInputStream(socket.getInputStream());

            this.objectOutputStream = new ObjectOutputStream(dataOutputStream);
            this.objectInputStream = new ObjectInputStream(dataInputStream);
            this.objectOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (socket != null && !socket.isClosed() && socket.isConnected() && objectInputStream != null) {
            try {
//                PacketServerUpdate packetServerUpdate = new PacketServerUpdate(clientSnake);
//                packetServerUpdate.readPacket(objectInputStream);
//                this.clientSnake = packetServerUpdate.getSnake();
//                for (int i = 0; i < entities.size(); i++) {
//                    if (entities.get(i).getUUID().equals(clientSnake.getUUID())) {
//                        entities.set(i, clientSnake);
//                    }
//                }

                PacketSnake packetSnake = new PacketSnake(null);
                packetSnake.readPacket(objectInputStream);

                if (!entities.contains(packetSnake.getSnake())) {
                    entities.add(packetSnake.getSnake());
                } else {
                    for (int i = 0; i < entities.size(); i++) {
                        if (entities.get(i).getUUID().equals(packetSnake.getSnake().getUUID())) {
                            entities.set(i, packetSnake.getSnake());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public void writePacket(Packet packet) {
        try {
            packet.writePacket(objectOutputStream);
            objectOutputStream.reset();
            objectOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Snake getClientSnake() {
        return clientSnake;
    }
}
