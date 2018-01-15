/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionbancariaserver.ejb;

import gestionbancariaserver.entity.Account;
import gestionbancariaserver.entity.Transaction;
import gestionbancariaserver.exceptions.ReadException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.PathParam;

/**
 *
 * @author ubuntu
 */
@Stateless
public class BankingEJB implements BankingEJBLocal {
    
    private static final Logger LOGGER = Logger.getLogger("bankingappserverside");
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Account> findAccountsByCustomerId(Long id) throws ReadException {
        List<Account> accounts = null;
        try {
            LOGGER.info("BankingEJB: Fetching accounts by customer");
            accounts = em.createNamedQuery("findAccountsByCustomerId")
                    .setParameter("id", id)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,
                    "BankingEJB: Exception fetching accounts by customer",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        if (accounts != null) {
            LOGGER.log(Level.INFO,
                    "BankingEJB: {0} accounts found",
                    accounts.size());
        } else {
            LOGGER.info("BankingEJB: No accounts found");
        }
        return accounts;
    }

    @Override
    public List<Transaction> findTransactionsByAccount(Account account) throws ReadException {
        List<Transaction> transactions = null;
        try {
            LOGGER.info("BankingEJB: Fetching transactions by account");
            transactions = em.createNamedQuery("findTransactionsByAccount")
                    .setParameter("account", account)
                    .getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,
                    "BankingEJB: Exception fetching transactions by account",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return transactions;
    }

    @Override
    public List<Transaction> findDepositsByAccount(Long id) throws ReadException {
        return null;
    }

    @Override
    public List<Transaction> findPaymentsByAccount(Long id) throws ReadException {
        return null;
    }

    @Override
    public List<Transaction> findTransfersByAccount(Long id) throws ReadException {
        return null;
    }
}
