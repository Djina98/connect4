/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.repository;

import java.sql.SQLException;
import java.util.Optional;
import common.domain.GenericEntity;

/**
 *
 * @author Djina
 */
public interface Repository {
    GenericEntity findRecord(GenericEntity entity) throws SQLException;
    Long insertRecord(GenericEntity entity) throws SQLException;
}
