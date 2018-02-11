/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionbancariaserver.rest;

import gestionbancariaserver.ejb.BankingEJBLocal;
import gestionbancariaserver.entity.Account;
import gestionbancariaserver.entity.Credential;
import gestionbancariaserver.entity.Transaction;
import gestionbancariaserver.entity.Customer;
import gestionbancariaserver.exception.AccountException;
import gestionbancariaserver.exception.BankingBussinessException;
import gestionbancariaserver.exception.CustomerException;
import gestionbancariaserver.exception.CustomerLoginException;
import gestionbancariaserver.exception.CustomerUnauthorizedException;
import gestionbancariaserver.exception.EntityDeleteException;
import gestionbancariaserver.exception.NoAccountException;
import gestionbancariaserver.exception.NoCustomerException;
import gestionbancariaserver.exception.NoTransactionException;
import gestionbancariaserver.exception.NotEnoughFundsException;
import gestionbancariaserver.exception.TransactionException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author jon
 */
@Path("/banking")
public class BankingREST {
    
    private static final Logger LOGGER = Logger.getLogger("bankingappserverside");
    private static final String LOG_HEADER = "BankingREST";
    private static final String CUSTOMER_CREATED = "Customer created; id: %s";
    private static final String ACCOUNT_CREATED = "Account created; id: %s";
    private static final String TRANSACTION_MADE = "Transaction made; id: %s";

    @EJB
    private BankingEJBLocal ejb;
    
