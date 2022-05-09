/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.start;

import common.response.Sender;
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
    private int port, numOfPlayers, maxPlayers;
    ServerSocket serverSocket;
    ArrayList<HandleClientRequest> players;
    
    public SocketCommunication(int port, int maxPlayers) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(port);
        this.maxPlayers = maxPlayers;
        this.players = new ArrayList<>();
    }
    
    @Override
    public void run() {
        System.out.println("###### GAME SERVER ######");
        System.out.println("Waiting for connections...");
        try {
            while (numOfPlayers < maxPlayers) {   
                Socket socket = serverSocket.accept();
                HandleClientRequest handleClientRequest = new HandleClientRequest(socket);
                players.add(handleClientRequest);
                handleClientRequest.start();
                
                numOfPlayers++;
                System.out.println("Player number " + numOfPlayers + " has connected to server.");
            }
            System.out.println("No longer accepting connections.");
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
