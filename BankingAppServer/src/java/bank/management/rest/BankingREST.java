/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.rest;

import bank.management.ejb.BankingEJBLocal;
import bank.management.entity.Account;
import bank.management.entity.Credential;
import bank.management.entity.Transaction;
import bank.management.entity.Customer;
import bank.management.exception.AccountException;
import bank.management.exception.BankingBussinessException;
import bank.management.exception.BankingDbException;
import bank.management.exception.CustomerException;
import bank.management.exception.CustomerLoginException;
import bank.management.exception.CustomerUnauthorizedException;
import bank.management.exception.EntityDeleteException;
import bank.management.exception.NoAccountException;
import bank.management.exception.NoCustomerException;
import bank.management.exception.NoTransactionException;
import bank.management.exception.NotEnoughFundsException;
import bank.management.exception.TransactionException;
import java.sql.SQLException;
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
 * RESTful web service that provides methods and URI paths for REST Clients. 
 * 
 * @author Jon Zaballa Zarzosa
 * @version 1.0, 17 Feb 2018
 */
@Path("/banking")
public class BankingREST {
    
    /**
     * Application log.
     */
    private static final Logger LOGGER = Logger.getLogger("bankingappserverside");
    
    /**
     * String constant to start the logging messages.
     */
    private static final String LOG_HEADER = "BankingREST";
    
    /**
     * String constant to send to the client when a customer is created.
     */
    private static final String CUSTOMER_CREATED = "Customer created; id: %s";
    
    /**
     * String constant to send to the client when an account is created.
     */
    private static final String ACCOUNT_CREATED = "Account created; id: %s";
    
    /**
     * String constant to send to the client when a transaction is made.
     */
    private static final String TRANSACTION_MADE = "Transaction made; id: %s";

    /**
     * EJB injection. It enables access to business logic methods.
     */
    @EJB
    private BankingEJBLocal ejb;
    
    /**
     * GET method that fetches accounts by customer. It returns the entities
     * found embedded into an HTTP {@link Response}. 
     * <p></p>
     * It throws {@link RuntimeException} when no entities are found or
     * when something goes wrong on the server's logic layer. The exceptions are
     * mapped to HTTP responses to notify the client.
     * 
     * @param id    Customer ID.
     * @return      HTTP response with {@link Response.Status#OK} status and
     *              embedded entities.
     * @see         AccountException
     * @see         BankingBussinessException
     * @see         GET
     */
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
    
    /**
     * GET method that fetches transactions by account. It returns the entities
     * found embedded into an HTTP {@link Response}. 
     * <p></p>
     * It throws {@link RuntimeException}s when no entities are found or
     * when something goes wrong on the server's logic layer. The exceptions are
     * mapped to HTTP responses to notify the client.
     * 
     * @param accountId Customer ID.
     * @return          HTTP response with {@link Response.Status#OK} status 
     *                  and embedded entities.
     * @see             TransactionException
     * @see             BankingBussinessException
     * @see             GET
     */
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
    
    /**
     * GET method that fetches deposits by account. It returns the entities
     * found embedded into an HTTP {@link Response}. 
     * <p></p>
     * It throws {@link RuntimeException}s when no entities are found or
     * when something goes wrong on the server's logic layer. The exceptions are
     * mapped to HTTP responses to notify the client.
     * 
     * @param accountId Customer ID.
     * @return          HTTP response with {@link Response.Status#OK} status 
     *                  and embedded entities.
     * @see             TransactionException
     * @see             BankingBussinessException
     * @see             GET
     */
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
    
    /**
     * GET method that fetches payments by account. It returns the entities
     * found embedded into an HTTP {@link Response}. 
     * <p></p>
     * It throws {@link RuntimeException}s when no entities are found or
     * when something goes wrong on the server's logic layer. The exceptions are
     * mapped to HTTP responses to notify the client.
     * 
     * @param accountId Customer ID.
     * @return          HTTP response with {@link Response.Status#OK} status 
     *                  and embedded entities.
     * @see             TransactionException
     * @see             BankingBussinessException
     * @see             GET
     */
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
    
