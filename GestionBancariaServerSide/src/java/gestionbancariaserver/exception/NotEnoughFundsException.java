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
public class NotEnoughFundsException extends Exception {

    /**
     * Creates a new instance of <code>NotEnoughFundsException</code> without
     * detail message.
     */
    public NotEnoughFundsException() {
    }

    /**
     * Constructs an instance of <code>NotEnoughFundsException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NotEnoughFundsException(String msg) {
        super(msg);
    }
    
    public NotEnoughFundsException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
