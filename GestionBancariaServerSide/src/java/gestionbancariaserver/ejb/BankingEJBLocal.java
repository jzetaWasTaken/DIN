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
import gestionbancariaserver.exception.CustomerLoginException;
import gestionbancariaserver.exception.NoAccountException;
import gestionbancariaserver.exception.NoCustomerException;
import gestionbancariaserver.exception.NoTransactionException;
import gestionbancariaserver.exception.NotEnoughFundsException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ubuntu
 */
@Local
public interface BankingEJBLocal {
        
    public List<Account> findAccountsByCustomerId(Long id) 
            throws NoAccountException, Exception;
    public List<Transaction> findTransactionsByAccount(String accountId) 
            throws NoTransactionException, Exception;
    public List<Transaction> findDepositsByAccount(String accountId) 
            throws NoTransactionException, Exception;
    public List<Transaction> findPaymentsByAccount(String accountId) 
            throws NoTransactionException, Exception;
    public List<Transaction> findTransfersByAccount(String accountId) 
            throws NoTransactionException, Exception;
    public List<Customer> findCustomersByLogin(String login) 
            throws NoCustomerException, Exception;
    public Customer authenticateCustomer(Long id, String password) 
            throws CustomerLoginException, Exception;
    public Customer createCustomer(Customer customer) throws Exception;
    public Account createAccount(Account account) throws Exception;
    public Transaction makeDeposit(Transaction transaction) throws Exception;
    public Transaction makePayment(Transaction transaction) 
            throws NotEnoughFundsException, Exception;
    public Transaction makeTransfer(Transaction transaction, String accountToId) 
            throws NoAccountException, NotEnoughFundsException, Exception;
    public void deleteCustomer(Long customerId) throws Exception;
    public void deleteAccount(Long accountId) throws NoAccountException, Exception;
    public void updateCustomer(Customer custmer) throws Exception;
    public void updateAccount(Account account) throws Exception;
    public void updatePassword(Credential credential) throws Exception;
}
