/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionbancariaserver.exception;

/**
 * Represents an application business logic exception involving any entity type
 * deletion. I serves as a wrapping exception to throw from the 
 * {@link gestionbancariaserver.rest.BankingREST} RESTFul service.
 * <p></p>
 * It is mapped to an HTTP response by 
 * {@link gestionbancariaserver.exceptionmapper.EntityDeleteExceptionMapper}
 * 
 * @author Jon Zaballa Zarzosa
 * @version 1.0, 17 Feb 2018
 */
public class EntityDeleteException extends RuntimeException {

    /**
     * Creates a new instance of <code>EntityDeleteException</code> without
     * detail message.
     */
    public EntityDeleteException() {
    }

    /**
     * Constructs an instance of <code>EntityDeleteException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public EntityDeleteException(String msg) {
        super(msg);
    }
    
    /**
     * Constructs an instance of <code>EntityDeleteException</code> with the
     * specified detail message.
     *
     * @param msg   the detail message.
     * @param cause the wrapped exception.
     */
    public EntityDeleteException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
