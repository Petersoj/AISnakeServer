package org.hackusu.aisnakeserver.server;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketManager extends Thread {

    private NetworkManager networkManager;

    private int port;
    private ServerSocket serverSocket;

    public ServerSocketManager(NetworkManager networkManager, int port) {
        this.networkManager = networkManager;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            this.serverSocket = new ServerSocket(port);

            System.out.println("Started server on port: " + port);

            while (serverSocket.isBound()) {
                Socket socket = this.serverSocket.accept();
                ClientConnection clientConnection = new ClientConnection(networkManager, socket);
                this.networkManager.getClientConnections().add(clientConnection);
                clientConnection.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
