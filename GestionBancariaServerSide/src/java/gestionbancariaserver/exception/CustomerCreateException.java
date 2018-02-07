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
public class CustomerCreateException extends Exception {

    /**
     * Creates a new instance of <code>CustomerCreateException</code> without
     * detail message.
     */
    public CustomerCreateException() {
    }

    /**
     * Constructs an instance of <code>CustomerCreateException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CustomerCreateException(String msg) {
        super(msg);
    }
    
    public CustomerCreateException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
