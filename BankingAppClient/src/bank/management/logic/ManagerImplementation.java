/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.logic;

import bank.management.exception.BankServerException;
import bank.management.exception.ManagerException;
import bank.management.rest.BankingRESTClient;
import bank.management.ui.model.AccountBean;
import bank.management.ui.model.CustomerBean;
import bank.management.ui.model.TransactionBean;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author jon
 */
public class ManagerImplementation implements Manager {
    
    private final BankingRESTClient restClient;
    private static final Logger LOG = 
            Logger.getLogger(ManagerImplementation.class.getName());
    
    public ManagerImplementation() {
        restClient = new BankingRESTClient();
    }

    @Override
    public Collection<AccountBean> getCustomerAccounts(String customerId) 
            throws ManagerException {
        LOG.info("Getting customer accounts from REST");
        Collection<AccountBean> accounts = null;
        try {
            accounts =restClient.findAccountsByCustomer(
                    new GenericType<Collection<AccountBean>>() {}, 
                    customerId
            );
        } catch (BankServerException e) {
            throw new ManagerException(e.getMessage());
        } catch (Exception e) {
            throw new ManagerException(e.getMessage());
        }
        LOG.log(
            Level.INFO,
            "{0} accounts found",
            accounts.size()
        );
        return accounts;
    }

    @Override
    public Collection<CustomerBean> getCustomer(String login) 
            throws ManagerException {
        LOG.info("Getting customers from REST");
        // TODO validate customer login
        Collection<CustomerBean> customers = null;
        try {
            customers =restClient.findCustomerByLogin(
                    new GenericType<Collection<CustomerBean>>() {}, 
                    login
            );
        } catch (BankServerException e) {
            throw new ManagerException(e.getMessage());
        } catch (Exception e) {
            throw new ManagerException(e.getMessage());
        }
        LOG.log(
            Level.INFO,
            "{0} customers found",
            customers.size()
        );
        return customers;
    }

    @Override
    public Collection<TransactionBean> getAccountTransactions(String accountId) 
            throws ManagerException {
        LOG.info("Getting transactions from REST");
        Collection<TransactionBean> transactions = null;
        try {
            transactions =restClient.findTransactionsByAccount(
                    new GenericType<Collection<TransactionBean>>() {}, 
                    accountId
            );
        } catch (BankServerException e) {
            throw new ManagerException(e.getMessage());
        } catch (Exception e) {
            throw new ManagerException(e.getMessage());
        }
        LOG.log(
            Level.INFO,
            "{0} transactions found",
            transactions.size()
        );
        return transactions;
    }

    @Override
    public Collection<TransactionBean> getAccountTransfers(String accountId) 
            throws ManagerException {
        LOG.info("Getting transfers from REST");
        Collection<TransactionBean> transfers = null;
        try {
            transfers =restClient.findTransfersByAccount(
                    new GenericType<Collection<TransactionBean>>() {}, 
                    accountId
            );
        } catch (BankServerException e) {
            throw new ManagerException(e.getMessage());
        } catch (Exception e) {
            throw new ManagerException(e.getMessage());
        }
        LOG.log(
            Level.INFO,
            "{0} transfers found",
            transfers.size()
        );
        return transfers;
    }

    @Override
    public Collection<TransactionBean> getAccountPayments(String accountId) 
            throws ManagerException {
        LOG.info("Getting payments from REST");
        Collection<TransactionBean> payments = null;
        try {
            payments =restClient.findPaymentsByAccount(
                    new GenericType<Collection<TransactionBean>>() {}, 
                    accountId
            );
        } catch (BankServerException e) {
            throw new ManagerException(e.getMessage());
        } catch (Exception e) {
            throw new ManagerException(e.getMessage());
        }
        LOG.log(
            Level.INFO,
            "{0} payments found",
            payments.size()
        );
        return payments;
    }

    @Override
    public Collection<TransactionBean> getAccountDeposits(String accountId)
            throws ManagerException {
        LOG.info("Getting deposits from REST");
        Collection<TransactionBean> deposits = null;
        try {
            deposits =restClient.findDepositsByAccount(
                    new GenericType<Collection<TransactionBean>>() {}, 
                    accountId
            );
        } catch (BankServerException e) {
            throw new ManagerException(e.getMessage());
        } catch (Exception e) {
            throw new ManagerException(e.getMessage());
        }
        LOG.log(
            Level.INFO,
            "{0} deposits found",
            deposits.size()
        );
        return deposits;
    }

