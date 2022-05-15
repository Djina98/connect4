/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 *
 * @author Djina
 */
public class Move implements GenericEntity{
    
    private Long id;
    private Long gameId;
    private Long playerId;
    private int row;
    private int column;

    public Move(Long id, Long gameId, Long playerId, int row, int column) {
        this.id = id;
        this.gameId = gameId;
        this.playerId = playerId;
        this.row = row;
        this.column = column;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "Move{" + "id=" + id + ", gameId=" + gameId + ", playerId=" + playerId + ", row=" + row + ", column=" + column + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.gameId);
        hash = 53 * hash + Objects.hashCode(this.playerId);
        hash = 53 * hash + this.row;
        hash = 53 * hash + this.column;
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
        if (this.column != other.column) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.gameId, other.gameId)) {
            return false;
        }
        if (!Objects.equals(this.playerId, other.playerId)) {
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
        sb.append(gameId).
                append(", ").append(playerId).
                append(", ").append(row).
                append(", ").append(column);
        return sb.toString();
    }

    @Override
    public String getAtrNames() {
        return "gameId,playerId,row,column";
    }

    @Override
    public String setAtrValues() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getWhereCondition() {
        return "gameId=" + gameId;
    }

    @Override
    public GenericEntity getNewRecord(ResultSet rs) {
        try {
            long dbId = rs.getLong("id");
            long dbGameId = rs.getLong("gameId");
            long dbPlayerId = rs.getLong("playerId");
            int dbRow = rs.getInt("row");
            int dbColumn = rs.getInt("column");

            return new Move(dbId, dbGameId, dbPlayerId, dbRow, dbColumn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
