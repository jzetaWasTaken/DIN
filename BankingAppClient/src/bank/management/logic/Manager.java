/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.logic;

import bank.management.exception.ManagerException;
import bank.management.ui.model.AccountBean;
import bank.management.ui.model.CustomerBean;
import bank.management.ui.model.TransactionBean;
import java.util.List;

/**
 *
 * @author ubuntu
 */
public interface Manager {
    
    public List<AccountBean> getCustomerAccounts(String customerId) throws ManagerException;
    
    public List<CustomerBean> getCustomer(String login) throws ManagerException;
    
    public List<TransactionBean> getAccountTransactions(String accountId) throws ManagerException;
    
    public List<TransactionBean> getAccountTransfers(String accountId) throws ManagerException;
    
    public List<TransactionBean> getAccountPayments(String accountId) throws ManagerException;
    
    public List<TransactionBean> getAccountDeposits(String accountId) throws ManagerException;
    
    public CustomerBean authenticate(String id, String password) throws ManagerException;
    
    public String createCustomer(CustomerBean customer) throws ManagerException;
    
    public String createAccount(AccountBean account) throws ManagerException;
    
    public void makeDeposit(TransactionBean deposit) throws ManagerException;
    
    public void makePayment(TransactionBean payment) throws ManagerException;
    
    public void makeTransfer(TransactionBean transfer, String accountTo) throws ManagerException;
    
    public void updateAccount(AccountBean account) throws ManagerException;
    
    public void updateCustomer(CustomerBean customer) throws ManagerException;
    
    public boolean deleteCustomer(String customerId) throws ManagerException;
    
    public boolean deleteAccount(String accountId) throws ManagerException;
}
