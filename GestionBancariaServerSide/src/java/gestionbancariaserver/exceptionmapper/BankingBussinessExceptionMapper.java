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
 *
 * @author jon
 */
public class BankingBussinessExceptionMapper 
        implements ExceptionMapper<BankingBussinessException> {
    
    @Override
    public Response toResponse(BankingBussinessException exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(exception.getCause().getMessage())
                .build();
    }
}
