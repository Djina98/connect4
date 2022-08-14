/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.response;

import java.io.Serializable;

/**
 *
 * @author Djina
 */
public enum ResponseStatus implements Serializable{
    SUCCESS,
    ERROR,
    PLAYER1_WON,
    PLAYER2_WON,
    DRAW,
    CONTINUE
}