    /**
     * GET method that fetches transactions by account. It returns the entities
     * found embedded into an HTTP {@link Response}. 
     * <p></p>
     * It throws {@link RuntimeException}s when no entities are found or
     * when something goes wrong on the server's logic layer. The exceptions are
     * mapped to HTTP responses to notify the client.
     * 
     * @param accountId Customer ID.
     * @return          HTTP response with {@link Response.Status#OK} status 
     *                  and embedded entities.
     * @see             TransactionException
     * @see             BankingBussinessException
     * @see             GET
     */
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
    
    /**
     * GET method that fetches customers by login. It returns the entities
     * found embedded into an HTTP {@link Response}. 
     * <p></p>
     * It throws {@link RuntimeException}s when no entities are found or
     * when something goes wrong on the server's logic layer. The exceptions are
     * mapped to HTTP responses to notify the client.
     * 
     * @param login Customer ID.
     * @return      HTTP response with {@link Response.Status#OK} status 
     *              and embedded entities.
     * @see         TransactionException
     * @see         BankingBussinessException
     * @see         GET
     */
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
        } catch (SQLException e) {
            LOGGER.severe(LOG_HEADER + ": Database error");
            System.out.println("SQL error");
            throw new BankingDbException(e.getMessage(), e.getCause());
        } catch (Exception e) {
            LOGGER.severe(LOG_HEADER + ": Exception fetching customers by login");
            System.out.println("General error");
            throw new BankingBussinessException(e.getMessage(), e.getCause());
        }
        LOGGER.info(LOG_HEADER + ": all good!");
        return Response.ok(entity).build();
    }
    
    /**
     * GET method to authenticate customers. If successful, it returns the customer
     * found embedded into an HTTP {@link Response}. 
     * <p></p>
     * It throws {@link RuntimeException}s when no entities are found or
     * when something goes wrong on the server's logic layer. The exceptions are
     * mapped to HTTP responses to notify the client.
     * 
     * @param id        Customer ID
     * @param password  Customer password
     * @return          HTTP response with {@link Response.Status#OK} status 
     *                  and embedded entities.
     * @see             BankingBussinessException
     * @see             CustomerUnauthorizedException
     * @see             GET
     */
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
    
    /**
     * POST method to create a new customer. If successful, it returns a 
     * message with the newly created customer's ID embedded into an HTTP 
     * {@link Response}. 
     * <p></p>
     * It throws a {@link RuntimeException} when the customer creation process 
     * fails. The exception is mapped to an HTTP response to notify the client.
     * 
     * @param customer  Customer to be created.
     * @return          HTTP response with {@link Response.Status#CREATED} 
     *                  status and embedded message with the new customer's ID.
     * @see             BankingBussinessException
     * @see             POST
     */
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
    
    /**
     * POST method to create a new account. If successful, it returns a 
     * message with the newly created account ID embedded into an HTTP 
     * {@link Response}. 
     * <p></p>
     * It throws a {@link RuntimeException} when the account creation process 
     * fails. The exception is mapped to an HTTP response to notify the client.
     * 
     * @param account   Account to be created.
     * @return          HTTP response with {@link Response.Status#CREATED} 
     *                  status and embedded message with the new account ID.
     * @see             BankingBussinessException
     * @see             POST
     */
    @POST
    @Path("/accounts")
    @Consumes(MediaType.APPLICATION_XML)
    public Response createAccount(Account account) {
        Account newAccount = null;
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
    
    /**
     * POST method to make a money deposit. If successful, it returns a message 
     * with the deposit ID embedded into an HTTP {@link Response}. 
     * <p></p>
     * It throws a {@link RuntimeException} when the deposit creation process 
     * fails. The exception is mapped to an HTTP response to notify the client.
     * 
     * @param transaction   Deposit to be made.
     * @return              HTTP response with {@link Response.Status#CREATED} 
     *                      status and embedded message with the new transaction
     *                      ID.
     * @see                 BankingBussinessException
     * @see                 POST
     */
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
    
    /**
     * POST method to make a money payment. If successful, it returns a message 
     * with the payment ID embedded into an HTTP {@link Response}. 
     * <p></p>
     * It throws {@link RuntimeException}s when when there are not enough funds 
     * on the sender account or when something goes wrong on the server logic 
     * layer. The exceptions are mapped to HTTP responses to notify the client.
     * 
     * @param transaction   Payment to be made.
     * @return              HTTP response with {@link Response.Status#CREATED} 
     *                      status and embedded message with the new transaction
     *                      ID.
     * @see                 BankingBussinessException
     * @see                 TransactionException
     * @see                 POST
     */
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
    
    /**
     * POST method to make a money transfer. If successful, it returns a message 
     * with the transfer ID embedded into an HTTP {@link Response}. 
     * <p></p>
     * It throws {@link RuntimeException}s when the recipient account number is 
     * not found, when there are not enough funds on the sender account or when 
     * something goes wrong on the server logic layer. The exceptions are mapped 
     * to HTTP responses to notify the client.
     * 
     * @param transaction   Transfer to be made.
     * @param accountNumber Recipient account number
     * @return              HTTP response with {@link Response.Status#CREATED} 
     *                      status and embedded message with the new transaction
     *                      ID.
     * @see                 AccountException
     * @see                 BankingBussinessException
     * @see                 TransactionException
     * @see                 POST
     */
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
                    LOG_HEADER + ": Recipient account not found; accountNumber: {0}",
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
    
    /**
     * DELETE method to delete a customer. If successful, it returns an HTTP 
     * {@link Response}. 
     * <p></p>
     * It throws {@link RuntimeException}s when the entity no longer exists or 
     * when an error occurs on the server logic layer. The exceptions are mapped 
     * to HTTP responses to notify the client.
     * 
     * @param customerId    ID of the customer to delete.  
     * @return              HTTP response with {@link Response.Status#NO_CONTENT} 
     *                      status.
     * @see                 EntityDeleteException
     * @see                 BankingBussinessException
     * @see                 DELETE
     */
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
    
    /**
     * DELETE method to delete an account. If successful, it returns an HTTP 
     * {@link Response}. 
     * <p></p>
     * It throws {@link RuntimeException}s when the entity no longer exists or 
     * when an error occurs on the server logic layer. The exceptions are mapped 
     * to HTTP responses to notify the client.
     * 
     * @param accountId ID of the account to delete.
     * @return          HTTP response with {@link Response.Status#NO_CONTENT} 
     *                  status.
     * @see             EntityDeleteException
     * @see             BankingBussinessException
     * @see             DELETE
     */
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
    
    /**
     * PUT method to update a customer. If successful, it returns an HTTP 
     * {@link Response}. 
     * <p></p>
     * It throws a {@link RuntimeException} when the update process fails. The 
     * exception is mapped to an HTTP response to notify the client.
     * 
     * @param customer  Customer to update.
     * @return          HTTP response with {@link Response.Status#NO_CONTENT} 
     *                  status.
     * @see             BankingBussinessException
     * @see             PUT
     */
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
    
    /**
     * PUT method to update an account. If successful, it returns an HTTP 
     * {@link Response}. 
     * <p></p>
     * It throws a {@link RuntimeException} when the update process fails. The 
     * exception is mapped to an HTTP response to notify the client.
     * 
     * @param account   Account to update.
     * @return          HTTP response with {@link Response.Status#NO_CONTENT} 
     *                  status.
     * @see             BankingBussinessException
     * @see             PUT
     */
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
    
    /**
     * PUT method to update a customer password. If successful, it returns an 
     * HTTP {@link Response}. 
     * <p></p>
     * It throws a {@link RuntimeException} when the update process fails. The 
     * exception is mapped to an HTTP response to notify the client.
     * 
     * @param credential    Customer credentials to update.
     * @return              HTTP response with {@link Response.Status#NO_CONTENT} 
     *                      status.
     * @see                 BankingBussinessException
     * @see                 PUT
     */
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
