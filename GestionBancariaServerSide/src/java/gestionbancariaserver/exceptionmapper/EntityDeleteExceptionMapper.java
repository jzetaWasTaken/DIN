/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionbancariaserver.exceptionmapper;

import gestionbancariaserver.exception.EntityDeleteException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author jon
 */
public class EntityDeleteExceptionMapper
        implements ExceptionMapper<EntityDeleteException> {

    @Override
    public Response toResponse(EntityDeleteException exception) {
        return Response.status(Response.Status.GONE)
                .entity(exception.getCause().getMessage())
                .build();
    }
    
}
