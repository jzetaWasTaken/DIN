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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author jon
 */
public class ManagerImplementation implements Manager {
    
    private static final Map SESSION = new HashMap<>();
    private final BankingRESTClient restClient;
    private static final Logger LOG = 
            Logger.getLogger(ManagerImplementation.class.getName());
    
    public ManagerImplementation() {
        restClient = new BankingRESTClient();
    }

    @Override
    public List<AccountBean> getCustomerAccounts(String customerId) 
            throws ManagerException {
        LOG.info("Getting customer accounts from REST");
        List<AccountBean> accounts = null;
        try {
            accounts =restClient.findAccountsByCustomer(
                    new GenericType<List<AccountBean>>() {}, 
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
    public List<CustomerBean> getCustomer(String login) 
            throws ManagerException {
        LOG.info("Getting customers from REST");
        // TODO validate customer login
        List<CustomerBean> customers = null;
        try {
            customers =restClient.findCustomerByLogin(
                    new GenericType<List<CustomerBean>>() {}, 
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
    public List<TransactionBean> getAccountTransactions(String accountId) 
            throws ManagerException {
        LOG.info("Getting transactions from REST");
        List<TransactionBean> transactions = null;
        try {
            transactions =restClient.findTransactionsByAccount(
                    new GenericType<List<TransactionBean>>() {}, 
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
    public List<TransactionBean> getAccountTransfers(String accountId) 
            throws ManagerException {
        LOG.info("Getting transfers from REST");
        List<TransactionBean> transfers = null;
        try {
            transfers =restClient.findTransfersByAccount(
                    new GenericType<List<TransactionBean>>() {}, 
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
    public List<TransactionBean> getAccountPayments(String accountId) 
            throws ManagerException {
        LOG.info("Getting payments from REST");
        List<TransactionBean> payments = null;
        try {
            payments =restClient.findPaymentsByAccount(
                    new GenericType<List<TransactionBean>>() {}, 
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
    public List<TransactionBean> getAccountDeposits(String accountId)
            throws ManagerException {
        LOG.info("Getting deposits from REST");
        List<TransactionBean> deposits = null;
        try {
            deposits =restClient.findDepositsByAccount(
                    new GenericType<List<TransactionBean>>() {}, 
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
    public String createCustomer(CustomerBean customer) throws ManagerException {
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
        return response;
    }

    @Override
    public String createAccount(AccountBean account) throws ManagerException {
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
        return response;
    }

    @Override
    public String makeDeposit(TransactionBean deposit) throws ManagerException {
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
        return response;
    }

    @Override
    public String makePayment(TransactionBean payment) throws ManagerException {
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
        return response;
    }

    @Override
    public String makeTransfer(TransactionBean transfer, String accountTo)
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
        return response;
    }

    @Override
    public boolean updateAccount(AccountBean account) throws ManagerException {
        LOG.info("Updating account");
        try {
            restClient.updateAccount(account);
        } catch (BankServerException e) {
            throw new ManagerException(e.getMessage());
        } catch (Exception e) {
            throw new ManagerException(e.getMessage());
        }
        LOG.info("Account updated");
        return true;
    }

    @Override
    public boolean updateCustomer(CustomerBean customer) throws ManagerException {
        LOG.info("Updating customer");
        try {
            restClient.updateCustomer(customer);
        } catch (BankServerException e) {
            throw new ManagerException(e.getMessage());
        } catch (Exception e) {
            throw new ManagerException(e.getMessage());
        }
        LOG.info("Customer updated");
        return true;
    }

    @Override
    public boolean deleteCustomer(String customerId) throws ManagerException {
        LOG.info("Deleting customer");
        try {
            restClient.deleteCustomer(customerId);
        } catch (BankServerException e) {
            throw new ManagerException(e.getMessage());
        } catch (Exception e) {
            throw new ManagerException(e.getMessage());
        }
        LOG.info("Customer deleted");
        return true;
    }

    @Override
    public boolean deleteAccount(String accountId) throws ManagerException {
        LOG.info("Deleting account");
        try {
            restClient.deleteAccount(accountId);
        } catch (BankServerException e) {
            throw new ManagerException(e.getMessage());
        } catch (Exception e) {
            throw new ManagerException(e.getMessage());
        }
        LOG.info("Account deleted");
        return true;
    }
    
    @Override 
    public Map getSession() {
        return this.SESSION;
    }
}
