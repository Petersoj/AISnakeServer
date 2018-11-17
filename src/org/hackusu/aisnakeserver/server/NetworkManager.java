package org.hackusu.aisnakeserver.server;

import org.hackusu.aisnakeserver.manager.EntityManager;
import org.hackusu.aisnakeserver.packet.PacketServerUpdate;

import java.util.ArrayList;

public class NetworkManager {

    private EntityManager entityManager;

    private ServerSocketManager serverSocketManager;
    private ArrayList<ClientConnection> clientConnections;
    private final int PORT = 42087;

    public NetworkManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.clientConnections = new ArrayList<>();
    }

    public void createServer() {
        this.serverSocketManager = new ServerSocketManager(this, PORT);
        this.serverSocketManager.start();
    }

    public void updateClients() {
        for (ClientConnection clientConnection : clientConnections) {
            PacketServerUpdate packetServerUpdate = new PacketServerUpdate(entityManager.getEntities());
            clientConnection.writePacket(packetServerUpdate);
        }
    }

    public ArrayList<ClientConnection> getClientConnections() {
        return clientConnections;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
