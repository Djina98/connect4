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
public class Player implements GenericEntity{
    private Long id;
    private String nickname;
    private String password;

    public Player(Long id, String nickname, String password) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
    }

    public Player(String nickname) {
        this.nickname = nickname;
    }

    public Player() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.nickname);
        hash = 59 * hash + Objects.hashCode(this.password);
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
        final Player other = (Player) obj;
        if (!Objects.equals(this.nickname, other.nickname)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Player: " + nickname;
    }

    @Override
    public String getClassName() {
        return "player";
    }

    @Override
    public String getAtrValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(nickname).
                append("', '").append(password).
                append("'");
        return sb.toString();
    }

    @Override
    public String getAtrNames() {
        return "nickname,password";
    }

    @Override
    public String setAtrValues() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String getWhereCondition() {
        return "nickname='" + nickname + "'";
    }

    @Override
    public GenericEntity getNewRecord(ResultSet rs) {
        try {
            long dbId = rs.getLong("id");
            String dbNickname = rs.getString("nickname");
            String dbPassword = rs.getString("password");

            return new Player(dbId, dbNickname, dbPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
