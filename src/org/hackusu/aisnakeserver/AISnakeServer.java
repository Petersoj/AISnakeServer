package org.hackusu.aisnakeserver;

import org.hackusu.aisnakeserver.manager.EntityManager;
import org.hackusu.aisnakeserver.server.NetworkManager;

import java.net.ServerSocket;
import java.net.Socket;

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

    private ServerSocket serverSocket;
    private Socket socket;

    public void start() {
//        new Thread() {
//            @Override
//            public void run() {
//                try {
//                    serverSocket = new ServerSocket(48839);
//                    Socket socket = serverSocket.accept();
//
//                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
//                    objectOutputStream.flush();
//
//
//                    Food food = new Food(0, 0, UUID.randomUUID());
//                    while (true) {
//                        Thread.sleep(100);
//                        int data = food.getX() + (int) (Math.random() * 100);
//                        food.setX(data);
//                        objectOutputStream.writeObject(food);
//                        objectOutputStream.reset();
//                        objectOutputStream.flush();
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//        new Thread() {
//            @Override
//            public void run() {
//                try {
//                    Socket socket = new Socket("127.0.0.1", 48839);
//
//                    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
//
//                    while (true) {
//                        Food food = (Food) objectInputStream.readObject();
//                        System.out.println(food.getX());
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();

        this.networkManager.createServer();
        this.serverLoop();
    }

    public void serverLoop() {
        while (true) {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            networkManager.updateClients();
        }
    }

}
