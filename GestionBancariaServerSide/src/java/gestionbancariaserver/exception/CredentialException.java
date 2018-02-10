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
public class CredentialException extends RuntimeException {

    /**
     * Creates a new instance of <code>CredentialUpdateException</code> without
     * detail message.
     */
    public CredentialException() {
    }

    /**
     * Constructs an instance of <code>CredentialUpdateException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CredentialException(String msg) {
        super(msg);
    }
    
    public CredentialException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
