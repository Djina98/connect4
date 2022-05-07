/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.session;

import common.domain.Player;

/**
 *
 * @author Djina
 */
public class Session {
    private Player player;
    private static Session instance;

    public Session() {
    }

    public synchronized static Session getInstance() {
        if(instance == null)
            instance = new Session();
        return instance;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

}
