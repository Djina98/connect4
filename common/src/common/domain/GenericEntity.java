/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.domain;

import java.io.Serializable;
import java.sql.ResultSet;

/**
 *
 * @author Djina
 */
public interface GenericEntity extends Serializable{
    String getClassName();
    String getAtrValues();
    String getAtrNames();
    String setAtrValues();
    String getWhereCondition();
    GenericEntity getNewRecord(ResultSet rs);
}
