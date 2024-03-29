/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Djina
 */
public class Move implements GenericEntity{
   
    private int row;
    private int col;

    public Move(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public String toString() {
        return "Move{" + "row=" + row + ", column=" + col + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.row;
        hash = 53 * hash + this.col;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Move other = (Move) obj;
        if (this.row != other.row) {
            return false;
        }
        if (this.col != other.col) {
            return false;
        }
        return true;
    }
    
    
    
    @Override
    public String getClassName() {
        return "move";
    }

    @Override
    public String getAtrValues() {
        StringBuilder sb = new StringBuilder();
        sb.append(row).
                append(", ").append(col);
        return sb.toString();
    }

    @Override
    public String getAtrNames() {
        return "row,col";
    }

    @Override
    public String setAtrValues() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String getWhereCondition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GenericEntity getNewRecord(ResultSet rs) {
        try {
            int dbRow = rs.getInt("row");
            int dbColumn = rs.getInt("col");

            return new Move(dbRow, dbColumn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
