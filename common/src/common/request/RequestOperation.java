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
    MAKE_MOVE
}
