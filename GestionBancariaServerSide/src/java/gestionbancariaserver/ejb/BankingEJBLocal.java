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
    public List<Transaction> findTransactionsByAccount(String accountId) throws EJBException;
    public List<Transaction> findDepositsByAccount(String accountId) throws EJBException;
    public List<Transaction> findPaymentsByAccount(String accountId) throws EJBException;
    public List<Transaction> findTransfersByAccount(String accountId) throws EJBException;
    public Customer findCustomerByLogin(String login, String passw) throws EJBException;
    public void createCustomer(Customer customer) throws EJBException;
    public void createAccount(Account account) throws EJBException;
    public void makeDeposit(Transaction transaction) throws EJBException;
    public void makePayment(Transaction transaction) throws EJBException;
    public void makeTransfer(Transaction transaction, String accountToId) throws EJBException;
    public void deleteCustomer(Long customerId) throws EJBException;
    public void deleteAccount(String accountId) throws EJBException;
    public void updateCustomer(Customer custmer) throws EJBException;
    public void updateAccount(Account account) throws EJBException;
    public void updateCredential(Credential credential) throws EJBException;
    public void updateSignedIn(Credential credential) throws EJBException;
}