    @GET
    @Path("/accounts/{customerid}")
    @Produces(MediaType.APPLICATION_XML)
    public Response findAccountsByCustomer(@PathParam("customerid") Long id) {
        List<Account> accounts;
        GenericEntity entity;
        try {
            accounts = ejb.findAccountsByCustomerId(id);
            entity = new GenericEntity<List<Account>>(accounts) {};
        } catch (NoAccountException e) {
            LOGGER.log(
                    Level.INFO,
                    LOG_HEADER + ": No accounts found; customerId: {0}",
                    id);
            throw new AccountException(e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.severe(LOG_HEADER + ": Exception fetching accounts by customer");
            throw new BankingBussinessException(e.getMessage(), e.getCause());
        }
        return Response.ok(entity).build();
    }
    
    @GET
    @Path("/transactions/{accountid}")
    @Produces(MediaType.APPLICATION_XML)
    public Response findTransactionsByAccount(@PathParam("accountid") Long accountId) {
        List<Transaction> transactions;
        GenericEntity entity;
        try {
            transactions = ejb.findTransactionsByAccount(accountId);
            entity = new GenericEntity<List<Transaction>>(transactions) {};
        } catch (NoTransactionException e) {
            LOGGER.log(
                    Level.INFO,
                    LOG_HEADER + ": No transactions found; accountId: {0}",
                    accountId);
            throw new TransactionException(e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.severe(LOG_HEADER + ": Exception fetching transactions by account");
            throw new BankingBussinessException(e.getMessage(), e.getCause());
        }
        return Response.ok(entity).build();
    }
    
    @GET
    @Path("/deposits/{accountid}")
    @Produces(MediaType.APPLICATION_XML)
    public Response findDepositsByAccount(@PathParam("accountid") Long accountId) {
        List<Transaction> transactions;
        GenericEntity entity;
        try {
            transactions = ejb.findDepositsByAccount(accountId);
            entity = new GenericEntity<List<Transaction>>(transactions) {};
        } catch (NoTransactionException e) {
            LOGGER.log(
                    Level.INFO,
                    LOG_HEADER + ": No deposits found; accountId: {0}",
                    accountId);
            throw new TransactionException(e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.severe(LOG_HEADER + ": Exception fetching deposits by account");
            throw new BankingBussinessException(e.getMessage(), e.getCause());
        }
        return Response.ok(entity).build();
    }
    
    @GET
    @Path("/payments/{accountid}")
    @Produces(MediaType.APPLICATION_XML)
    public Response findPaymentsByAccount(@PathParam("accountid") Long accountId) {
        List<Transaction> transactions;
        GenericEntity entity;
        try {
            transactions = ejb.findPaymentsByAccount(accountId);
            entity = new GenericEntity<List<Transaction>>(transactions) {};
        } catch (NoTransactionException e) {
            LOGGER.log(
                    Level.INFO,
                    LOG_HEADER + ": No payments found; accountId: {0}",
                    accountId);
            throw new TransactionException(e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.severe(LOG_HEADER + ": Exception fetching payments by account");
            throw new BankingBussinessException(e.getMessage(), e.getCause());
        }
        return Response.ok(entity).build();
    }
    
    @GET
    @Path("/transfers/{accountid}")
    @Produces(MediaType.APPLICATION_XML)
    public Response findTransfersByAccount(@PathParam("accountid") Long accountId) {
        List<Transaction> transactions;
        GenericEntity entity;
        try {
            transactions = ejb.findTransfersByAccount(accountId);
            entity = new GenericEntity<List<Transaction>>(transactions) {};
        } catch (NoTransactionException e) {
            LOGGER.log(
                    Level.INFO,
                    LOG_HEADER + ": No payments found; accountId: {0}",
                    accountId);
            throw new TransactionException(e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.severe(LOG_HEADER + ": Exception fetching payments by account");
            throw new BankingBussinessException(e.getMessage(), e.getCause());
        }
        return Response.ok(entity).build();
    }
    
    @GET
    @Path("/login/{login}")
    @Produces(MediaType.APPLICATION_XML)
    public Response findCustomerByLogin(@PathParam("login") String login) {
        List<Customer> customers;
        GenericEntity entity;
        try {
            customers = ejb.findCustomersByLogin(login);
            entity = new GenericEntity<List<Customer>>(customers) {};
        } catch (NoCustomerException e) {
            LOGGER.log(
                    Level.INFO,
                    LOG_HEADER + ": No customers found; login: {0}",
                    login);
            throw new CustomerException(e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.severe(LOG_HEADER + ": Exception fetching customers by login");
            throw new BankingBussinessException(e.getMessage(), e.getCause());
        }
        return Response.ok(entity).build();
    }
    
    @GET
    @Path("/login/{id}-{password}")
    @Produces(MediaType.APPLICATION_XML)
    public Response authenticateCustomer(
            @PathParam("id") Long id, @PathParam("password") String password) {
        Customer customer;
        try {
            customer = ejb.authenticateCustomer(id, password);
        } catch (CustomerLoginException e) {
            LOGGER.log(
                    Level.INFO,
                    LOG_HEADER + ": Wrong pasword for user id: {0}",
                    id);
            throw new CustomerUnauthorizedException(e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.severe(LOG_HEADER + ": Exception authenticating customer");
            throw new BankingBussinessException(e.getMessage(), e.getCause());
        }
        return Response.ok(customer).build();
    }
    
    
    @POST
    @Path("/customers")
    @Consumes(MediaType.APPLICATION_XML)
    public Response createCustomer(Customer customer) {
        Customer newCustomer;
        try {
            newCustomer = ejb.createCustomer(customer);
        } catch (Exception e) {
            LOGGER.severe(LOG_HEADER + ": Exception creating customer");
            throw new BankingBussinessException(e.getMessage(), e.getCause());
        }
        return Response.status(Status.CREATED)
                .entity(String.format(
                        CUSTOMER_CREATED, 
                        Long.toString(newCustomer.getId())))
                .build();
    }
    
    @POST
    @Path("/accounts")
    @Consumes(MediaType.APPLICATION_XML)
    public Response createAccount(Account account) {
        Account newAccount;
        try {
            newAccount = ejb.createAccount(account);
        } catch (Exception e) {
            LOGGER.severe(LOG_HEADER + ": Exception creating account");
            throw new BankingBussinessException(e.getMessage(), e.getCause());
        }
        return Response.status(Status.CREATED)
                .entity(String.format(
                        ACCOUNT_CREATED,
                        Long.toString(newAccount.getId())))
                .build();
    }
    
    @POST
    @Path("/deposits")
    @Consumes(MediaType.APPLICATION_XML)
    public Response makeDeposit(Transaction transaction) {
        Transaction deposit;
        try {
            deposit = ejb.makeDeposit(transaction);
        } catch (Exception e) {
            LOGGER.severe(LOG_HEADER + ": Exception making deposit");
            throw new BankingBussinessException(e.getMessage(), e.getCause());
        }
        return Response.status(Status.CREATED)
                .entity(String.format(
                        TRANSACTION_MADE,
                        Long.toString(deposit.getId())))
                .build();
    }
    
    @POST
    @Path("/payments")
    @Consumes(MediaType.APPLICATION_XML)
    public Response makePayment(Transaction transaction) {
        Transaction payment;
        try {
            payment = ejb.makePayment(transaction);
        } catch (NotEnoughFundsException e) {
            LOGGER.log(
                    Level.INFO,
                    LOG_HEADER + ": Not enough funds; requested: {0}",
                    transaction.getAmount());
            throw new TransactionException(e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.severe(LOG_HEADER + ": Exception making payment");
            throw new BankingBussinessException(e.getMessage(), e.getCause());
        }
        return Response.status(Status.CREATED)
                .entity(String.format(
                        TRANSACTION_MADE,
                        Long.toString(payment.getId())))
                .build();
    }
    
    @POST
    @Path("/transfers/{accountto}")
    @Consumes(MediaType.APPLICATION_XML)
    public Response makeTransfer(Transaction transaction, @PathParam("accountto") String accountNumber) {
        Transaction transfer;
        try {
            transfer = ejb.makeTransfer(transaction, accountNumber);
        } catch (NoAccountException e) {
            LOGGER.log(
                    Level.INFO,
                    LOG_HEADER + ": No accounts found; accountNumber: {0}",
                    accountNumber);
            throw new AccountException(e.getMessage(), e);
        } catch (NotEnoughFundsException e) {
            LOGGER.log(
                    Level.INFO,
                    LOG_HEADER + ": Not enough funds; requested: {0}",
                    transaction.getAmount());
            throw new TransactionException(e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.severe(LOG_HEADER + ": Exception making transfer");
            throw new BankingBussinessException(e.getMessage(), e.getCause());
        }
        return Response.status(Status.CREATED)
                .entity(String.format(
                        TRANSACTION_MADE,
                        Long.toString(transfer.getId())))
                .build();
    }
    
    @DELETE
    @Path("/customers/delete/{customerid}")
    public Response deleteCustomer(@PathParam("customerid")Long customerId) {
        try {
            ejb.deleteCustomer(customerId);
        } catch (NoCustomerException e) {
            LOGGER.log(
                    Level.WARNING,
                    LOG_HEADER + ": Customer with id {0} no longer exists",
                    customerId);
            throw new EntityDeleteException(e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.severe(LOG_HEADER + ": Exception deleting customer");
            throw new BankingBussinessException(e.getMessage(), e.getCause());
        }
        return Response.status(Status.NO_CONTENT).build();
    }
    
    @DELETE
    @Path("/accounts/delete/{accountid}")
    public Response deleteAccount(@PathParam("accountid")Long accountId) {
        try {
            ejb.deleteAccount(accountId);
        } catch (NoAccountException e) {
            LOGGER.log(
                    Level.WARNING,
                    LOG_HEADER + ": Account with id {0} no longer exists",
                    accountId);
            throw new EntityDeleteException(e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.severe(LOG_HEADER + ": Exception deleting account");
            throw new BankingBussinessException(e.getMessage(), e.getCause());
        }
        return Response.status(Status.NO_CONTENT).build();
    }
    
    @PUT
    @Path("/customers/update")
    @Consumes(MediaType.APPLICATION_XML)
    public Response updateCustomer(Customer customer) {
        try {
            ejb.updateCustomer(customer);
        } catch (Exception e) {
            LOGGER.severe(LOG_HEADER + ": Exception updating customer");
            throw new BankingBussinessException(e.getMessage(), e.getCause());
        }
        return Response.status(Status.NO_CONTENT).build();
    }
    
    @PUT
    @Path("/accounts/update")
    @Consumes(MediaType.APPLICATION_XML)
    public Response updateAccount(Account account) {
        try {
            ejb.updateAccount(account);
        } catch (Exception e) {
            LOGGER.severe(LOG_HEADER + ": Exception updating account");
            throw new BankingBussinessException(e.getMessage(), e.getCause());
        }
        return Response.status(Status.NO_CONTENT).build();
    }
    
    @PUT
    @Path("/credentials/update")
    @Consumes(MediaType.APPLICATION_XML)
    public Response updateCustomerPassword(Credential credential) {
        try {
            ejb.updatePassword(credential);
        } catch (Exception e) {
            LOGGER.severe(LOG_HEADER + ": Exception updating password");
            throw new BankingBussinessException(e.getMessage(), e.getCause());
        }
        return Response.status(Status.NO_CONTENT).build();
    }
}