    @Override
    public CustomerBean authenticate(String id, String password)
            throws ManagerException {
        LOG.info("Authenticating user from REST");
        CustomerBean customer = null;
        try {
            customer =restClient.authenticateCustomer(
                    new GenericType<CustomerBean>() {}, 
                    id,
                    password
            );
        } catch (BankServerException e) {
            throw new ManagerException(e.getMessage());
        } catch (Exception e) {
            throw new ManagerException(e.getMessage());
        }
        LOG.log(
            Level.INFO,
            "User found; ID: {0}",
            customer.getId()
        );
        return customer;
    }

    @Override
    public void createCustomer(CustomerBean customer) throws ManagerException {
        LOG.info("Creating customer");
        String response = null;
        try {
            response = restClient.createCustomer(customer);
        } catch (BankServerException e) {
            throw new ManagerException(e.getMessage());
        } catch (Exception e) {
            throw new ManagerException(e.getMessage());
        }
        LOG.info(response);
    }

    @Override
    public void createAccount(AccountBean account) throws ManagerException {
        LOG.info("Creating account");
        String response = null;
        try {
            response = restClient.createAccount(account);
        } catch (BankServerException e) {
            throw new ManagerException(e.getMessage());
        } catch (Exception e) {
            throw new ManagerException(e.getMessage());
        }
        LOG.info(response);
    }

    @Override
    public void makeDeposit(TransactionBean deposit) throws ManagerException {
        LOG.info("Making deposit");
        String response = null;
        try {
            response = restClient.makeDeposit(deposit);
        } catch (BankServerException e) {
            throw new ManagerException(e.getMessage());
        } catch (Exception e) {
            throw new ManagerException(e.getMessage());
        }
        LOG.info(response);
    }

    @Override
    public void makePayment(TransactionBean payment) throws ManagerException {
        LOG.info("Making payment");
        String response = null;
        try {
            response = restClient.makePayment(payment);
        } catch (BankServerException e) {
            throw new ManagerException(e.getMessage());
        } catch (Exception e) {
            throw new ManagerException(e.getMessage());
        }
        LOG.info(response);
    }

    @Override
    public void makeTransfer(TransactionBean transfer, String accountTo)
            throws ManagerException {
        LOG.info("Making transfer");
        String response = null;
        // TODO validate recipient account
        try {
            response = restClient.makeTransfer(transfer, accountTo);
        } catch (BankServerException e) {
            throw new ManagerException(e.getMessage());
        } catch (Exception e) {
            throw new ManagerException(e.getMessage());
        }
        LOG.info(response);
    }

    @Override
    public void updateAccount(AccountBean account) throws ManagerException {
        LOG.info("Updating account");
        try {
            restClient.updateAccount(account);
        } catch (BankServerException e) {
            throw new ManagerException(e.getMessage());
        } catch (Exception e) {
            throw new ManagerException(e.getMessage());
        }
        LOG.info("Account updated");
    }

    @Override
    public void updateCustomer(CustomerBean customer) throws ManagerException {
        LOG.info("Updating customer");
        try {
            restClient.updateCustomer(customer);
        } catch (BankServerException e) {
            throw new ManagerException(e.getMessage());
        } catch (Exception e) {
            throw new ManagerException(e.getMessage());
        }
        LOG.info("Customer updated");
    }

    @Override
    public void deleteCustomer(String customerId) throws ManagerException {
        LOG.info("Deleting customer");
        try {
            restClient.deleteCustomer(customerId);
        } catch (BankServerException e) {
            throw new ManagerException(e.getMessage());
        } catch (Exception e) {
            throw new ManagerException(e.getMessage());
        }
        LOG.info("Customer deleted");
    }

    @Override
    public void deleteAccount(String accountId) throws ManagerException {
        LOG.info("Deleting account");
        try {
            restClient.deleteAccount(accountId);
        } catch (BankServerException e) {
            throw new ManagerException(e.getMessage());
        } catch (Exception e) {
            throw new ManagerException(e.getMessage());
        }
        LOG.info("Account deleted");
    }
}
