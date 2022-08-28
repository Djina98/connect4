/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.dbb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import server.dbb.DBBConnectionFactory;
import server.dbb.DBRepository;
import common.domain.GenericEntity;
import java.sql.ResultSet;

/**
 *
 * @author Djina
 */
public class RepositoryDBGeneric implements DBRepository{

    @Override
    public GenericEntity findRecord(GenericEntity entity) throws SQLException {
        try {
            Connection connection = DBBConnectionFactory.getInstance().getConnection();
            String query = "SELECT * FROM " + entity.getClassName() + " WHERE " + entity.getWhereCondition();
            System.out.println(query);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            boolean signal = resultSet.next();
            if (signal == true) {
                return entity.getNewRecord(resultSet);
            }
            
            return null;
       
        } catch (SQLException ex) {
            throw new SQLException("Error retrieving user from database");
        }
    }
    
    @Override
    public Long insertRecord(GenericEntity entity) throws SQLException {
        try {
            String query;
            Connection connection = DBBConnectionFactory.getInstance().getConnection();
            query = "INSERT INTO "+ entity.getClassName() + " (" + entity.getAtrNames() + ") VALUES (" + entity.getAtrValues() + ")";
            System.out.println(query);
            PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs != null && rs.next()) {
                return rs.getLong(1);
            } else {
                return 0L;
            }
        } catch (Exception ex) {
            throw ex;
        }
    }
}
