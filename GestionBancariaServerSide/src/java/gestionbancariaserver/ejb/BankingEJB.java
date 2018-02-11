/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionbancariaserver.ejb;

import gestionbancariaserver.entity.Account;
import gestionbancariaserver.entity.AccountType;
import gestionbancariaserver.entity.Credential;
import gestionbancariaserver.entity.Customer;
import gestionbancariaserver.entity.Transaction;
import gestionbancariaserver.entity.TransactionType;
import gestionbancariaserver.exception.CustomerLoginException;
import gestionbancariaserver.exception.NoAccountException;
import gestionbancariaserver.exception.NoCustomerException;
import gestionbancariaserver.exception.NoTransactionException;
import gestionbancariaserver.exception.NotEnoughFundsException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author ubuntu
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@Transactional(rollbackOn = Exception.class)
public class BankingEJB implements BankingEJBLocal {

    public static final String LOG_HEADER = "BankingEJB";
    private static final Logger LOGGER = 
            Logger.getLogger("bankingappserverside");

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Account> findAccountsByCustomerId(Long id) 
            throws NoAccountException, Exception {
        LOGGER.info(LOG_HEADER + ": Fetching accounts by customer");
        List<Account> accounts = em.createNamedQuery("findAccountsByCustomerId")
                .setParameter("id", id)
                .getResultList();
        if (accounts.isEmpty())
            throw new NoAccountException("Accounts not found");
        LOGGER.log(Level.INFO,
                LOG_HEADER + ": {0} accounts found",
                accounts.size());
        return accounts;
    }

    @Override
    public List<Transaction> findTransactionsByAccount(String accountId) 
            throws NoTransactionException, Exception {
        LOGGER.info(LOG_HEADER + ": Fetching transactions by account");
        List<Transaction> transactions = 
                em.createNamedQuery("findTransactionsByAccount")
                .setParameter("accountId", accountId)
                .getResultList();
        if (transactions.isEmpty())
            throw new NoTransactionException("No transactions found");
        LOGGER.log(Level.INFO,
                LOG_HEADER + ": {0} transactions found",
                transactions.size());
        return transactions;
    }

    @Override
    public List<Transaction> findDepositsByAccount(String accountId) 
            throws NoTransactionException, Exception {
        LOGGER.info(LOG_HEADER + ": Fetching deposits by account");
        List<Transaction> deposits = 
                em.createNamedQuery("findDepositsByAccount")
                .setParameter("accountId", accountId)
                .getResultList();
        if (deposits.isEmpty())
            throw new NoTransactionException("No deposits found");
        LOGGER.log(Level.INFO,
                LOG_HEADER + ": {0} deposits found",
                deposits.size());
        return deposits;
    }

    @Override
    public List<Transaction> findPaymentsByAccount(String accountId)
            throws NoTransactionException, Exception {
        LOGGER.info(LOG_HEADER + ": Fetching payments by account");
        List<Transaction> payments = 
                em.createNamedQuery("findPaymentsByAccount")
                .setParameter("accountId", accountId)
                .getResultList();
        if (payments.isEmpty())
            throw new NoTransactionException("No payments found");
        LOGGER.log(Level.INFO,
                LOG_HEADER + ": {0} payments found",
                payments.size());
        return payments;
    }

    @Override
    public List<Transaction> findTransfersByAccount(String accountId) 
            throws NoTransactionException, Exception {
        LOGGER.info(LOG_HEADER + ": Fetching transfers by account");
        List<Transaction> transfers = 
                em.createNamedQuery("findTransfersByAccount")
                .setParameter("accountId", accountId)
                .getResultList();
        if (transfers.isEmpty())
            throw new NoTransactionException("No transfers found");
        LOGGER.log(Level.INFO,
                LOG_HEADER + ": {0} transfers found",
                transfers.size());
        return transfers;
    }

    @Override
    public List<Customer> findCustomersByLogin(String login) 
            throws NoCustomerException, Exception {
        LOGGER.info(LOG_HEADER + ": Fetching customers by login");
        List<Customer> customers = em.createNamedQuery("findCustomersByLogin")
                    .setParameter("login", login)
                    .getResultList();
        if (customers.isEmpty())
            throw new NoCustomerException("No customer matches the login");
        LOGGER.log(Level.INFO,
                LOG_HEADER + ": {0} customers found",
                customers.size());
        return customers;
    }
    
