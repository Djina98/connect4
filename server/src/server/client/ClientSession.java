/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.client;

import common.domain.GenericEntity;
import common.domain.Player;
import java.util.ArrayList;

/**
 *
 * @author Djina
 */
public class ClientSession {
    private static ClientSession instance;
    ArrayList<Player> players;

    public ClientSession() {
        players = new ArrayList<>();
    }

    public static ClientSession getInstance() {
        if (instance == null) {
            instance = new ClientSession();
        }
        return instance;
    }

    public void addPlayer(GenericEntity object) {
        if (object instanceof Player) {
            if (!players.contains(object)) {
                players.add((Player) object);
            }
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void removePlayer(Object object) {
        try {
            if (object instanceof Player) {
                for (Player player : players) {
                    if (players.contains(object)) {
                        players.remove(object);
                    }
                }
            }
        } catch (Exception e) {
            
        }
    }

    public void logoutAll() {
        players = new ArrayList<>();
    }
}