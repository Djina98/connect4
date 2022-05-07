/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.controller;

import common.domain.GenericEntity;
import java.sql.SQLException;
import java.util.Optional;
import server.dbb.impl.RepositoryDBGeneric;
import server.operation.AbstractGenericOperation;
import server.operation.impl.SignupOperation;
import server.repository.Repository;

/**
 *
 * @author Djina
 */
public class Controller {
    private static Controller instance;
    private Repository repository;
    
    private Controller() {
        repository = new RepositoryDBGeneric();
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }
    
    public GenericEntity findRecord(GenericEntity entity) throws SQLException, Exception {
        AbstractGenericOperation so = new SignupOperation();
        so.execute(entity);
        return ((SignupOperation) so).getObject();
    }
}
