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
public class CustomerDeleteException extends Exception {

    /**
     * Creates a new instance of <code>CustomerDeleteException</code> without
     * detail message.
     */
    public CustomerDeleteException() {
    }

    /**
     * Constructs an instance of <code>CustomerDeleteException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CustomerDeleteException(String msg) {
        super(msg);
    }
    
    public CustomerDeleteException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
