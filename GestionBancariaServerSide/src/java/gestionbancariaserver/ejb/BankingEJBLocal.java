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
import gestionbancariaserver.exception.AccountCreateException;
import gestionbancariaserver.exception.AccountDeleteException;
import gestionbancariaserver.exception.AccountFetchException;
import gestionbancariaserver.exception.AccountUpdateException;
import gestionbancariaserver.exception.CredentialUpdateException;
import gestionbancariaserver.exception.CustomerCreateException;
import gestionbancariaserver.exception.CustomerDeleteException;
import gestionbancariaserver.exception.CustomerLoginException;
import gestionbancariaserver.exception.CustomerUpdateException;
import gestionbancariaserver.exception.TransactionCreateException;
import gestionbancariaserver.exception.TransactionFetchException;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Local;

/**
 *
 * @author ubuntu
 */
@Local
public interface BankingEJBLocal {
        
    public List<Account> findAccountsByCustomerId(Long id) throws AccountFetchException;
    public List<Transaction> findTransactionsByAccount(String accountId) throws TransactionFetchException;
    public List<Transaction> findDepositsByAccount(String accountId) throws TransactionFetchException;
    public List<Transaction> findPaymentsByAccount(String accountId) throws TransactionFetchException;
    public List<Transaction> findTransfersByAccount(String accountId) throws TransactionFetchException;
    public Customer findCustomerByLogin(String login, String passw) throws CustomerLoginException;
    public void createCustomer(Customer customer) throws CustomerCreateException;
    public void createAccount(Account account) throws AccountCreateException;
    public void makeDeposit(Transaction transaction) throws TransactionCreateException;
    public void makePayment(Transaction transaction) throws TransactionCreateException;
    public void makeTransfer(Transaction transaction, String accountToId) throws TransactionCreateException;
    public void deleteCustomer(Long customerId) throws CustomerDeleteException;
    public void deleteAccount(String accountId) throws AccountDeleteException;
    public void updateCustomer(Customer custmer) throws CustomerUpdateException;
    public void updateAccount(Account account) throws AccountUpdateException;
    public void updateCredential(Credential credential) throws CredentialUpdateException;
    public void updateSignedIn(Credential credential) throws CredentialUpdateException;
}
