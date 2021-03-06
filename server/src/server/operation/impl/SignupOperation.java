/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.operation.impl;

import common.domain.GenericEntity;
import common.domain.Player;
import server.operation.AbstractGenericOperation;

/**
 *
 * @author Djina
 */
public class SignupOperation extends AbstractGenericOperation<Player, Player>{

    GenericEntity object = null;
    
    @Override
    protected void preconditions(Player entity) throws Exception {
        if(entity.getNickname() == null || entity.getNickname().isEmpty()) {
            throw new Exception("Nickname is required");
        }
        if(entity.getPassword()== null || entity.getPassword().isEmpty()) {
            throw new Exception("Password is required");
        }
    }

    @Override
    protected void executeOperation(Player entity) throws Exception {
        if(repository.findRecord(new Player(entity.getNickname())) == null) {
            Player registered = new Player();
            registered.setId(repository.insertRecord(entity));
            object = registered;
        } else {
           throw new RuntimeException("Nickname already exists");
        }
    }

    public GenericEntity getObject() {
        return object;
    }
    
}
