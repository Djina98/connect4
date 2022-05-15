/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.client;

import common.domain.GenericEntity;
import common.domain.Player;
import common.request.Receiver;
import common.request.Request;
import common.response.Response;
import common.response.ResponseStatus;
import common.response.Sender;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.controller.Controller;
import server.dbb.DBBConnectionFactory;
import server.start.SocketCommunication;

/**
 *
 * @author Djina
 */
public class HandleClientRequest extends Thread{
    private Socket socket;
    private Sender sender;
    private Receiver receiver;

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
                                    ClientSession.getInstance().addPlayer(object);
                                    int number = ClientSession.getInstance().getIndexOfPlayer((Player) object);
                                    if(first(++number))
                                        response.setMessage("You are player #" + (++number) + ". Waiting for player #2 to connect.");
                                    else
                                        response.setMessage("You are player #" + (++number));
                                } catch (Exception ex) {
                                    Logger.getLogger(HandleClientRequest.class.getName()).log(Level.SEVERE, null, ex);
                                    response.setStatus(ResponseStatus.ERROR);
                                    response.setException(ex);
                                }
                                break;
                            case LOGIN:
                                try {
                                    GenericEntity object = Controller.getInstance().login((GenericEntity) request.getData());

                                    response.setResult(object);
                                    response.setStatus(ResponseStatus.SUCCESS);
                                    ClientSession.getInstance().addPlayer(object);
                                    int number = ClientSession.getInstance().getIndexOfPlayer((Player) object);
                                    if(first(++number))
                                        response.setMessage("You are player #" + (++number) + ". Waiting for player #2 to connect.");
                                    else
                                        response.setMessage("You are player #" + (++number));
                                } catch (Exception ex) {
                                    Logger.getLogger(HandleClientRequest.class.getName()).log(Level.SEVERE, null, ex);
                                    response.setStatus(ResponseStatus.ERROR);
                                    response.setException(ex);
                                }
                                break;
                            case MAKE_MOVE:
                                try {
                                    Controller.getInstance().makeMove((GenericEntity) request.getData());
                                    response.setStatus(ResponseStatus.SUCCESS);
                                } catch (Exception ex) {
                                    Logger.getLogger(HandleClientRequest.class.getName()).log(Level.SEVERE, null, ex);
                                    response.setStatus(ResponseStatus.ERROR);
                                    response.setException(ex);
                                }
                                break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                sender.send(response);
            } catch (Exception ex) {
                System.out.println("Player has disconnected.");
                ClientSession.getInstance().logoutAll();     
                SocketCommunication.getInstance().stopServer();
                DBBConnectionFactory.getInstance().closeConnection();   
            }
        }
    }
    
    private boolean first(int number){
        return number == 1;
    }
}
