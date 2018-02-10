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
public class NoAccountException extends Exception {

    /**
     * Creates a new instance of <code>NoAccountException</code> without detail
     * message.
     */
    public NoAccountException() {
    }

    /**
     * Constructs an instance of <code>NoAccountException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NoAccountException(String msg) {
        super(msg);
    }
    
    public NoAccountException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
