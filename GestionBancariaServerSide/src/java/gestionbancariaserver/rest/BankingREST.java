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
import gestionbancariaserver.exception.NoAccountException;
import gestionbancariaserver.exception.NoCustomerException;
import gestionbancariaserver.exception.NoTransactionException;
import gestionbancariaserver.exception.TransactionException;
import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author jon
 */
@Path("/banking")
public class BankingREST {
    
    private static final Logger LOGGER = Logger.getLogger("bankingappserverside");
    private static final String LOG_HEADER = "BankingREST";

    @EJB
    private BankingEJBLocal ejb;
    
    @GET
    @Path("/account/{customerid}")
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
    public Response findTransactionsByAccount(@PathParam("accountid") String accountId) {
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
    public Response findDepositsByAccount(@PathParam("accountid") String accountId) {
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
    public Response findPaymentsByAccount(@PathParam("accountid") String accountId) {
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
    public Response findTransfersByAccount(@PathParam("accountid") String accountId) {
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
    @Path("/login/{login}-{passw}")
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
    
    // TODO check authenticateCustomer
    
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
        return Response.created(URI.create("/customers/" 
                + String.valueOf(newCustomer.getId()))).build();
    }
    
    @POST
    @Path("account")
    @Consumes(MediaType.APPLICATION_XML)
    public void createAccount(Account account) {
        try {
            LOGGER.log(Level.INFO,
                    LOG_HEADER + ": Creating account {0}",
                    account);
            ejb.createAccount(account);
            LOGGER.info(LOG_HEADER + ": Account created");
        } catch (EJBException e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception creating account. {0}",
                    e.getMessage());
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    @POST
    @Path("deposit")
    @Consumes(MediaType.APPLICATION_XML)
    public void makeDeposit(Transaction transaction) {
        try {
            LOGGER.log(Level.INFO,
                    LOG_HEADER + ": Making deposit {0}",
                    transaction);
            ejb.makeDeposit(transaction);
            LOGGER.info(LOG_HEADER + ": Deposit made");
        } catch (EJBException e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception making deposit. {0}",
                    e.getMessage());
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    @POST
    @Path("payment")
    @Consumes(MediaType.APPLICATION_XML)
    public void makePayment(Transaction transaction) {
        try {
            LOGGER.log(Level.INFO,
                    LOG_HEADER + ": Making payment {0}",
                    transaction);
            ejb.makePayment(transaction);
            LOGGER.info(LOG_HEADER + ": Payment made");
        } catch (EJBException e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception making payment. {0}",
                    e.getMessage());
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    @POST
    @Path("transfer/{accountto}")
    @Consumes(MediaType.APPLICATION_XML)
    public void makeTransfer(Transaction transaction, @PathParam("accountto") String accountToId) {
        try {
            LOGGER.log(Level.INFO,
                    LOG_HEADER + ": Making transfer {0}",
                    transaction);
            ejb.makeTransfer(transaction, accountToId);
            LOGGER.info(LOG_HEADER + ": Transfer made");
        } catch (EJBException e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception making transfer. {0}",
                    e.getMessage());
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DELETE
    @Path("customer/delete/{customerid}")
    public void deleteCustomer(@PathParam("customerid")Long customerId) {
        try {
            LOGGER.log(Level.INFO,
                    LOG_HEADER + ": Deleting user with id {0}",
                    customerId);
            ejb.deleteCustomer(customerId);
            LOGGER.info(LOG_HEADER + ": User deleted");
        } catch (EJBException e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception deleting user. {0}",
                    e.getMessage());
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DELETE
    @Path("account/delete/{accountid}")
    public void deleteAccount(@PathParam("accountid")String accountId) {
        try {
            LOGGER.log(Level.INFO,
                    LOG_HEADER + ": Deleting account with id {0}",
                    accountId);
            ejb.deleteAccount(accountId);
            LOGGER.info(LOG_HEADER + ": Account deleted");
        } catch (EJBException e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception deleting account. {0}",
                    e.getMessage());
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PUT
    @Path("customer/update")
    @Consumes(MediaType.APPLICATION_XML)
    public void updateCustomer(Customer customer) {
        try {
            LOGGER.log(Level.INFO,
                    LOG_HEADER + ": Updating customer {0}",
                    customer);
            ejb.updateCustomer(customer);
            LOGGER.info(LOG_HEADER + ": Customer updated");
        } catch (EJBException e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception updating customer. {0}",
                    e.getMessage());
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PUT
    @Path("account/update")
    @Consumes(MediaType.APPLICATION_XML)
    public void updateAccount(Account account) {
        try {
            LOGGER.log(Level.INFO,
                    LOG_HEADER + ": Updating account {0}",
                    account);
            ejb.updateAccount(account);
            LOGGER.info(LOG_HEADER + ": Account updated");
        } catch (EJBException e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception updating account. {0}",
                    e.getMessage());
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PUT
    @Path("credentials/update")
    @Consumes(MediaType.APPLICATION_XML)
    public void updateCredential(Credential credential) {
        try {
            LOGGER.log(Level.INFO,
                    LOG_HEADER + ": Updating credentials {0}",
                    credential);
            ejb.updateCredential(credential);
            LOGGER.info(LOG_HEADER + ": Credentials updated");
        } catch (EJBException e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception updating credentials. {0}",
                    e.getMessage());
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PUT
    @Path("lastaccess/update")
    @Consumes(MediaType.APPLICATION_XML)
    public void updateSignedIn(Credential credential) {
        try {
            LOGGER.log(Level.INFO,
                    LOG_HEADER + ": Updating last access date {0}",
                    credential);
            ejb.updateSignedIn(credential);
            LOGGER.info(LOG_HEADER + ": Last access date updated");
        } catch (EJBException e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception updating last access date. {0}",
                    e.getMessage());
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    /*
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Transaction> findDepositsByAccount(Account account) {
        
    }
    */
}
