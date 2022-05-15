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
import java.util.logging.Level;
import java.util.logging.Logger;
import server.client.HandleClientRequest;
import server.settings.Constants;
import server.settings.PropertiesLoader;

/**
 *
 * @author Djina
 */
public class SocketCommunication extends Thread{
    private int port, numOfPlayers, maxPlayers;
    private ServerSocket serverSocket;
    private ArrayList<HandleClientRequest> players;
    private static SocketCommunication instance;
    
    
    private SocketCommunication(int port, int maxPlayers) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(port);
        this.maxPlayers = maxPlayers;
        this.players = new ArrayList<>();
    }
    
    public static SocketCommunication getInstance() {
        if (instance == null) {
            try {
                instance = new SocketCommunication(Integer.parseInt(PropertiesLoader.getInstance().getProperty(Constants.PORT)), 2);
            } catch (IOException ex) {
                Logger.getLogger(SocketCommunication.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instance;
    }
    
    @Override
    public void run() {
        System.out.println("###### GAME SERVER ######");
        System.out.println("Waiting for connections...");
        try {
            while (!isInterrupted()) {   
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
        for (HandleClientRequest player : players) {
            player.getSocket().close();
            
        }
    }
}
