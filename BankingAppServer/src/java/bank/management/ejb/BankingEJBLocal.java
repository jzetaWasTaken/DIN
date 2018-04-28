/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.ejb;

import bank.management.entity.Account;
import bank.management.entity.Credential;
import bank.management.entity.Customer;
import bank.management.entity.Transaction;
import bank.management.exception.CustomerLoginException;
import bank.management.exception.NoAccountException;
import bank.management.exception.NoCustomerException;
import bank.management.exception.NoTransactionException;
import bank.management.exception.NotEnoughFundsException;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Local;

/**
 * Local EJB interface with the declaration of the business logic of the 
 * application.
 * 
 * @author Jon Zaballa Zarzosa
 * @version 1.0, 17 Feb 2018
 * @see Local
 */
@Local
public interface BankingEJBLocal {
        
    public List<Account> findAccountsByCustomerId(Long id) 
            throws NoAccountException, SQLException;
    public List<Transaction> findTransactionsByAccount(Long accountId) 
            throws NoTransactionException, SQLException;
    public List<Transaction> findDepositsByAccount(Long accountId) 
            throws NoTransactionException, SQLException;
    public List<Transaction> findPaymentsByAccount(Long accountId) 
            throws NoTransactionException, SQLException;
    public List<Transaction> findTransfersByAccount(Long accountId) 
            throws NoTransactionException, SQLException;
    public List<Customer> findCustomersByLogin(String login) 
            throws NoCustomerException, SQLException;
    public Customer authenticateCustomer(Long id, String password) 
            throws CustomerLoginException, SQLException;
    public Customer createCustomer(Customer customer) throws SQLException;
    public Account createAccount(Account account) throws SQLException;
    public Transaction makeDeposit(Transaction transaction) throws SQLException;
    public Transaction makePayment(Transaction transaction) 
            throws NotEnoughFundsException, SQLException;
    public Transaction makeTransfer(Transaction transaction, String accountToId) 
            throws NotEnoughFundsException, NoAccountException, SQLException;
    public void deleteCustomer(Long customerId) 
            throws NoCustomerException, SQLException;
    public void deleteAccount(Long accountId) 
            throws NoAccountException, SQLException;
    public void updateCustomer(Customer custmer) throws SQLException;
    public void updateAccount(Account account) throws SQLException;
    public void updatePassword(Credential credential) throws SQLException;
}
