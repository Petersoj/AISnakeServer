package org.hackusu.aisnakeserver;

import org.hackusu.aisnakeserver.manager.EntityManager;
import org.hackusu.aisnakeserver.server.NetworkManager;

public class AISnakeServer {

    private NetworkManager networkManager;
    private EntityManager entityManager;

    public static void main(String[] args) {
        AISnakeServer aiSnakeServer = new AISnakeServer();
        aiSnakeServer.start();
    }

    public AISnakeServer() {
        this.entityManager = new EntityManager();
        this.networkManager = new NetworkManager(entityManager);
    }

    public void start() {
        this.networkManager.createServer();
        this.serverLoop();
    }

    public void serverLoop() {
        while (true) {
            try {
                Thread.sleep(25);
            } catch (Exception e) {
                e.printStackTrace();
            }
            networkManager.updateClients();
        }
    }

}
