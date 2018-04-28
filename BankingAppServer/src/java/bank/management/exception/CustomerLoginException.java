/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.exception;

import javax.ejb.ApplicationException;

/**
 * Represents an application business logic exception involving 
 * {@link gestionbancariaserver.entity.Customer} and
 * {@link gestionbancariaserver.entity.Customer}. Thrown from 
 * {@link gestionbancariaserver.ejb.BankingEJB} when the login process fails.
 * 
 * @author Jon Zaballa Zarzosa
 * @version 1.0, 17 Feb 2018
 */
@ApplicationException(rollback = true)
public class CustomerLoginException extends Exception {

    /**
     * Creates a new instance of <code>CustomerLoginException</code> without
     * detail message.
     */
    public CustomerLoginException() {
    }

    /**
     * Constructs an instance of <code>CustomerLoginException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CustomerLoginException(String msg) {
        super(msg);
    }
}
