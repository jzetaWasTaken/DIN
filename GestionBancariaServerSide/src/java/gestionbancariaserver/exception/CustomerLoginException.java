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
public class CustomerLoginException extends Exception {

    /**
     * Creates a new instance of <code>CustomerLoginException</code> without
     * detail message.
     */
    public CustomerLoginException() {
    }

    /**
     * Constructs an instance of <code>CustomerLoginException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CustomerLoginException(String msg) {
        super(msg);
    }
    
    public CustomerLoginException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
