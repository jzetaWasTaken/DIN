/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.rest;

import bank.management.exception.BankServerException;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Jersey REST client generated for REST resource:BankingREST [/banking]<br>
 * USAGE:
 * <pre>
 *        BankingRESTClient client = new BankingRESTClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author jon
 */
public class BankingRESTClient {

    private final WebTarget webTarget;
    private final Client client;
    private static final String BASE_URI = "http://localhost:8080/BankingAppServer/webresources/banking";

    public BankingRESTClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI);
    }
    
    public <T> T findAccountsByCustomer(GenericType<T> responseType, String customerId)
            throws BankServerException, ClientErrorException {
        Map<String, Object> map = new HashMap<>();
        map.put("customerid", customerId);
        return findEntity("accounts/{curtomerid}", map, responseType);
    }
    
    public <T> T findCustomerByLogin(GenericType<T> responseType, String login) 
            throws BankServerException, ClientErrorException {
        Map<String, Object> map = new HashMap<>();
        map.put("login", login);
        return findEntity("login/{login}", map, responseType);
    }
    
    public <T> T findTransactionsByAccount(GenericType<T> responseType, String accountId) 
            throws BankServerException, ClientErrorException {
        Map<String, Object> map = new HashMap<>();
        map.put("accountid", accountId);
        return findEntity("/transactions/{accountid}", map, responseType);
    }

    public <T> T findTransfersByAccount(GenericType<T> responseType, String accountId) 
            throws BankServerException, ClientErrorException {
        Map<String, Object> map = new HashMap<>();
        map.put("accountid", accountId);
        return findEntity("/transfers/{accountid}", map, responseType);
    }
    
    public <T> T findPaymentsByAccount(GenericType<T> responseType, String accountId) 
            throws BankServerException, ClientErrorException {
        Map<String, Object> map = new HashMap<>();
        map.put("accountid", accountId);
        return findEntity("/payments/{accountid}", map, responseType);
    }

    public <T> T findDepositsByAccount(GenericType<T> responseType, String accountId) 
            throws BankServerException, ClientErrorException {
        Map<String, Object> map = new HashMap<>();
        map.put("accountid", accountId);
        return findEntity("/deposits/{accountid}", map, responseType);
    }
    
    public <T> T authenticateCustomer(GenericType<T> responseType, String id, String password) 
            throws BankServerException, ClientErrorException {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("password", password);
        return findEntity("/login/{id}-{password}", map, responseType);
    }
    
    public String createCustomer(Object requestEntity) 
            throws BankServerException, ClientErrorException {
        return createEntity("customers", requestEntity);
    }
    
    public String createAccount(Object requestEntity) 
            throws BankServerException, ClientErrorException {
        return createEntity("accounts", requestEntity);
    }
    
    public String makeDeposit(Object requestEntity) 
            throws BankServerException, ClientErrorException {
        return createEntity("deposits", requestEntity);
    }
    
    public String makePayment(Object requestEntity) 
            throws BankServerException, ClientErrorException {
        return createEntity("payments", requestEntity);
    }

    public String makeTransfer(Object requestEntity, String accountTo) 
            throws BankServerException, ClientErrorException {
        Map<String, Object> map = new HashMap<>();
        map.put("accountto", accountTo);
        return createEntity("transfers/{accountto}", map, requestEntity);
    }

    public void updateCustomerPassword(Object requestEntity) 
            throws BankServerException, ClientErrorException {
        updateEntity("credentials/update", requestEntity);
    }
    
    public void updateAccount(Object requestEntity) 
            throws BankServerException, ClientErrorException {
        updateEntity("accounts/update", requestEntity);
    }

    public void updateCustomer(Object requestEntity) 
            throws BankServerException, ClientErrorException {
        updateEntity("customers/update", requestEntity);
    }

    public boolean deleteCustomer(String customerId) 
            throws BankServerException, ClientErrorException {
        Map<String, Object> map = new HashMap<>();
        map.put("customerid", customerId);
        return deleteEntity("customers/delete/{customerid}", map);
    }

    public boolean deleteAccount(String accountId) 
            throws BankServerException, ClientErrorException {
        Map<String, Object> map = new HashMap<>();
        map.put("accountid", accountId);
        return deleteEntity("accounts/delete/{accountid}", map);
    }

    public void close() {
        client.close();
    }
    
    private <T> T findEntity(String path, Map<String, Object> map, GenericType<T> responseType) 
            throws BankServerException {
        Response response = webTarget.path(path)
                .resolveTemplates(map)
                .request()
                .accept(MediaType.APPLICATION_XML)
                .accept(MediaType.TEXT_PLAIN)
                .get();
        T entity = null;
        try {
            if (response.getStatus() == Status.OK.getStatusCode()) 
                entity = response.readEntity(responseType);
            else
                throw new BankServerException(response.readEntity(String.class));
        } finally {
            response.close();
        }
        return entity;
    }
    
    private String createEntity(String path, Object requestEntity) 
            throws BankServerException, ClientErrorException {
        Response response = webTarget.path(path)
                .request()
                .accept(MediaType.TEXT_PLAIN)
                .post(Entity.xml(requestEntity));
        String entity = null;
        try {
            if (response.getStatus() == Status.CREATED.getStatusCode()) 
                entity = response.readEntity(String.class);
            else
                throw new BankServerException(response.readEntity(String.class));
        } finally {
            response.close();
        }
        return entity;
    }
    
    private String createEntity(String path, Map<String, Object> map, Object requestEntity) 
            throws BankServerException, ClientErrorException {
        Response response = webTarget.path(path)
                .resolveTemplates(map)
                .request()
                .accept(MediaType.TEXT_PLAIN)
                .post(Entity.xml(requestEntity));
        String entity = null;
        try {
            if (response.getStatus() == Status.CREATED.getStatusCode()) 
                entity = response.readEntity(String.class);
            else
                throw new BankServerException(response.readEntity(String.class));
        } finally {
            response.close();
        }
        return entity;
    }
    
    private void updateEntity(String path, Object requestEntity) 
            throws BankServerException, ClientErrorException {
        Response response = webTarget.path(path)
                .request()
                .put(Entity.xml(requestEntity));
        try {
            if (response.getStatus() != Status.NO_CONTENT.getStatusCode()) 
                throw new BankServerException(response.readEntity(String.class));
        } finally {
            response.close();
        }
    }
    
    private boolean deleteEntity(String path, Map<String, Object> map) 
            throws BankServerException {
        Response response = webTarget.path(path)
                .resolveTemplates(map)
                .request()
                .delete();
        try {
            if (response.getStatus() != Status.NO_CONTENT.getStatusCode()) 
                throw new BankServerException(response.readEntity(String.class));
        } finally {
            response.close();
        }
        return true;
    }
}
