/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_bancaria.main.test;

import gestion_bancaria.BankingRESTClient;
import gestion_bancaria.ui.model.CredentialBean;
import gestion_bancaria.ui.model.CustomerBean;
import java.util.Date;

/**
 *
 * @author jon
 */
public class Main {
    
    private static BankingRESTClient webClient = new BankingRESTClient();
    
    public static void main(String[] args) {
        
        for (int i = 0; i < 10; i++) {
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
            
            try {
                webClient.createCustomer(customer);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        System.exit(0);
    }
}
