/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.client;

import common.domain.ChooseColumn;
import common.domain.GenericEntity;
import common.domain.Move;
import common.request.Receiver;
import common.request.Request;
import common.response.Response;
import common.response.ResponseStatus;
import common.response.Sender;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.controller.Controller;
import server.dbb.DBBConnectionFactory;
import server.start.Server;

/**
 *
 * @author Djina
 */
public class HandleClientRequest extends Thread{
    private Socket socket;
    private final Sender sender;
    private final Receiver receiver;
    
    Connect4ComputerPlayer computer;
    Connect4ComputerPlayer.gameState gameState;

    public HandleClientRequest(Socket socket) {
        this.socket = socket;    
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }
    
    @Override
    public void run() {
        try {
            handleRequest();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    private void handleRequest() throws Exception {
        while (!isInterrupted()) {
            try{
                Request request = (Request) receiver.receive();
                Response response = new Response();
            
                try {
                    System.out.println("Operation: " + request.getOperation());
                    switch(request.getOperation()){
                            case SIGNUP:
                                try {
                                    GenericEntity object = Controller.getInstance().signup((GenericEntity) request.getData());

                                    response.setResult(object);
                                    response.setStatus(ResponseStatus.SUCCESS);
                                } catch (Exception ex) {
                                    Logger.getLogger(HandleClientRequest.class.getName()).log(Level.SEVERE, null, ex);
                                    response.setStatus(ResponseStatus.ERROR);
                                    response.setException(ex);
                                }
                                this.computer = new Connect4ComputerPlayer();
                                sender.send(response);
                                break;
                            case LOGIN:
                                try {
                                    GenericEntity object = Controller.getInstance().login((GenericEntity) request.getData());

                                    response.setResult(object);
                                    response.setStatus(ResponseStatus.SUCCESS);
                                } catch (Exception ex) {
                                    Logger.getLogger(HandleClientRequest.class.getName()).log(Level.SEVERE, null, ex);
                                    response.setStatus(ResponseStatus.ERROR);
                                    response.setException(ex);
                                }
                                this.computer = new Connect4ComputerPlayer();
                                sender.send(response);
                                break;
                            case GET_AVAILABLE_ROW:
                                ChooseColumn c1 = (ChooseColumn) request.getData(); // receive which button is clicked
                                 
                                int r1 = computer.getFirstEmptyRow(c1.getColumn()); // find out which row to setToken
                                response.setResult(r1);
                                sender.send(response); // write back the row to player 1
                                break;
                            case SEND_MOVE:    
                                 Move move = (Move) request.getData();
                                 int row = move.getRow();
                                 int column = move.getCol();
                                 gameState = computer.updateGrid(column, 'X');   


                                // Check if Player wins
                                if (gameState == Connect4ComputerPlayer.gameState.XWin) {
                                    response.setStatus(ResponseStatus.PLAYER1_WON);
                                    sender.send(response);
                                    break;
                                } else if (gameState == Connect4ComputerPlayer.gameState.DRAW) { // Check if all cells are filled
                                    response.setResult(ResponseStatus.DRAW);
                                    sender.send(response);
                                    break;
                                } else {

                                }

                                // -----Computer-----
                                Random rand = new Random();
                                int c2;
                                int r2;
                                do {
                                    c2 = Math.abs(rand.nextInt() % Server.COLS); // generate the column
                                    r2 = computer.getFirstEmptyRow(c2); // find out which row to setToken
                                } while (r2 == -1);

                                // Generate a move from computer
                                gameState = computer.updateGrid(c2, 'O');

                                // Check if Player 2 wins
                                if (gameState == Connect4ComputerPlayer.gameState.OWin) {
                                    response.setStatus(ResponseStatus.PLAYER2_WON);
                                    response.setResult(new Move(r2, c2));
                                    sender.send(response);
                                    break;
                                } else if (gameState == Connect4ComputerPlayer.gameState.DRAW) { // Check if all cells are filled
                                    response.setStatus(ResponseStatus.DRAW);
                                    sender.send(response);
                                    break;
                                } else {
                                    // Notify player 1 to take the turn
                                    response.setStatus(ResponseStatus.CONTINUE);
                                    // Send player 2's selected row and column to player 1
                                    response.setResult(new Move(r2, c2));
                                    System.out.println("row: " + r2 + " col: " + c2);
                                    sender.send(response);
                                }
                                break; 
                            }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Player has disconnected.");
                DBBConnectionFactory.getInstance().closeConnection();   
            }
        }
    }
}
