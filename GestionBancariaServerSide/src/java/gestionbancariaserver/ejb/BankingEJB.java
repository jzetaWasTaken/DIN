/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionbancariaserver.ejb;

import com.sun.xml.wss.impl.MessageConstants;
import gestionbancariaserver.entity.Account;
import gestionbancariaserver.entity.Credential;
import gestionbancariaserver.entity.Customer;
import gestionbancariaserver.entity.Transaction;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.CreateException;
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
                    LOG_HEADER + ": Exception fetching accounts by customer",
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
                    LOG_HEADER + ": Exception fetching transactions by account",
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
                    LOG_HEADER + ": Exception fetching deposits by account",
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
                    LOG_HEADER + ": Exception fetching payments by account",
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
                    LOG_HEADER + ": Exception fetching transfers by account",
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
                    LOG_HEADER + ": Exception fetching customer ID by login",
                    e.getMessage());
            throw new EJBException(e.getMessage());
        }
        LOGGER.log(Level.INFO,
                LOG_HEADER + ": {0} IDs found",
                list.size());
        return id;
    }

    
    // TODO
    @Override
    public Customer findCustomerById(Long id) throws EJBException {
        Customer customer = null;
        
        return customer;
    }
    
    @Override
    public void createCustomer(Customer customer) throws EJBException {
        LOGGER.info(LOG_HEADER + ": Creating customer");
        try {
            customer.getCredentials().setCreatedOn(new Date());
            LOGGER.info(LOG_HEADER + ": Setting user credentials");
            em.persist(customer);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception in customer creation",
                    e.getMessage());
            throw new EJBException(e.getMessage());
        }
        LOGGER.info(LOG_HEADER + ": Customer created");
    }

    @Override
    public void createAccount(Account account) throws EJBException {
    }

    @Override
    public void createTransaction(Transaction transaction) throws EJBException {
    }

    @Override
    public void deleteCustomer(Customer customer) throws EJBException {
    }

    @Override
    public void deleteAccount(Account account) throws EJBException {
    }

    @Override
    public void updateAccount(Account account) throws EJBException {
    }

    @Override
    public void updateCredential(Credential credential) throws EJBException {

    }
}
