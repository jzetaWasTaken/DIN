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
public class AccountUpdateException extends Exception {

    /**
     * Creates a new instance of <code>AccountUpdateException</code> without
     * detail message.
     */
    public AccountUpdateException() {
    }

    /**
     * Constructs an instance of <code>AccountUpdateException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public AccountUpdateException(String msg) {
        super(msg);
    }
    
    public AccountUpdateException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
