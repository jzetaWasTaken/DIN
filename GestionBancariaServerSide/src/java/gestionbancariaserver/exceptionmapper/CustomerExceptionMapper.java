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
 *
 * @author jon
 */
public class CustomerExceptionMapper 
        implements ExceptionMapper<CustomerException> {

    @Override
    public Response toResponse(CustomerException exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(exception.getCause().getMessage())
                .build();
    }
}

