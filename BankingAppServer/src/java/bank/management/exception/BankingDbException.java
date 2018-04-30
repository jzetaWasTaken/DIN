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
public class BankingDbException extends RuntimeException {

    /**
     * Creates a new instance of <code>BankingDbException</code> without detail
     * message.
     */
    public BankingDbException() {
    }

    /**
     * Constructs an instance of <code>BankingDbException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public BankingDbException(String msg) {
        super(msg);
    }
    
    public BankingDbException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
