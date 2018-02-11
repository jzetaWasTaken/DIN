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
public class EntityDeleteException extends RuntimeException {

    /**
     * Creates a new instance of <code>EntityDeleteException</code> without
     * detail message.
     */
    public EntityDeleteException() {
    }

    /**
     * Constructs an instance of <code>EntityDeleteException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public EntityDeleteException(String msg) {
        super(msg);
    }
    
    public EntityDeleteException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
