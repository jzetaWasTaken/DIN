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
import javax.ejb.Stateless;

/**
 *
 * @author ubuntu
 */
@Stateless
public class BankingEJB implements BankingEJBLocal {

    @Override
    public List<Account> findAccountsByCustomerId(Long id) throws ReadException {
        return null;
    }

    @Override
    public List<Transaction> findTransactionsByAccount(Long id) throws ReadException {
        return null;
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
