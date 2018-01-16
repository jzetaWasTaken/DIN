/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionbancariaserver.ejb;

import gestionbancariaserver.entity.Account;
import gestionbancariaserver.entity.Credential;
import gestionbancariaserver.entity.Customer;
import gestionbancariaserver.entity.Transaction;
import gestionbancariaserver.exceptions.ReadException;
import gestionbancariaserver.exceptions.DeleteException;
import gestionbancariaserver.exceptions.UpdateException;
import java.util.List;
import javax.ejb.CreateException;
import javax.ejb.Local;

/**
 *
 * @author ubuntu
 */
@Local
public interface BankingEJBLocal {
        
    public List<Account> findAccountsByCustomerId(Long id) throws ReadException;
    public List<Transaction> findTransactionsByAccount(Account account) throws ReadException;
    public List<Transaction> findDepositsByAccount(Account account) throws ReadException;
    public List<Transaction> findPaymentsByAccount(Account account) throws ReadException;
    public List<Transaction> findTransfersByAccount(Account account) throws ReadException;
    public void createCustomer(Customer customer, Credential credential) throws CreateException;
    public void createAccount(Account account) throws CreateException;
    public void createTransaction(Transaction transaction) throws CreateException;
    public void deleteCustomer(Customer customer) throws DeleteException;
    public void deleteAccount(Account account) throws DeleteException;
    public void updateAccount(Account account) throws UpdateException;
    public void updateCredential(Credential credential) throws UpdateException;
}
