/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.operation.impl;

import common.domain.Move;
import server.operation.AbstractGenericOperation;

/**
 *
 * @author Djina
 */
public class MakeMoveOperation extends AbstractGenericOperation<Move, Move>{

    @Override
    protected void preconditions(Move entity) throws Exception {
        
    }

    @Override
    protected void executeOperation(Move entity) throws Exception {
        repository.insertRecord(entity);
    }
    
}
