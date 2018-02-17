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
            throws Exception;
    public List<Transaction> findTransactionsByAccount(Long accountId) 
            throws Exception;
    public List<Transaction> findDepositsByAccount(Long accountId) 
            throws Exception;
    public List<Transaction> findPaymentsByAccount(Long accountId) 
            throws Exception;
    public List<Transaction> findTransfersByAccount(Long accountId) 
            throws Exception;
    public List<Customer> findCustomersByLogin(String login) 
            throws Exception;
    public Customer authenticateCustomer(Long id, String password) 
            throws Exception;
    public Customer createCustomer(Customer customer) throws Exception;
    public Account createAccount(Account account) throws Exception;
    public Transaction makeDeposit(Transaction transaction) throws Exception;
    public Transaction makePayment(Transaction transaction) 
            throws Exception;
    public Transaction makeTransfer(Transaction transaction, String accountToId) 
            throws Exception;
    public void deleteCustomer(Long customerId) throws Exception;
    public void deleteAccount(Long accountId) throws Exception;
    public void updateCustomer(Customer custmer) throws Exception;
    public void updateAccount(Account account) throws Exception;
    public void updatePassword(Credential credential) throws Exception;
}
