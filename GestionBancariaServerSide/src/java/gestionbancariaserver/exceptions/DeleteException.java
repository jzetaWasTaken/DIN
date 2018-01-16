/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionbancariaserver.exceptions;

/**
 *
 * @author jon
 */
public class DeleteException extends Exception {

    /**
     * Creates a new instance of <code>DeleteExcection</code> without detail
     * message.
     */
    public DeleteException() {
    }

    /**
     * Constructs an instance of <code>DeleteExcection</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public DeleteException(String msg) {
        super(msg);
    }
}
