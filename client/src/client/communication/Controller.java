/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.communication;

import common.domain.ChooseColumn;
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
    private final SocketCommunication socketCommunication;
    
    private Controller() {
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
    
    public int playGame() throws Exception{
        Request request = new Request(RequestOperation.PLAY_GAME, null);
        socketCommunication.sendRequest(request);
        Response response = socketCommunication.readResponse();
        
        return (int) response.getResult();
    }
    
    public int getAvailableRow(int column) throws Exception {
        ChooseColumn chooseColumn = new ChooseColumn(column);
        Request request = new Request(RequestOperation.GET_AVAILABLE_ROW, chooseColumn);
        socketCommunication.sendRequest(request);
        Response response = socketCommunication.readResponse();
        
        System.out.println("RESPONSE: " + response.getResult().toString());
        return (int) response.getResult();
    }
    
    public Response sendMove(int row, int column) throws Exception{
        Move move = new Move(row, column);
        Request request = new Request(RequestOperation.SEND_MOVE, move);
        socketCommunication.sendRequest(request);
        Response response = socketCommunication.readResponse();
        System.out.println("GAME STATUS: " + response.getStatus());
        return response;
    }
    
    public Move receiveMove() throws Exception{
        Response response = socketCommunication.readResponse();
        
        return (Move) response.getResult();
    }
}
