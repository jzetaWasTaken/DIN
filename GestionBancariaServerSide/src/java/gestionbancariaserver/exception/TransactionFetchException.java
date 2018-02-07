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
public class TransactionFetchException extends Exception {

    /**
     * Creates a new instance of <code>TransactionFetchException</code> without
     * detail message.
     */
    public TransactionFetchException() {
    }

    /**
     * Constructs an instance of <code>TransactionFetchException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public TransactionFetchException(String msg) {
        super(msg);
    }
    
    public TransactionFetchException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
