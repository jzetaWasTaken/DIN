/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.test;

import bank.management.rest.BankingRESTClient;
import bank.management.ui.model.AccountBean;
import bank.management.ui.model.CredentialBean;
import bank.management.ui.model.CustomerBean;
import java.util.Date;
import java.util.List;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author jon
 */
public class MainTest {
    
    private static BankingRESTClient webClient = new BankingRESTClient();
    
    public static void main(String[] args) {
        
        for (int i = 0; i < 10; i++) {
            System.out.println("Loop" + (i+1));
            CredentialBean credential = new CredentialBean();
            credential.setLogin("customer"+i);
            credential.setPassword("customer"+i);
            credential.setLastModifiedOn(new Date());
            credential.setLastSignedIn(new Date());
            
            CustomerBean customer = new CustomerBean();
            customer.setCredentials(credential);
            customer.setCity("City"+1);
            customer.setEmail("customer"+i+"@test.org");
            customer.setFirstName("Customer #"+i);
            customer.setBirthDate(new Date());
            customer.setLastName("Lastname"+i);
            customer.setNation("Nation"+i);
            customer.setPhone("66655500"+i);
            customer.setStreet("Elm Street "+i);
            customer.setZip("0000"+i);
            
            
            System.out.println("Creating customer");
            webClient.createCustomer(customer);

        }
        
        try {
            webClient.findAccountsByCustomer(new GenericType<List<AccountBean>>() {}, "1");
        } catch (NotFoundException e) {
            System.out.print("Not found exception");
        }
        
        System.exit(0);
    }
}
