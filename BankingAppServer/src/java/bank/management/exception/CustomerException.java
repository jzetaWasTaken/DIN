/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.exception;

/**
 * Represents an application business logic exception involving 
 * {@link gestionbancariaserver.entity.Customer}. I serves as a wrapping 
 * exception to throw from the {@link gestionbancariaserver.rest.BankingREST} 
 * RESTFul service.
 * <p></p>
 * It is mapped to an HTTP response by 
 * {@link gestionbancariaserver.exceptionmapper.CustomerExceptionMapper}
 * 
 * @author Jon Zaballa Zarzosa
 * @version 1.0, 17 Feb 2018
 */
public class CustomerException extends RuntimeException {

    /**
     * Creates a new instance of <code>CustomerCreateException</code> without
     * detail message.
     */
    public CustomerException() {
    }

    /**
     * Constructs an instance of <code>CustomerCreateException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CustomerException(String msg) {
        super(msg);
    }
    
    /**
     * Constructs an instance of <code>CustomerCreateException</code> with the
     * specified detail message and the cause.
     *
     * @param msg   the detail message.
     * @param cause the wrapped exception
     */
    public CustomerException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
