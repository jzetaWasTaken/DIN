/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.exceptionmapper;

import bank.management.exception.EntityDeleteException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Represents an {@link bank.management.exception.EntityDeleteException}
 * to an HTTP response mapper.
 * 
 * @author Jon Zaballa Zarzosa
 * @version 1.0, 17 Feb 2018
 */
@Provider
public class EntityDeleteExceptionMapper
        implements ExceptionMapper<EntityDeleteException> {

    /**
     * Creates the HTTP {@link Response} to send to the client when an
     * <code>EntityDeleteException</code> occurs.
     * 
     * @param exception The exception to map.
     * @return          HTTP response with GONE status and the 
     *                  exception message.
     * @see             Response
     * @see             Response.Status#GONE
     */
    @Override
    public Response toResponse(EntityDeleteException exception) {
        return Response.status(Response.Status.GONE)
                .entity(exception.getCause().getMessage())
                .build();
    }
    
}
