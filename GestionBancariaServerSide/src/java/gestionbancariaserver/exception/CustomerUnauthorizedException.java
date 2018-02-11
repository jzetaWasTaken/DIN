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
public class CustomerUnauthorizedException extends RuntimeException {

    /**
     * Creates a new instance of <code>CustomerUnauthorizedException</code>
     * without detail message.
     */
    public CustomerUnauthorizedException() {
    }

    /**
     * Constructs an instance of <code>CustomerUnauthorizedException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public CustomerUnauthorizedException(String msg) {
        super(msg);
    }
    
    public CustomerUnauthorizedException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
