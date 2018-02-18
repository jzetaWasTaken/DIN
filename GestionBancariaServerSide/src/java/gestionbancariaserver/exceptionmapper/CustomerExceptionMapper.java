/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionbancariaserver.exceptionmapper;

import gestionbancariaserver.exception.CustomerException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Represents a {@link gestionbancariaserver.exception.CustomerException}to an 
 * HTTP response mapper.
 * 
 * @author Jon Zaballa Zarzosa
 * @version 1.0, 17 Feb 2018
 */
public class CustomerExceptionMapper 
        implements ExceptionMapper<CustomerException> {

    /**
     * Creates the HTTP {@link Response} to send to the client when an
     * <code>CustomerException</code> occurs.
     * 
     * @param exception The exception to map.
     * @return          HTTP response with NOT_FOUND status and the 
     *                  exception message.
     * @see             Response
     * @see             Response.Status#NOT_FOUND
     */
    @Override
    public Response toResponse(CustomerException exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(exception.getCause().getMessage())
                .build();
    }
}

