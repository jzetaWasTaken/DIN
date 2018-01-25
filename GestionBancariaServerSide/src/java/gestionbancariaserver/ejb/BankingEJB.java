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
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

/**
 *
 * @author ubuntu
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.BEAN)
public class BankingEJB implements BankingEJBLocal {

    public static final String LOG_HEADER = "BankingEJB";
    private static final Logger LOGGER = Logger.getLogger("bankingappserverside");

    @Resource
    private UserTransaction userTransaction;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Account> findAccountsByCustomerId(Long id) throws EJBException {
        List<Account> accounts = null;
        try {
            LOGGER.info(LOG_HEADER + ": Fetching accounts by customer");
            accounts = em.createNamedQuery("findAccountsByCustomerId")
                    .setParameter("id", id)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception fetching accounts by customer. {0}",
                    e.getMessage());
            throw new EJBException(e.getMessage());
        }
        LOGGER.log(Level.INFO,
                LOG_HEADER + ": {0} accounts found",
                accounts.size());
        return accounts;
    }

    @Override
    public List<Transaction> findTransactionsByAccount(Account account) throws EJBException {
        List<Transaction> transactions = null;
        try {
            LOGGER.info(LOG_HEADER + ": Fetching transactions by account");
            transactions = em.createNamedQuery("findTransactionsByAccount")
                    .setParameter("account", account)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception fetching transactions by account. {0}",
                    e.getMessage());
            throw new EJBException(e.getMessage());
        }
        LOGGER.log(Level.INFO,
                LOG_HEADER + ": {0} transactions found",
                transactions.size());
        return transactions;
    }

    @Override
    public List<Transaction> findDepositsByAccount(Account account) throws EJBException {
        List<Transaction> deposits = null;
        try {
            LOGGER.info(LOG_HEADER + ": Fetching deposits by account");
            deposits = em.createNamedQuery("findDepositsByAccount")
                    .setParameter("account", account)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception fetching deposits by account. {0}",
                    e.getMessage());
            throw new EJBException(e.getMessage());
        }
        LOGGER.log(Level.INFO,
                LOG_HEADER + ": {0} deposits found",
                deposits.size());
        return deposits;
    }

    @Override
    public List<Transaction> findPaymentsByAccount(Account account) throws EJBException {
        List<Transaction> payments = null;
        try {
            LOGGER.info(LOG_HEADER + ": Fetching payments by account");
            payments = em.createNamedQuery("findPaymentsByAccount")
                    .setParameter("account", account)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception fetching payments by account. {0}",
                    e.getMessage());
            throw new EJBException(e.getMessage());
        }
        LOGGER.log(Level.INFO,
                LOG_HEADER + ": {0} payments found",
                payments.size());
        return payments;
    }

    @Override
    public List<Transaction> findTransfersByAccount(Account account) throws EJBException {
        List<Transaction> transfers = null;
        try {
            LOGGER.info(LOG_HEADER + ": Fetching transfers by account");
            transfers = em.createNamedQuery("findTransfersByAccount")
                    .setParameter("account", account)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception fetching transfers by account. {0}",
                    e.getMessage());
            throw new EJBException(e.getMessage());
        }
        LOGGER.log(Level.INFO,
                LOG_HEADER + ": {0} transfers found",
                transfers.size());
        return transfers;
    }

    @Override
    public Long findCustomerIdByLogin(String login, String passw) throws EJBException {
        Long id = null;
        List<Object[]> list = null;
        try {
            LOGGER.info(LOG_HEADER + ": Fetching customer ID by login");
            list = em.createNamedQuery("findCustomerIdByLogin")
                    .setParameter("login", login)
                    .setParameter("passw",passw)
                    .getResultList();
            if (list.size() == 1) {
                id = (Long) list.get(0)[0];
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception fetching customer ID by login. {0}",
                    e.getMessage());
            throw new EJBException(e.getMessage());
        }
        LOGGER.log(Level.INFO,
                LOG_HEADER + ": {0} IDs found",
                list.size());
        return id;
    }

    @Override
    public Customer findCustomerById(Long id) throws EJBException {
        Customer customer = null;
        try {
            LOGGER.info(LOG_HEADER + ": Fetching customer by ID");
            customer = (Customer) em.createNamedQuery("findCustomerById")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception fetching customer by ID. {0}",
                    e.getMessage());
            throw new EJBException(e.getMessage());
        }
        LOGGER.info(LOG_HEADER + ": Customer found");
        return customer;
    }
    
    @Override
    public void createCustomer(Customer customer) throws EJBException {
        try {
            LOGGER.info(LOG_HEADER + ": Creating customer");
            customer.getCredentials().setCreatedOn(new Date());
            LOGGER.info(LOG_HEADER + ": Setting user credentials");
            em.persist(customer);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception in customer creation. {0}",
                    e.getMessage());
            throw new EJBException(e.getMessage());
        }
        LOGGER.info(LOG_HEADER + ": Customer created");
    }

    @Override
    public void createAccount(Account account) throws EJBException {
        try {
            LOGGER.info(LOG_HEADER + ": Creating account");
            em.persist(account);
            LOGGER.info(LOG_HEADER + ": Account created");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception in account creation. {0}",
                    e.getMessage());
            throw new EJBException(e.getMessage());
        }
    }
    
    @Override
    @SuppressWarnings("UseSpecificCatch")
    public void makeDeposit(Transaction transaction) throws EJBException {
        LOGGER.info(LOG_HEADER + ": Making money deposit");
        try {
            userTransaction.begin();
            Account account = transaction.getAccount();
            BigDecimal newBalance = account.getBalance().add(transaction.getAmount());
            account.setBalance(newBalance);
            transaction.setBalance(newBalance);
            transaction.setTimeStamp(new Date());
            em.merge(account);
            em.persist(transaction);
            userTransaction.commit();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception making deposit. {0}",
                    e.getMessage());
            throw new EJBException(e.getMessage());
        }
    }

    @Override
    public void makePayment(Transaction transaction) throws EJBException {
    }

    @Override
    public void makeTransfer(Transaction transaction) throws EJBException {
    }
    
    @Override
    public void deleteCustomer(Customer customer) throws EJBException {
        LOGGER.info(LOG_HEADER + ": Deleting customer");
        try {
            em.remove(customer);
            LOGGER.info(LOG_HEADER + "Customer deleted");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception deleting customer. {0}",
                    e.getMessage());
            throw new EJBException(e.getMessage());
        }
    }

    @Override
    public void deleteAccount(Account account) throws EJBException {
        LOGGER.info(LOG_HEADER + ": Deleting account");
        try {
            em.remove(account);
            LOGGER.info(LOG_HEADER + ": Account deleted");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception deleting account. {0}",
                    e.getMessage());
            throw new EJBException(e.getMessage());
        }
    }
    
    @Override
    public void updateCustomer(Customer customer) throws EJBException {
        LOGGER.info(LOG_HEADER + ": Updating customer");
        try {
            em.merge(customer);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + "Exception updating customer. {0}",
                    e.getMessage());
            throw new EJBException(e.getMessage());
        }
    }

    @Override
    public void updateAccount(Account account) throws EJBException {
        LOGGER.info(LOG_HEADER + ": Updating account");
        try {
            em.merge(account);
            LOGGER.info(LOG_HEADER + ": Account updated");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception updating account. {0}",
                    e.getMessage());
            throw new EJBException(e.getMessage());
        }
    }

    @Override
    public void updateCredential(Credential credential) throws EJBException {
        LOGGER.info(LOG_HEADER + ": Updating credentials");
        try {
            credential.setLastModifiedOn(new Date());
            em.merge(credential);
            LOGGER.info(LOG_HEADER + ": Credentials updated");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception updating credentials. {0}",
                    e.getMessage());
            throw new EJBException(e.getMessage());
        }
    }
    
    @Override
    public void updateSignedIn(Credential credential) {
        LOGGER.info(LOG_HEADER + ": Updating customer last sign in date");
        try {
            credential.setLastSignedIn(new Date());
            em.merge(credential);
            LOGGER.info(LOG_HEADER + ": Last signed in date updated");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception updating las signed in date. {0}",
                    e.getMessage());
            throw new EJBException(e.getMessage());
        }
    }
}
