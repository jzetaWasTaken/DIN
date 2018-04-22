/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.ejb;

import bank.management.entity.Account;
import bank.management.entity.AccountType;
import bank.management.entity.Credential;
import bank.management.entity.Customer;
import bank.management.entity.Transaction;
import bank.management.entity.TransactionType;
import bank.management.exception.CustomerLoginException;
import bank.management.exception.NoAccountException;
import bank.management.exception.NoCustomerException;
import bank.management.exception.NoTransactionException;
import bank.management.exception.NotEnoughFundsException;
import java.math.BigDecimal;
import java.sql.SQLException;
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
 * <p>Stateless EJB that contains the business logic of the application.</p>
 * <p>It implements {@link bank.management.ejb.BankingEJBLocal} interface</p>
 * 
 * @author Jon Zaballa Zarzosa
 * @version 1.0, 17 Feb 2018
 * @see Stateless
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@Transactional(rollbackOn = SQLException.class)
public class BankingEJB implements BankingEJBLocal {

    /**
     * String constant to start the logging messages
     */
    public static final String LOG_HEADER = "BankingEJB";
    /**
     * Application log
     */
    private static final Logger LOGGER = 
            Logger.getLogger("bankingappserverside");

    /**
     * Entity manager injection to handle ORM methods
     */
    @PersistenceContext(unitName = "GestionBancariaServerSidePU")
    private EntityManager em;

