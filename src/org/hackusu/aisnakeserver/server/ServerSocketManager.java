package org.hackusu.aisnakeserver.server;

import java.net.ServerSocket;

public class ServerSocketManager extends Thread {

    private NetworkManager networkManager;

    private ServerSocket serverSocket;

    public ServerSocketManager(NetworkManager networkManager) {
        super();
        this.networkManager = networkManager;
    }

    @Override
    public void run() {

    }
}
