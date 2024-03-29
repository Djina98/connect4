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
public class Server extends Thread{
    
    public static int PLAYER1 = 1; // Indicate player 1
    public static int PLAYER2 = 2; // Indicate player 2
    public static int PLAYER1_WON = 1; // Indicate player 1 won
    public static int PLAYER2_WON = 2; // Indicate player 2 won
    public static int DRAW = 3; // Indicate a draw
    public static int CONTINUE = 4; // Indicate to continue

    public static final int COLS = 7;
    public static final int ROWS = 6;
    
    private int port;
    private ServerSocket serverSocket;
    private ArrayList<HandleClientRequest> players;
    //private static Server instance;
    
    
    public Server(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(port);
        this.players = new ArrayList<>();
    }
     
    @Override
    public void run() {
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
        serverSocket.close();
        for (HandleClientRequest player : players) {
            player.getSocket().close();
            
        }
    }
}
