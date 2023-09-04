/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.Serializable;

/**
 *
 * @author Aleksandra
 */
public class Response implements Serializable{
    private ResponseType responseType;
    private Object result;
    private Exception exception;

    public Response() {
    }
    
    public Response(ResponseType responseType, Object result, Exception exception) {
        this.responseType = responseType;
        this.result = result;
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }
    
    public void setException(Exception exception) {
        this.exception = exception;
    }

    public ResponseType getResponseType() {
        return responseType;
    }
    
    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
    
    
}