    /**
     * Fetches all accounts belonging to a given customer.
     * 
     * @param id    Customer's ID
     * @return      List of accounts
     * @throws NoAccountException
     *              If no accounts are found.
     * @throws Exception
     *              If anything goes wrong.
     * @see         bank.management.entity.Account
     * @see         bank.management.entity.Customer
     */
    @Override
    public List<Account> findAccountsByCustomerId(Long id) 
            throws Exception {
        LOGGER.info(LOG_HEADER + ": Fetching accounts by customer");
        System.out.println(em == null?"EM is null":"EM is not null");
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

    /**
     * Fetches all the transactions of a given account.
     * 
     * @param accountId Account ID
     * @return          List of Transactions
     * @throws NoTransactionException
     *                  If no transactions are found
     * @throws Exception 
     *                  If anything goes wrong
     * @see             bank.management.entity.Account
     * @see             bank.management.entity.Transaction
     */
    @Override
    public List<Transaction> findTransactionsByAccount(Long accountId) 
            throws Exception {
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
    
    /**
     * Fetches all deposits of a given account.
     * 
     * @param accountId Account ID
     * @return          List of deposits
     * @throws NoTransactionException
     *                  If no deposits are found
     * @throws Exception 
     *                  If anything goes wrong
     * @see             bank.management.entity.Account
     * @see             bank.management.entity.Transaction
     * @see             gestionbancariaserver.entity.TransactionType#DEPOSIT
     */
    @Override
    public List<Transaction> findDepositsByAccount(Long accountId) 
            throws Exception {
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

    /**
     * Fetches all payments of a given account.
     * 
     * @param accountId Account ID
     * @return          List of payments
     * @throws NoTransactionException
     *                  If no payments are found
     * @throws Exception 
     *                  If anything goes wrong
     * @see             bank.management.entity.Account
     * @see             bank.management.entity.Transaction
     * @see             gestionbancariaserver.entity.TransactionType#PAYMENT
     */
    @Override
    public List<Transaction> findPaymentsByAccount(Long accountId)
            throws Exception {
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

    /**
     * Fetches all transfers of a given account.
     * 
     * @param accountId Account ID
     * @return          List of transfers
     * @throws NoTransactionException
     *                  If no transfers are found
     * @throws Exception 
     *                  If anything goes wrong
     * @see             bank.management.entity.Account
     * @see             bank.management.entity.Transaction
     * @see             gestionbancariaserver.entity.TransactionType#TRANSFER
     */
    @Override
    public List<Transaction> findTransfersByAccount(Long accountId) 
            throws Exception {
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

    /**
     * Fetches customers with a given login.
     *  
     * @param login The NIF of the customer
     * @return      List of customers
     * @throws NoCustomerException
     *              If no customers are found
     * @throws Exception 
     *              If anything goes wrong
     * @see         bank.management.entity.Customer
     * @see         gestionbancariaserver.entity.Customer#credentials
     * @see         bank.management.entity.Credential
     * @see         gestionbancariaserver.entity.Credential#login
     */
    @Override
    public List<Customer> findCustomersByLogin(String login) 
            throws Exception {
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
    
    /**
     * Validates customer's password.
     * 
     * @param id        Customer's ID
     * @param password  Customer's password
     * @return          Customer that is signing in
     * @throws CustomerLoginException
     *                  If the password is not valid
     * @throws Exception 
     *                  If anything goes wrong
     * @see             bank.management.entity.Customer
     * @see             gestionbancariaserver.entity.Customer#credentials
     * @see             bank.management.entity.Credential
     * @see             gestionbancariaserver.entity.Credential#password
     */
    @Override
    public Customer authenticateCustomer(Long id, String password) 
            throws Exception {
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
    
    /**
     * Creates a new customer.
     * 
     * @param customer  The customer to be created
     * @return          Created customer
     * @throws Exception 
     *                  If anything goes wrong
     * @see             bank.management.entity.Customer
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Customer createCustomer(Customer customer) throws Exception {
        LOGGER.info(LOG_HEADER + ": Creating customer");
        customer.getCredentials().setCreatedOn(new Date());
        System.out.println(customer == null? "Null":"Still not null");
        System.out.println(em == null?"EM is null":"EM is not null");
        em.persist(customer);
        em.flush();
        em.refresh(customer);
        LOGGER.info(LOG_HEADER + ": Customer created");
        return customer;
    }

    /**
     * Creates a new account.
     * 
     * @param account   Account to be created
     * @return          Created account
     * @throws Exception 
     *                  If anything goes wrong
     * @see             bank.management.entity.Account
     */
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
    
    /**
     * Make a new deposit in a given account.
     * 
     * @param transaction   The deposit to be made
     * @return              Made deposit
     * @throws Exception 
     *                      If anything goes wrong
     * @see                 bank.management.entity.Transaction
     * @see                 gestionbancariaserver.entity.Transaction#account
     * @see                 gestionbancariaserver.entity.TransactionType#DEPOSIT
     */
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

    /**
     * Make a new payment from a given account.
     * 
     * @param transaction   The payment to be made
     * @return              Made payment
     * @throws NotEnoughFundsException
     *                      If the account does not have enough funds
     * @throws Exception 
     *                      If anything goes wrong
     * @see                 bank.management.entity.Transaction
     * @see                 gestionbancariaserver.entity.Transaction#account
     * @see                 gestionbancariaserver.entity.TransactionType#PAYMENT
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Transaction makePayment(Transaction transaction) 
            throws Exception {
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
    
    /**
     * Make a new transfer from one account to another.
     * 
     * @param transaction       The transfer to be made
     * @param accountToNumber   The recipient account number
     * @return                  Made transfer
     * @throws NoAccountException
     *                          If the recipient account is not found
     * @throws NotEnoughFundsException
     *                          If the sender account does not have enough funds
     * @throws Exception 
     *                          If anything goes wrong
     * @see                 bank.management.entity.Transaction
     * @see                 gestionbancariaserver.entity.Transaction#account
     * @see                 gestionbancariaserver.entity.TransactionType#TRANSFER
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Transaction makeTransfer(Transaction transaction, String accountToNumber) 
            throws Exception {
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
    
    /**
     * Deletes a customer.
     * 
     * @param customerId    The ID of the customer to delete
     * @throws NoCustomerException
     *                      If there is no customer matching the ID
     * @throws Exception 
     *                      If anything goes wrong
     * @see bank.management.entity.Customer
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteCustomer(Long customerId) 
            throws Exception {
        LOGGER.info(LOG_HEADER + ": Deleting customer");
        em.remove(findCustomerById(customerId));
        LOGGER.info(LOG_HEADER + "Customer deleted");
    }

    /**
     * Deletes an account.
     * 
     * @param accountId     The ID of the account to delete
     * @throws NoAccountException
     *                      If there is no account matching the ID
     * @throws Exception 
     *                      If anything goes wrong
     * @see bank.management.entity.Account
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteAccount(Long accountId) 
            throws Exception {
        LOGGER.info(LOG_HEADER + ": Deleting account");
        em.remove(findAccountById(accountId));
        LOGGER.info(LOG_HEADER + ": Account deleted");
    }
    
    /**
     * Updates customer's fields.
     * 
     * @param customer  Customer to update
     * @throws Exception 
     *                  If anything goes wrong
     * @see bank.management.entity.Customer
     * @see gestionbancariaserver.entity.Customer#credentials
     * @see bank.management.entity.Credential
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateCustomer(Customer customer) throws Exception {
        LOGGER.info(LOG_HEADER + ": Updating customer");
        em.merge(customer);
        LOGGER.info(LOG_HEADER + ": Customer updated");
    }
    
    /**
     * Updates account fields.
     * 
     * @param account   Account to update
     * @throws Exception 
     *                  If anything goes wrong
     * @see bank.management.entity.Account
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateAccount(Account account) throws Exception {
        LOGGER.info(LOG_HEADER + ": Updating account");
        em.merge(account);
        LOGGER.info(LOG_HEADER + ": Account updated");
    }

    /**
     * Updates customer password.
     * 
     * @param credential    Customer credentials
     * @throws Exception    
     *                      If anything goes wrong
     * @see bank.management.entity.Credential
     * @see gestionbancariaserver.entity.Credential#password
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updatePassword(Credential credential) throws Exception {
        LOGGER.info(LOG_HEADER + ": Updating credentials");
        credential.setLastModifiedOn(new Date());
        em.merge(credential);
        LOGGER.info(LOG_HEADER + ": Credentials updated");
    }
    
    /**
     * Update customer's last access.
     * 
     * @param customer  Customer accessing the system
     * @throws Exception 
     *                  If anything goes wrong
     * @see bank.management.entity.Customer
     * @see gestionbancariaserver.entity.Customer#credentials
     * @see gestionbancariaserver.entity.Credential#lastSignedIn
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    private void updateLastAccess(Customer customer) throws Exception {
        LOGGER.info(LOG_HEADER + ": Updating customer last access date");
        customer.getCredentials().setLastSignedIn(new Date());
        em.merge(customer);
        LOGGER.info(LOG_HEADER + ": Last access updated");
    }
    
    /**
     * Checks if there are enough funds in the account balance.
     * 
     * @param account   Account to check
     * @throws NotEnoughFundsException 
     *                  If there are not enough funds
     * @see gestionbancariaserver.entity.Account#balance
     * @see gestionbancariaserver.entity.Account#creditLine
     */
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
    
    /**
     * Fetches an account given the account number.
     * 
     * @param accountNumber Account number
     * @return              Account matching the number
     * @throws NoAccountException 
     *                      If no account matches the given number
     * @see bank.management.entity.Account
     * @see gestionbancariaserver.entity.Account#accountNumber
     */
    private Account findAccountByNumber(String accountNumber) throws NoAccountException {
        LOGGER.info(LOG_HEADER + ": Fetching account by ID");
        List<Account> accounts = em.createNamedQuery("findAccountByNumber")
            .setParameter("accountNumber", accountNumber)
            .getResultList();
        if (accounts.isEmpty()) 
            throw new NoAccountException("The account does not exist");
        LOGGER.info(LOG_HEADER + ": Account found");
        return accounts.get(0);
    }
    
    /**
     * Fetches customers given an ID.
     * 
     * @param id    Customer ID
     * @return      Customer matching the ID
     * @throws NoCustomerException 
     *              If no customer matches the ID
     * @see bank.management.entity.Customer
     * @see gestionbancariaserver.entity.Customer#id
     */
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
    
    /**
     * Fetches accounts given an ID
     * 
     * @param accountId Account ID
     * @return          Account matching the ID
     * @throws NoAccountException 
     *                  If no account matches the ID
     * @see bank.management.entity.Account
     * @see gestionbancariaserver.entity.Account#id
     */
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
