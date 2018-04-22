/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.exception;

/**
 * Represents an application business logic exception involving 
 * {@link gestionbancariaserver.entity.Customer}. Thrown from 
 * {@link gestionbancariaserver.ejb.BankingEJB} when no customers are found.
 * 
 * @author Jon Zaballa Zarzosa
 * @version 1.0, 17 Feb 2018
 */
public class NoCustomerException extends Exception {

    /**
     * Creates a new instance of <code>NoCustomerException</code> without detail
     * message.
     */
    public NoCustomerException() {
    }

    /**
     * Constructs an instance of <code>NoCustomerException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NoCustomerException(String msg) {
        super(msg);
    }
}
