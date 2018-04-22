/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.test;

import bank.management.ejb.BankingEJB;
import bank.management.ejb.BankingEJBLocal;
import bank.management.entity.Account;
import bank.management.entity.Credential;
import bank.management.entity.Customer;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;

/**
 *
 * @author jon
 */
public class MainTest {
    
    @EJB
    private static BankingEJBLocal ejb;
    
    public static void main(String[] args) {
        
//        for (int i = 0; i < 10; i++) {
//            Credential credential = new Credential();
//            credential.setLogin("customer"+i);
//            credential.setPassword("customer"+i);
//            credential.setLastModifiedOn(new Date());
//            credential.setLastSignedIn(new Date());
//            
//            Customer customer = new Customer();
//            customer.setCredentials(credential);
//            customer.setCity("City"+1);
//            customer.setEmail("customer"+i+"@test.org");
//            customer.setFirstName("Customer #"+i);
//            customer.setAccounts(new ArrayList<Account>());
//            customer.setBirthDate(new Date());
//            customer.setLastName("Lastname"+i);
//            customer.setNation("Nation"+i);
//            customer.setPhone("66655500"+i);
//            customer.setStreet("Elm Street "+i);
//            customer.setZip("0000"+i);
//            
//            try {
//                System.out.println(customer == null?"Null":"Not null");
//                ejb.createCustomer(customer);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
        try {
            ejb.findAccountsByCustomerId(new Long(1));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        System.exit(0);
    }
    
}
