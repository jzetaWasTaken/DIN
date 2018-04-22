/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.exceptionmapper;

import bank.management.exception.CustomerUnauthorizedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Represents a 
 * {@link bank.management.exception.CustomerUnauthorizedException}to an 
 * HTTP response mapper.
 * 
 * @author Jon Zaballa Zarzosa
 * @version 1.0, 17 Feb 2018
 */
public class CustomerUnauthorizedExceptionMapper
        implements ExceptionMapper<CustomerUnauthorizedException>{

    /**
     * Creates the HTTP {@link Response} to send to the client when an
     * <code>CustomerUnauthorizedException</code> occurs.
     * 
     * @param exception The exception to map.
     * @return          HTTP response with UNAUTHORIZED status and the 
     *                  exception message.
     * @see             Response
     * @see             Response.Status#UNAUTHORIZED
     */
    @Override
    public Response toResponse(CustomerUnauthorizedException exception) {
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(exception.getCause().getMessage())
                .build();
    }
    
}
