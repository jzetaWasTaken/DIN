/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.exception;

/**
 *
 * @author jon
 */
public class ManagerException extends Exception {

    /**
     * Creates a new instance of <code>ManagerException</code> without detail
     * message.
     */
    public ManagerException() {
    }

    /**
     * Constructs an instance of <code>ManagerException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ManagerException(String msg) {
        super(msg);
    }
}
