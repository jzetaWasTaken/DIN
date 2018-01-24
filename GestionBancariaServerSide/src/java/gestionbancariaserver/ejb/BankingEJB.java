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
import gestionbancariaserver.exceptions.DeleteException;
import gestionbancariaserver.exceptions.ReadException;
import gestionbancariaserver.exceptions.UpdateException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.CreateException;
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
@TransactionManagement(value=TransactionManagementType.BEAN)
public class BankingEJB implements BankingEJBLocal {
    
    public static final String LOG_HEADER = "BankingEJB";
    private static final Logger LOGGER = Logger.getLogger("bankingappserverside");
    
    @Resource
    private UserTransaction userTransaction;
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Account> findAccountsByCustomerId(Long id) throws ReadException {
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
            throw new ReadException(e.getMessage());
        }
        if (accounts != null) {
            LOGGER.log(Level.INFO,
                    LOG_HEADER + ": {0} accounts found",
                    accounts.size());
        } else {
            LOGGER.info(LOG_HEADER + ": No accounts found");
        }
        return accounts;
    }

    @Override
    public List<Transaction> findTransactionsByAccount(Account account) throws ReadException {
        LOGGER.info(LOG_HEADER + ": Fetching transactions by account");
        List<Transaction> transactions = em
            .createNamedQuery("findTransactionsByAccount")
            .setParameter("account", account)
            .getResultList();
        /*
        try {
            LOGGER.info(LOG_HEADER + ": Fetching transactions by account");
            transactions = em.createNamedQuery("findTransactionsByAccount")
                    .setParameter("account", account)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception fetching transactions by account",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        if (transactions != null) {
            LOGGER.log(Level.INFO,
                    LOG_HEADER + ": {0} transactions found",
                    transactions.size());
        } else {
            LOGGER.info(LOG_HEADER + ": No transactions found");
        }
        */
        LOGGER.log(Level.INFO,
            LOG_HEADER + ": {0} transactions found",
            transactions.size());
        return transactions.isEmpty() ? null : transactions;
    }

    @Override
    public List<Transaction> findDepositsByAccount(Account account) throws ReadException {
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
            throw new ReadException(e.getMessage());
        }
        if (deposits != null) {
            LOGGER.log(Level.INFO,
                    LOG_HEADER + ": {0} deposits found",
                    deposits.size());
        } else {
            LOGGER.info(LOG_HEADER + ": No deposits found");
        }
        return deposits;
    }

    @Override
    public List<Transaction> findPaymentsByAccount(Account account) throws ReadException {
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
            throw new ReadException(e.getMessage());
        }
        if (payments != null) {
            LOGGER.log(Level.INFO,
                    LOG_HEADER + ": {0} payments found",
                    payments.size());
        } else {
            LOGGER.info(LOG_HEADER + ": No payments found");
        }
        return payments;
    }

    @Override
    public List<Transaction> findTransfersByAccount(Account account) throws ReadException {
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
            throw new ReadException(e.getMessage());
        }
        if (transfers != null) {
            LOGGER.log(Level.INFO,
                    LOG_HEADER + ": {0} transfers found",
                    transfers.size());
        } else {
            LOGGER.info(LOG_HEADER + ": No transfers found");
        }
        return transfers;
    }

    @Override
    public void createCustomer(Customer customer, Credential credential) {
        LOGGER.info(LOG_HEADER + ": Creating customer");
        try {
            credential.setCreatedOn(new Date());
            credential.setPassw(encryptPassw(credential));
            LOGGER.info(LOG_HEADER + ": Setting user credentials");
            customer.setCredentials(credential);
            em.persist(customer);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception in customer creation",
                    e.getMessage());
            
        }
        LOGGER.info(LOG_HEADER + ": Customer created");
    }

    @Override
    public void createAccount(Account account, List<Customer> customers) throws CreateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createTransaction(Transaction transaction) throws CreateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteCustomer(Customer customer) throws DeleteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAccount(Account account) throws DeleteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateAccount(Account account) throws UpdateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void updateCredential(Credential credential) throws UpdateException {
        
    }

    private String encryptPassw(Credential credential) throws NoSuchAlgorithmException {
        byte [] passwBytes = credential.getPassw().getBytes();
        MessageDigest md = MessageDigest.getInstance(MessageConstants.SHA_512);
        md.update(passwBytes);
        StringBuilder sb = new StringBuilder();
        for (byte passwByte : passwBytes) {
            sb.append(String.format("%02x", passwByte & 0xff));
        }
        return sb.toString();
    }
}
