/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionbancariaserver.ejb;

import gestionbancariaserver.entity.Account;
import gestionbancariaserver.entity.Transaction;
import gestionbancariaserver.exceptions.ReadException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ubuntu
 */
@Local
public interface BankingEJBLocal {
        
    public List<Account> findAccountsByCustomerId(Long id) throws ReadException;
    public List<Transaction> findTransactionsByAccount(Account account) throws ReadException;
    public List<Transaction> findDepositsByAccount(Long id) throws ReadException;
    public List<Transaction> findPaymentsByAccount(Long id) throws ReadException;
    public List<Transaction> findTransfersByAccount(Long id) throws ReadException;
    // TODO think about how to do login (see Credential and Customer entity)
}
