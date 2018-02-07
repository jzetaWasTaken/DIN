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
import gestionbancariaserver.exception.AccountFetchException;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author jon
 */
@Path("banking")
public class BankingREST {
    
    private static final Logger LOGGER = Logger.getLogger("bankingappserverside");
    private static final String LOG_HEADER = "BankingREST";

    @EJB
    private BankingEJBLocal ejb;
    
    @GET
    @Path("account/{customerid}")
    @Produces(MediaType.APPLICATION_XML)
    @SuppressWarnings("UseSpecificCatch")
    public List<Account> findAccountsByCustomer(@PathParam("customerid") Long id) {
        List<Account> accounts = null;
        try {
            LOGGER.log(Level.INFO,
                    LOG_HEADER + ": Find accounts by user id {0}",
                    id);
            accounts = ejb.findAccountsByCustomerId(id);
        } catch (AccountFetchException e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception finding accounts. {0}",
                    e.getMessage());
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
        return accounts;
    }
    
    @GET
    @Path("transactions/{accountid}")
    @Produces(MediaType.APPLICATION_XML)
    public List<Transaction> findTransactionsByAccount(@PathParam("accountid") String accountId) {
        List<Transaction> transactions = null;
        try {
            LOGGER.log(Level.INFO,
                    LOG_HEADER + ": Find transactions by account id {0}",
                    accountId);
            transactions = ejb.findTransactionsByAccount(accountId);
            if (transactions == null || transactions.isEmpty()) {
                LOGGER.info(LOG_HEADER + ": No transactions found");
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
        } catch (EJBException e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception finding transactions. {0}",
                    e.getMessage());
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
        return transactions;
    }
    
    @GET
    @Path("deposits/{accountid}")
    @Produces(MediaType.APPLICATION_XML)
    public List<Transaction> findDepositsByAccount(@PathParam("accountid") String accountId) {
        List<Transaction> transactions = null;
        try {
            LOGGER.log(Level.INFO,
                    LOG_HEADER + ": Find deposits by account id {0}",
                    accountId);
            transactions = ejb.findDepositsByAccount(accountId);
            if (transactions == null || transactions.isEmpty()) {
                LOGGER.info(LOG_HEADER + ": No deposits found");
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
        } catch (EJBException e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception finding deposits. {0}",
                    e.getMessage());
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
        return transactions;
    }
    
    @GET
    @Path("payments/{accountid}")
    @Produces(MediaType.APPLICATION_XML)
    public List<Transaction> findPaymentsByAccount(@PathParam("accountid") String accountId) {
        List<Transaction> transactions = null;
        try {
            LOGGER.log(Level.INFO,
                    LOG_HEADER + ": Find payments by account id {0}",
                    accountId);
            transactions = ejb.findPaymentsByAccount(accountId);
            if (transactions == null || transactions.isEmpty()) {
                LOGGER.info(LOG_HEADER + ": No payments found");
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
        } catch (EJBException e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception finding payments. {0}",
                    e.getMessage());
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
        return transactions;
    }
    
    @GET
    @Path("transfers/{accountid}")
    @Produces(MediaType.APPLICATION_XML)
    public List<Transaction> findTransfersByAccount(@PathParam("accountid") String accountId) {
        List<Transaction> transactions = null;
        try {
            LOGGER.log(Level.INFO,
                    LOG_HEADER + ": Find transfers by account id {0}",
                    accountId);
            transactions = ejb.findTransfersByAccount(accountId);
            if (transactions == null || transactions.isEmpty()) {
                LOGGER.info(LOG_HEADER + ": No transfers found");
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
        } catch (EJBException e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception finding transfers. {0}",
                    e.getMessage());
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
        return transactions;
    }
    
    @GET
    @Path("login/{login}-{passw}")
    @Produces(MediaType.APPLICATION_XML)
    public Customer findCustomerByLogin(@PathParam("login") String login, @PathParam("passw") String passw) {
        Customer customer = null;
        try {
            LOGGER.log(Level.INFO,
                    LOG_HEADER + ": Find customer by login {0}",
                    login);
            customer = ejb.findCustomerByLogin(login, passw);
            if (customer == null) {
                LOGGER.info(LOG_HEADER + ": No customer found");
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
        } catch (EJBException e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception finding customer. {0}",
                    e.getMessage());
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
        return customer;
    }
    
    @POST
    @Path("customer")
    @Consumes(MediaType.APPLICATION_XML)
    public void createCustomer(Customer customer) {
        try {
            LOGGER.log(Level.INFO,
                    LOG_HEADER + ": Creating customer {0}",
                    customer);
            ejb.createCustomer(customer);
            LOGGER.info(LOG_HEADER + ": Customer created");
        } catch (EJBException e) {
            LOGGER.log(Level.SEVERE,
                    LOG_HEADER + ": Exception creating customer. {0}",
                    e.getMessage());
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
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
    
    
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Account entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Account entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Account find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Account> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Account> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    */
}
