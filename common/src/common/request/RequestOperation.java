/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.request;

import java.io.Serializable;

/**
 *
 * @author Djina
 */
public enum RequestOperation implements Serializable{
    LOGIN,
    SIGNUP,
    GET_AVAILABLE_ROW,
    SEND_MOVE,
    RECEIVE_MOVE,
    PLAY_GAME
}
