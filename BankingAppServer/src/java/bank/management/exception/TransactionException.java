/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.exception;

/**
 * Represents an application business logic exception involving 
 * {@link gestionbancariaserver.entity.Transaction}. I serves as a wrapping 
 * exception to throw from the {@link gestionbancariaserver.rest.BankingREST} 
 * RESTFul service.
 * <p></p>
 * It is mapped to an HTTP response by 
 * {@link gestionbancariaserver.exceptionmapper.TransactionExceptionMapper}
 * 
 * @author Jon Zaballa Zarzosa
 * @version 1.0, 17 Feb 2018
 */
public class TransactionException extends RuntimeException {

    /**
     * Creates a new instance of <code>TransactionCreateException</code> without
     * detail message.
     */
    public TransactionException() {
    }

    /**
     * Constructs an instance of <code>TransactionCreateException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public TransactionException(String msg) {
        super(msg);
    }
    
    /**
     * Constructs an instance of <code>TransactionCreateException</code> with
     * the specified detail message.
     *
     * @param msg   the detail message.
     * @param cause wrapped exception.
     */
    public TransactionException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
