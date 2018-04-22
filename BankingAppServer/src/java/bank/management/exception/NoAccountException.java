/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.exception;

/**
 * Represents an application business logic exception involving 
 * {@link gestionbancariaserver.entity.Account}. Thrown from 
 * {@link gestionbancariaserver.ejb.BankingEJB} when no accounts are found.
 * 
 * @author Jon Zaballa Zarzosa
 * @version 1.0, 17 Feb 2018
 */
public class NoAccountException extends Exception {

    /**
     * Creates a new instance of <code>NoAccountException</code> without detail
     * message.
     */
    public NoAccountException() {
    }

    /**
     * Constructs an instance of <code>NoAccountException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NoAccountException(String msg) {
        super(msg);
    }
}
