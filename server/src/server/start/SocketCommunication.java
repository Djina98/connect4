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
    
    public static int PLAYER1 = 1; // Indicate player 1
    public static int PLAYER2 = 2; // Indicate player 2
    public static int PLAYER1_WON = 1; // Indicate player 1 won
    public static int PLAYER2_WON = 2; // Indicate player 2 won
    public static int DRAW = 3; // Indicate a draw
    public static int CONTINUE = 4; // Indicate to continue

    private int sessionNo = 1; // Number a session

    public static final int COLS = 7;
    public static final int ROWS = 6;
    
    private int port;
    private ServerSocket serverSocket;
    private ArrayList<HandleClientRequest> players;
    private static SocketCommunication instance;
    
    
    private SocketCommunication(int port, int maxPlayers) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(port);
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
