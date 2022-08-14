/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.client;

import common.domain.Move;
import common.request.Receiver;
import common.response.Response;
import common.response.Sender;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.start.SocketCommunication;

/**
 *
 * @author Djina
 */
public class AgainstComputer implements Runnable{
    
    Connect4ComputerPlayer computer = new Connect4ComputerPlayer();
    
    private Sender sender;
    private Receiver receiver;
    
    public AgainstComputer(Sender sender, Receiver receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    @Override
    public void run() {
        try {
            Response response = new Response();
            // Write anything to notify player 1 to start
            // This is just to let player 1 know to start
            //toPlayer.writeInt(1);

            // Continuously serve the players and determine and report
            // the game status to the players
            while (true) {
                // gameState
                Connect4ComputerPlayer.gameState gameState;

                // -----Player-----
                int c1 = (int) receiver.receive(); // receive which button clicked
                System.out.println(c1);
                int r1 = computer.getFirstEmptyRow(c1); // find out which row to setToken
                response.setResult(r1);
                sender.send(response); // write back the row to player 1

                // Receive a move from player 1
                @SuppressWarnings("unused")
                int row = (int) receiver.receive();
                int column = (int) receiver.receive();
                gameState = computer.updateGrid(column, 'X');

                // Check if Player wins
                if (gameState == Connect4ComputerPlayer.gameState.XWin) {
                    response.setResult(SocketCommunication.PLAYER1_WON);
                    sender.send(response);
                    break; // Break the loop
                } else if (gameState == Connect4ComputerPlayer.gameState.DRAW) { // Check if all cells are filled
                    response.setResult(SocketCommunication.DRAW);
                    sender.send(response);
                    break;
                } else {

                }

                // -----Computer-----
                Random rand = new Random();
                int c2;
                int r2;
                do {
                    c2 = Math.abs(rand.nextInt() % SocketCommunication.COLS); // generate the column
                    r2 = computer.getFirstEmptyRow(c2); // find out which row to setToken
                } while (r2 == -1);

                // Generate a move from computer
                gameState = computer.updateGrid(c2, 'O');

                // Check if Player 2 wins
                if (gameState == Connect4ComputerPlayer.gameState.OWin) {
                    response.setResult(SocketCommunication.PLAYER2_WON);
                    sender.send(response);
                    response.setResult(new Move(r2, c2));
                    sender.send(response);
                    break;
                } else if (gameState == Connect4ComputerPlayer.gameState.DRAW) { // Check if all cells are filled
                    response.setResult(SocketCommunication.DRAW);
                    sender.send(response);
                    break;
                } else {
                    // Notify player 1 to take the turn
                    response.setResult(SocketCommunication.CONTINUE);
                    sender.send(response);

                    // Send player 2's selected row and column to player 1
                    response.setResult(new Move(r2, c2));
                    sender.send(response);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(AgainstComputer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
} 

