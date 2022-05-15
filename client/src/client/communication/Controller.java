/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.communication;

import common.domain.Move;
import common.domain.Player;
import common.request.Request;
import common.request.RequestOperation;
import common.response.Response;
import common.response.ResponseStatus;

/**
 *
 * @author Djina
 */
public class Controller {
    private static Controller instance;
    //private final WebCommunication webCommunication;
    private final SocketCommunication socketCommunication;
    
    private Controller() {
        //webCommunication = new WebCommunication();
        socketCommunication = new SocketCommunication();
    }
     
    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }
    
    public Player signup(String nickaname, String password) throws Exception {
        Player player = new Player(-1L, nickaname, password);
        Request request = new Request(RequestOperation.SIGNUP, player);
        socketCommunication.sendRequest(request);
        Response response = socketCommunication.readResponse();
        if (response.getStatus() == ResponseStatus.SUCCESS) {
            return (Player)response.getResult();
        }
        throw response.getException();
    }
    
    public Player login(String nickaname, String password) throws Exception {
        Player player = new Player(-1L, nickaname, password);
        Request request = new Request(RequestOperation.LOGIN, player);
        socketCommunication.sendRequest(request);
        Response response = socketCommunication.readResponse();
        if (response.getStatus() == ResponseStatus.SUCCESS) {
            return (Player) response.getResult();
        }
        throw response.getException();
    }
    
    public void makeMove(Move move) throws Exception {
        Request request = new Request(RequestOperation.MAKE_MOVE, move);
        socketCommunication.sendRequest(request);
        Response response = socketCommunication.readResponse();
        if (response.getStatus() == ResponseStatus.ERROR) {
            throw response.getException();
        }
        
    }

}
