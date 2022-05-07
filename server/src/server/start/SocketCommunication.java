/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.start;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.net.Socket;
import server.client.HandleClientRequest;

/**
 *
 * @author Djina
 */
public class SocketCommunication extends Thread{
    private int port;
    ServerSocket serverSocket;
    ArrayList<HandleClientRequest> players;
    
    public SocketCommunication(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(port);
        this.players = new ArrayList<>();
    }
    
    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                System.out.println("Waiting for connection...");
                Socket socket = serverSocket.accept();
                System.out.println("New client has just connected!");
                HandleClientRequest handleClientRequest = new HandleClientRequest(socket);
                players.add(handleClientRequest);
                handleClientRequest.start();

            }
        } catch (Exception ex) {
            System.out.println("Server has been stopped!");
        }
    }

    public void stopServer() throws IOException {
        serverSocket.close();
        for (HandleClientRequest player : players) {
            player.getSocket().close();
            
        }
    }
}
