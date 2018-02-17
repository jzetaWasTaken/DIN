/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionbancariaserver.exception;

/**
 * Represents an application business logic exception involving 
 * {@link gestionbancariaserver.entity.Account}. I serves as a wrapping 
 * exception to throw from the {@link gestionbancariaserver.rest.BankingREST} 
 * RESTFul service.
 * <p></p>
 * It is mapped to an HTTP response by 
 * {@link gestionbancariaserver.exceptionmapper.AccountExceptionMapper}
 * 
 * @author Jon Zaballa Zarzosa
 * @version 1.0, 17 Feb 2018
 */
public class AccountException extends RuntimeException {

    /**
     * Creates a new instance of <code>AccountCreateException</code> without
     * detail message.
     */
    public AccountException() {
    }

    /**
     * Constructs an instance of <code>AccountCreateException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public AccountException(String msg) {
        super(msg);
    }
    
    /**
     * Constructs an instance of <code>AccountCreateException</code> with the
     * specified detail message and the cause.
     *
     * @param msg   the detail message.
     * @param cause the wrapped exception
     */
    public AccountException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
