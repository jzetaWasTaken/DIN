/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionbancariaserver.exception;

/**
 * Represents an application business logic exception involving 
 * {@link gestionbancariaserver.entity.Transaction} and
 * {@link gestionbancariaserver.entity.Transaction}. Thrown from 
 * {@link gestionbancariaserver.ejb.BankingEJB} when an account
 * does not have enough funds to make certain transaction.
 * 
 * @author Jon Zaballa Zarzosa
 * @version 1.0, 17 Feb 2018
 */
public class NotEnoughFundsException extends Exception {

    /**
     * Creates a new instance of <code>NotEnoughFundsException</code> without
     * detail message.
     */
    public NotEnoughFundsException() {
    }

    /**
     * Constructs an instance of <code>NotEnoughFundsException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NotEnoughFundsException(String msg) {
        super(msg);
    }
}
