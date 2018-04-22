/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.exception;

/**
 * Represents an application business logic exception involving customer 
 * authentication. I serves as a wrapping exception to throw from the 
 * {@link gestionbancariaserver.rest.BankingREST} RESTFul service.
 * <p></p>
 * It is mapped to an HTTP response by 
 * {@link gestionbancariaserver.exceptionmapper.CustomerUnauthorizedExceptionMapper}
 * 
 * @author Jon Zaballa Zarzosa
 * @version 1.0, 17 Feb 2018
 */
public class CustomerUnauthorizedException extends RuntimeException {

    /**
     * Creates a new instance of <code>CustomerUnauthorizedException</code>
     * without detail message.
     */
    public CustomerUnauthorizedException() {
    }

    /**
     * Constructs an instance of <code>CustomerUnauthorizedException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public CustomerUnauthorizedException(String msg) {
        super(msg);
    }
    
    /**
     * Constructs an instance of <code>CustomerUnauthorizedException</code> with
     * the specified detail message.
     *
     * @param msg   the detail message.
     * @param cause the wrapped exception.
     */
    public CustomerUnauthorizedException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