    @Override
    public Customer authenticateCustomer(Long id, String password) 
            throws CustomerLoginException, Exception {
        LOGGER.info(LOG_HEADER + ": Authenticating user");
        List<Customer> customers = 
                em.createNamedQuery("findCustomerByIdPassword")
                .setParameter("id", id)
                .setParameter("password", password)
                .getResultList();
        if (customers.isEmpty()) 
            throw new CustomerLoginException("Invalid password");
        Customer customer = customers.get(0);
        updateLastAccess(customer);
        LOGGER.info(LOG_HEADER + ": Login successful");
        return customer;
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Customer createCustomer(Customer customer) throws Exception {
        LOGGER.info(LOG_HEADER + ": Creating customer");
        customer.getCredentials().setCreatedOn(new Date());
        em.persist(customer);
        em.flush();
        em.refresh(customer);
        LOGGER.info(LOG_HEADER + ": Customer created");
        return customer;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Account createAccount(Account account) throws Exception {
        LOGGER.info(LOG_HEADER + ": Creating account");
        account.setBeginBalanceDate(new Date());
        em.persist(account);
        em.flush();
        em.refresh(account);
        LOGGER.info(LOG_HEADER + ": Account created");
        return account;
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Transaction makeDeposit(Transaction transaction) throws Exception {
        LOGGER.info(LOG_HEADER + ": Making money deposit");
        Account account = transaction.getAccount();
        BigDecimal newBalance = 
                account.getBalance().add(transaction.getAmount());
        account.setBalance(newBalance);
        em.merge(account);
        em.flush();
        transaction.setBalance(newBalance);
        transaction.setTimeStamp(new Date());
        transaction.setType(TransactionType.DEPOSIT);
        em.persist(transaction);
        em.flush();
        em.refresh(transaction);
        LOGGER.info(LOG_HEADER + ": Deposit made");
        return transaction;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Transaction makePayment(Transaction transaction) 
            throws NotEnoughFundsException, Exception {
        LOGGER.info(LOG_HEADER + ": Making money payment");
        Account account = transaction.getAccount();
        BigDecimal newBalance = 
                account.getBalance().subtract(transaction.getAmount());
        account.setBalance(newBalance);
        checkBalance(account);
        em.merge(account);
        em.flush();
        transaction.setBalance(newBalance);
        transaction.setTimeStamp(new Date());
        transaction.setType(TransactionType.PAYMENT);
        em.persist(transaction);
        em.flush();
        em.refresh(transaction);
        LOGGER.info(LOG_HEADER + ": Payment made");
        return transaction;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Transaction makeTransfer(Transaction transaction, String accountToNumber) 
            throws NoAccountException, NotEnoughFundsException, Exception {
        LOGGER.info(LOG_HEADER + ": Making money transfer");
        Account accountTo =  findAccountByNumber(accountToNumber);
        Account accountFrom = transaction.getAccount();
        BigDecimal newBalance = 
                accountFrom.getBalance().subtract(transaction.getAmount());
        accountFrom.setBalance(newBalance);
        checkBalance(accountFrom);
        em.merge(accountFrom);
        em.flush();
        transaction.setBalance(newBalance);
        transaction.setTimeStamp(new Date());
        transaction.setType(TransactionType.TRANSFER);
        em.persist(transaction);
        em.flush();
        newBalance = accountTo.getBalance().add(transaction.getAmount());
        accountTo.setBalance(newBalance);
        em.merge(accountTo);
        em.flush();
        transaction = new Transaction(
                new Date(),
                transaction.getAmount(), 
                newBalance, 
                transaction.getDescription(), 
                TransactionType.DEPOSIT, 
                accountTo);
        em.persist(transaction);
        em.flush();
        em.refresh(transaction);
        LOGGER.info(LOG_HEADER + ": Transfer made");
        return transaction;
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteCustomer(Long customerId) 
            throws NoCustomerException, Exception {
        LOGGER.info(LOG_HEADER + ": Deleting customer");
        em.remove(findCustomerById(customerId));
        LOGGER.info(LOG_HEADER + "Customer deleted");
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteAccount(Long accountId) 
            throws NoAccountException, Exception {
        LOGGER.info(LOG_HEADER + ": Deleting account");
        em.remove(findAccountById(accountId));
        LOGGER.info(LOG_HEADER + ": Account deleted");
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateCustomer(Customer customer) throws Exception {
        LOGGER.info(LOG_HEADER + ": Updating customer");
        em.merge(customer);
        LOGGER.info(LOG_HEADER + ": Customer updated");
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateAccount(Account account) throws Exception {
        LOGGER.info(LOG_HEADER + ": Updating account");
        em.merge(account);
        LOGGER.info(LOG_HEADER + ": Account updated");
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updatePassword(Credential credential) throws Exception {
        LOGGER.info(LOG_HEADER + ": Updating credentials");
        credential.setLastModifiedOn(new Date());
        em.merge(credential);
        LOGGER.info(LOG_HEADER + ": Credentials updated");
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    private void updateLastAccess(Customer customer) throws Exception {
        LOGGER.info(LOG_HEADER + ": Updating customer last access date");
        customer.getCredentials().setLastSignedIn(new Date());
        em.merge(customer);
        LOGGER.info(LOG_HEADER + ": Last access updated");
    }
    
    private void checkBalance(Account account) throws NotEnoughFundsException {
        LOGGER.info(LOG_HEADER + ": Checking account balance");
        BigDecimal balance;
        if (account.getType().equals(AccountType.CREDIT)) {
            balance = account.getBalance().add(account.getCreditLine());
        } else {
            balance = account.getBalance();
        }
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new NotEnoughFundsException("Not enough funds");
        }
    }

    private Account findAccountByNumber(String accountTo) throws NoAccountException {
        LOGGER.info(LOG_HEADER + ": Fetching account by ID");
        List<Account> accounts = em.createNamedQuery("findAccountByNumber")
            .setParameter("accountNumber", accountTo)
            .getResultList();
        if (accounts.isEmpty()) 
            throw new NoAccountException("The account does not exist");
        LOGGER.info(LOG_HEADER + ": Account found");
        return accounts.get(0);
    }
    
    private Customer findCustomerById(Long id) throws NoCustomerException {
        LOGGER.info(LOG_HEADER + ": Fetching customer by ID");
        List<Customer> customers = em.createNamedQuery("findCustomerById")
                .setParameter("id", id)
                .getResultList();
        if (customers.isEmpty()) 
            throw new NoCustomerException("Customer not found");
        LOGGER.info(LOG_HEADER + ": Customer found");
        return customers.get(0);
    }

    private Object findAccountById(Long accountId) throws NoAccountException {
        LOGGER.info(LOG_HEADER + ": Fetching account by ID");
        List<Account> accounts = em.createNamedQuery("findAccountById")
            .setParameter("accountId", accountId)
            .getResultList();
        if (accounts.isEmpty()) 
            throw new NoAccountException("The account does not exist");
        LOGGER.info(LOG_HEADER + ": Account found");
        return accounts.get(0);
    }
}
