/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.exceptionmapper;

import bank.management.exception.TransactionException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Represents a {@link bank.management.exception.TransactionException}
 * to an HTTP response mapper.
 * 
 * @author Jon Zaballa Zarzosa
 * @version 1.0, 17 Feb 2018
 */
@Provider
public class TransactionExceptionMapper 
        implements ExceptionMapper<TransactionException> {
    
    /**
     * Creates the HTTP {@link Response} to send to the client when an
     * <code>TransactionException</code> occurs.
     * 
     * @param exception The exception to map.
     * @return          HTTP response with CONFLICT status and the 
     *                  exception message.
     * @see             Response
     * @see             Response.Status#CONFLICT
     */
    @Override
    public Response toResponse(TransactionException exception) {
        return Response.status(Response.Status.CONFLICT)
                .entity(exception.getCause().getMessage())
                .build();
    }
}
