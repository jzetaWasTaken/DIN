/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionbancariaserver.exceptionmapper;

import gestionbancariaserver.exception.BankingBussinessException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Represents a {@link gestionbancariaserver.exception.BankingBussinessException}
 * to an HTTP response mapper.
 * 
 * @author Jon Zaballa Zarzosa
 * @version 1.0, 17 Feb 2018
 */
public class BankingBussinessExceptionMapper 
        implements ExceptionMapper<BankingBussinessException> {
    
    /**
     * Creates the HTTP {@link Response} to send to the client when an
     * <code>BankingBussinessException</code> occurs.
     * 
     * @param exception The exception to map.
     * @return          HTTP response with INTERNAL_SERVER_ERROR status and the 
     *                  exception message.
     * @see Response
     * @see Response.Status#INTERNAL_SERVER_ERROR
     */
    @Override
    public Response toResponse(BankingBussinessException exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(exception.getCause().getMessage())
                .build();
    }
}
