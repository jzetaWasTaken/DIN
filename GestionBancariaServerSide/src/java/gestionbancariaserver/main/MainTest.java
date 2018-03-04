/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionbancariaserver.main;

import gestionbancariaserver.ejb.BankingEJB;
import gestionbancariaserver.ejb.BankingEJBLocal;
import gestionbancariaserver.entity.Account;
import gestionbancariaserver.entity.Credential;
import gestionbancariaserver.entity.Customer;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author jon
 */
public class MainTest {
    
    private static BankingEJBLocal ejb = new BankingEJB();
    
    public static void main(String[] args) {
        
        for (int i = 0; i < 10; i++) {
            Credential credential = new Credential();
            credential.setLogin("customer"+i);
            credential.setPassword("customer"+i);
            credential.setLastModifiedOn(new Date());
            credential.setLastSignedIn(new Date());
            
            Customer customer = new Customer();
            customer.setCredentials(credential);
            customer.setCity("City"+1);
            customer.setEmail("customer"+i+"@test.org");
            customer.setFirstName("Customer #"+i);
            customer.setAccounts(new ArrayList<Account>());
            customer.setBirthDate(new Date());
            customer.setLastName("Lastname"+i);
            customer.setNation("Nation"+i);
            customer.setPhone("66655500"+i);
            customer.setStreet("Elm Street "+i);
            customer.setZip("0000"+i);
            
            try {
                ejb.createCustomer(customer);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        System.exit(0);
    }
    
}
