/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.communication;

import client.settings.PropertiesLoader;
import common.request.Request;
import common.response.Response;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Djina
 */
public class SocketCommunication {
    private final Socket socket;

    public SocketCommunication() {
        try {
            socket = new Socket(PropertiesLoader.getInstance().getProperty("server.socket.host"), Integer.parseInt(PropertiesLoader.getInstance().getProperty("server.socket.port")));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void sendRequest(Request request)throws Exception{
        try{
            ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
            outSocket.writeObject(request);
        } catch (IOException ex) {
            System.out.println(ex);
            throw new IOException("Error sending request!");
        }
    }

    public Response readResponse()throws Exception{
        try{
            ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
            return (Response) inSocket.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex);
            throw new IOException("Error reading response");
        }
    }
}
