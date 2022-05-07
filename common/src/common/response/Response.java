/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.response;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Djina
 */
public class Response implements Serializable{
    private ResponseStatus status;
    private String message;
    private Exception exception;
    private Object result;

    public Response() {
    }

    
    public Response(ResponseStatus type, String message, Object result) {
        this.status = type;
        this.message = message;
        this.result = result;
    }

    public Response(ResponseStatus status, String message, Exception exception, Object result) {
        this.status = status;
        this.message = message;
        this.exception = exception;
        this.result = result;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.status);
        hash = 97 * hash + Objects.hashCode(this.message);
        hash = 97 * hash + Objects.hashCode(this.exception);
        hash = 97 * hash + Objects.hashCode(this.result);
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
        final Response other = (Response) obj;
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.exception, other.exception)) {
            return false;
        }
        if (!Objects.equals(this.result, other.result)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "Response{" + "status=" + status + ", message=" + message + ", result=" + result + ", exception=" + exception + '}';
    } 
}
