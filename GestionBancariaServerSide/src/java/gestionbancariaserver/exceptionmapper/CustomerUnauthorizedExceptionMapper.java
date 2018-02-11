/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionbancariaserver.exceptionmapper;

import gestionbancariaserver.exception.CustomerUnauthorizedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author jon
 */
public class CustomerUnauthorizedExceptionMapper
        implements ExceptionMapper<CustomerUnauthorizedException>{

    @Override
    public Response toResponse(CustomerUnauthorizedException exception) {
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(exception.getCause().getMessage())
                .build();
    }
    
}
