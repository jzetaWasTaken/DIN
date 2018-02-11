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
public class NoCustomerException extends Exception {

    /**
     * Creates a new instance of <code>NoCustomerException</code> without detail
     * message.
     */
    public NoCustomerException() {
    }

    /**
     * Constructs an instance of <code>NoCustomerException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NoCustomerException(String msg) {
        super(msg);
    }
    
    public NoCustomerException(String msg, Throwable cause) {
        super(msg, cause);
    }
}