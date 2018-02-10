/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionbancariaserver.exception;

/**
 *
 * @author jon
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
    
    public TransactionException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
