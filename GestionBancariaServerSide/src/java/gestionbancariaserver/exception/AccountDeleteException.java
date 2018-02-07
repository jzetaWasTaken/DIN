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
public class AccountDeleteException extends Exception {

    /**
     * Creates a new instance of <code>AccountDeleteException</code> without
     * detail message.
     */
    public AccountDeleteException() {
    }

    /**
     * Constructs an instance of <code>AccountDeleteException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public AccountDeleteException(String msg) {
        super(msg);
    }
    
    public AccountDeleteException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
