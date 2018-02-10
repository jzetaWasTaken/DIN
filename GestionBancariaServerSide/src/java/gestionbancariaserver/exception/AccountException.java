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
public class AccountException extends RuntimeException {

    /**
     * Creates a new instance of <code>AccountCreateException</code> without
     * detail message.
     */
    public AccountException() {
    }

    /**
     * Constructs an instance of <code>AccountCreateException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public AccountException(String msg) {
        super(msg);
    }
    
    public AccountException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
