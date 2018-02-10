/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionbancariaserver.exceptionmapper;

import gestionbancariaserver.exception.CredentialException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author jon
 */
public class CredentialExceptionMapper 
        implements ExceptionMapper<CredentialException> {

    @Override
    public Response toResponse(CredentialException exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(exception.getCause().getMessage())
                .build();
    }
}

