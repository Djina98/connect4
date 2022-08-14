/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.request;

import java.io.Serializable;
import java.util.Objects;
import common.domain.GenericEntity;

/**
 *
 * @author Djina
 */
public class Request implements Serializable{
    private RequestOperation operation;
    private GenericEntity data;

    public Request() {
    }

    public Request(RequestOperation operation, GenericEntity data) {
        this.operation = operation;
        this.data = data;
    }
    
    public RequestOperation getOperation() {
        return operation;
    }

    public void setOperation(RequestOperation operation) {
        this.operation = operation;
    }

    public GenericEntity getData() {
        return data;
    }

    public void setData(GenericEntity data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.operation);
        hash = 37 * hash + Objects.hashCode(this.data);
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
        final Request other = (Request) obj;
        if (this.operation != other.operation) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Request{operation=").append(operation);
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
    
}
