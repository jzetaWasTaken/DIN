/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.rest;

import bank.management.ui.model.AccountBean;
import java.util.List;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

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

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/GestionBancariaServerSide/webresources";

    public BankingRESTClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("banking");
    }
    
    public <T> T findAccountsByCustomer(GenericType<T> responseType, String customerid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("accounts/{0}", new Object[]{customerid}));
        //Response response = resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get();
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
        /*
        T object = null;
        try {
            if (response.getStatusInfo() == Response.Status.OK) {
                object = response.readEntity(new GenericType<T>() {});
            } else if (response.getStatusInfo() == Response.Status.NOT_FOUND) {
                throw new Exception(response.readEntity(String.class));
            }
        } finally {
            response.close();
        }
        return object;
        */
    }

    public Response updateCustomerPassword(Object requestEntity) throws ClientErrorException {
        return webTarget.path("credentials/update").request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), Response.class);
    }

    public Response makePayment(Object requestEntity) throws ClientErrorException {
        return webTarget.path("payments").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), Response.class);
    }

    public Response createCustomer(Object requestEntity) throws ClientErrorException {
        return webTarget.path("customers").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), Response.class);
    }

    public Response makeTransfer(Object requestEntity, String accountto) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("transfers/{0}", new Object[]{accountto})).request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), Response.class);
    }

    public <T> T findTransactionsByAccount(Class<T> responseType, String accountid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("transactions/{0}", new Object[]{accountid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T findTransfersByAccount(Class<T> responseType, String accountid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("transfers/{0}", new Object[]{accountid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public Response deleteCustomer(String customerid) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("customers/delete/{0}", new Object[]{customerid})).request().delete(Response.class);
    }

    public Response createAccount(Object requestEntity) throws ClientErrorException {
        return webTarget.path("accounts").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), Response.class);
    }

    public Response updateAccount(Object requestEntity) throws ClientErrorException {
        return webTarget.path("accounts/update").request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), Response.class);
    }

    public <T> T authenticateCustomer(Class<T> responseType, String id, String password) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("login/{0}-{1}", new Object[]{id, password}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public Response makeDeposit(Object requestEntity) throws ClientErrorException {
        return webTarget.path("deposits").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), Response.class);
    }

    public <T> T findPaymentsByAccount(Class<T> responseType, String accountid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("payments/{0}", new Object[]{accountid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T findDepositsByAccount(Class<T> responseType, String accountid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("deposits/{0}", new Object[]{accountid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public Response updateCustomer(Object requestEntity) throws ClientErrorException {
        return webTarget.path("customers/update").request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML), Response.class);
    }

    public <T> T findCustomerByLogin(Class<T> responseType, String login) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("login/{0}", new Object[]{login}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public Response deleteAccount(String accountid) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("accounts/delete/{0}", new Object[]{accountid})).request().delete(Response.class);
    }

    public void close() {
        client.close();
    }
    
}
