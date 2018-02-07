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
public class CustomerUpdateException extends Exception {

    /**
     * Creates a new instance of <code>CustomerUpdateException</code> without
     * detail message.
     */
    public CustomerUpdateException() {
    }

    /**
     * Constructs an instance of <code>CustomerUpdateException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CustomerUpdateException(String msg) {
        super(msg);
    }
    
    public CustomerUpdateException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
