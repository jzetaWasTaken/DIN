/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.exception;

/**
 * Represents an application business logic exception involving 
 * {@link gestionbancariaserver.entity.Transaction}. Thrown from 
 * {@link gestionbancariaserver.ejb.BankingEJB} when no transactions are found.
 * 
 * @author Jon Zaballa Zarzosa
 * @version 1.0, 17 Feb 2018
 */
public class NoTransactionException extends Exception {

    /**
     * Creates a new instance of <code>NoTransactionException</code> without
     * detail message.
     */
    public NoTransactionException() {
    }

    /**
     * Constructs an instance of <code>NoTransactionException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NoTransactionException(String msg) {
        super(msg);
    }
}
