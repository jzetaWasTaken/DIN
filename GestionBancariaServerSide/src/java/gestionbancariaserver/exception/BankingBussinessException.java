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
public class BankingBussinessException extends RuntimeException {

    /**
     * Creates a new instance of <code>BankingBussinessException</code> without
     * detail message.
     */
    public BankingBussinessException() {
    }

    /**
     * Constructs an instance of <code>BankingBussinessException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public BankingBussinessException(String msg) {
        super(msg);
    }
    
    public BankingBussinessException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
