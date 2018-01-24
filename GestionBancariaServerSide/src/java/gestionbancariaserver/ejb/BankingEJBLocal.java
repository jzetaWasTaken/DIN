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
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Local;

/**
 *
 * @author ubuntu
 */
@Local
public interface BankingEJBLocal {
        
    public List<Account> findAccountsByCustomerId(Long id) throws EJBException;
    public List<Transaction> findTransactionsByAccount(Account account) throws EJBException;
    public List<Transaction> findDepositsByAccount(Account account) throws EJBException;
    public List<Transaction> findPaymentsByAccount(Account account) throws EJBException;
    public List<Transaction> findTransfersByAccount(Account account) throws EJBException;
    public Long findCustomerIdByLogin(String login, String passw) throws EJBException;
    public Customer findCustomerById(Long id) throws EJBException;
    public void createCustomer(Customer customer) throws EJBException;
    public void createAccount(Account account) throws EJBException;
    public void createTransaction(Transaction transaction) throws EJBException;
    public void deleteCustomer(Customer customer) throws EJBException;
    public void deleteAccount(Account account) throws EJBException;
    public void updateAccount(Account account) throws EJBException;
    public void updateCredential(Credential credential) throws EJBException;
}
