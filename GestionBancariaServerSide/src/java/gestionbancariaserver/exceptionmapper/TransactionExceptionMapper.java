/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionbancariaserver.exceptionmapper;

import gestionbancariaserver.exception.TransactionException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author jon
 */
public class TransactionExceptionMapper 
        implements ExceptionMapper<TransactionException> {
    
    @Override
    public Response toResponse(TransactionException exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(exception.getCause().getMessage())
                .build();
    }
}
