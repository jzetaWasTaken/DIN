/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.logic;

import bank.management.ui.model.AccountBean;
import bank.management.ui.model.AccountType;
import bank.management.ui.model.CredentialBean;
import bank.management.ui.model.CustomerBean;
import bank.management.ui.model.TransactionBean;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author jon
 */
public class ManagerImplementationTest {
    
    public ManagerImplementationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCustomerAccounts method, of class ManagerImplementation.
     */
    @Ignore
    @Test
    public void testGetCustomerAccounts() throws Exception {
        System.out.println("getCustomerAccounts");
        String customerId = "351";
        ManagerImplementation instance = new ManagerImplementation();
        List<AccountBean> expResult = new ArrayList<>();
        AccountBean account = new AccountBean();
        account.setId(new Long(401));
        expResult.add(account);
        List<AccountBean> result = instance.getCustomerAccounts(customerId);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCustomer method, of class ManagerImplementation.
     */
    @Ignore
    @Test
    public void testGetCustomer() throws Exception {
        System.out.println("getCustomer");
        String login = "jzaballa";
        
        ManagerImplementation instance = new ManagerImplementation();
        CredentialBean credential = new CredentialBean();
        credential.setLogin("jzaballa");
        credential.setPassword("jzaballa");
        CustomerBean customer = new CustomerBean();
        customer.setId(new Long(2));
        customer.setCredentials(credential);
        List<CustomerBean> expResult = new ArrayList<>();
        expResult.add(customer);
        List<CustomerBean> result = instance.getCustomer(login);
        assertEquals(expResult, result);
    }

//    /**
//     * Test of getAccountTransactions method, of class ManagerImplementation.
//     */
//    @Test
//    public void testGetAccountTransactions() throws Exception {
//        System.out.println("getAccountTransactions");
//        String accountId = "";
//        ManagerImplementation instance = new ManagerImplementation();
//        Collection<TransactionBean> expResult = null;
//        Collection<TransactionBean> result = instance.getAccountTransactions(accountId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAccountTransfers method, of class ManagerImplementation.
//     */
//    @Test
//    public void testGetAccountTransfers() throws Exception {
//        System.out.println("getAccountTransfers");
//        String accountId = "";
//        ManagerImplementation instance = new ManagerImplementation();
//        Collection<TransactionBean> expResult = null;
//        Collection<TransactionBean> result = instance.getAccountTransfers(accountId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAccountPayments method, of class ManagerImplementation.
//     */
//    @Test
//    public void testGetAccountPayments() throws Exception {
//        System.out.println("getAccountPayments");
//        String accountId = "";
//        ManagerImplementation instance = new ManagerImplementation();
//        Collection<TransactionBean> expResult = null;
//        Collection<TransactionBean> result = instance.getAccountPayments(accountId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAccountDeposits method, of class ManagerImplementation.
//     */
//    @Test
//    public void testGetAccountDeposits() throws Exception {
//        System.out.println("getAccountDeposits");
//        String accountId = "";
//        ManagerImplementation instance = new ManagerImplementation();
//        Collection<TransactionBean> expResult = null;
//        Collection<TransactionBean> result = instance.getAccountDeposits(accountId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
    /**
     * Test of authenticate method, of class ManagerImplementation.
     */
    @Ignore
    @Test
    public void testAuthenticate() throws Exception {
        System.out.println("authenticate");
        String id = "351";
        String password = "jzaballa";
        ManagerImplementation instance = new ManagerImplementation();
        CustomerBean expResult = new CustomerBean();
        expResult.setId(new Long(351));
        CustomerBean result = instance.authenticate(id, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of createCustomer method, of class ManagerImplementation.
     */
    @Ignore
    @Test
    public void testCreateCustomer() throws Exception {
        System.out.println("createCustomer");
        CredentialBean credential = new CredentialBean();
        credential.setLogin("jzaballa");
        credential.setPassword("jzaballa");
        CustomerBean customer = new CustomerBean();
        customer.setCredentials(credential);
        customer.setBirthDate(new Date());
        customer.setCity("Leioa");
        customer.setEmail("jzaballa@test.org");
        customer.setFirstName("Jon");
        customer.setLastName("ZZ");
        customer.setNation("Basque");
        customer.setPhone("789456123");
        customer.setStreet("Kalea street");
        customer.setZip("54444");
        ManagerImplementation instance = new ManagerImplementation();
        String result = instance.createCustomer(customer);
        assertNotNull(result);
        
    }

    /**
     * Test of createAccount method, of class ManagerImplementation.
     */
    @Ignore
    @Test
    public void testCreateAccount() throws Exception {
        System.out.println("createAccount");
        ManagerImplementation instance = new ManagerImplementation();
        AccountBean account = new AccountBean();
        account.setAccountNumber("1");
        account.setBalance(new BigDecimal(1000));
        account.setBeginBalance(new BigDecimal(1000));
        account.setDescription("My account");
        account.setType(AccountType.CHECK);
        CustomerBean customer = instance.getCustomer("jzaballa").get(0);
        List<CustomerBean> customers = new ArrayList<>();
        customers.add(customer);
        account.setCustomers(customers);
        String result = instance.createAccount(account);
        assertNotNull(result);
    }
//
//    /**
//     * Test of makeDeposit method, of class ManagerImplementation.
//     */
//    @Test
//    public void testMakeDeposit() throws Exception {
//        System.out.println("makeDeposit");
//        TransactionBean deposit = null;
//        ManagerImplementation instance = new ManagerImplementation();
//        instance.makeDeposit(deposit);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of makePayment method, of class ManagerImplementation.
//     */
//    @Test
//    public void testMakePayment() throws Exception {
//        System.out.println("makePayment");
//        TransactionBean payment = null;
//        ManagerImplementation instance = new ManagerImplementation();
//        instance.makePayment(payment);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of makeTransfer method, of class ManagerImplementation.
//     */
//    @Test
//    public void testMakeTransfer() throws Exception {
//        System.out.println("makeTransfer");
//        TransactionBean transfer = null;
//        String accountTo = "";
//        ManagerImplementation instance = new ManagerImplementation();
//        instance.makeTransfer(transfer, accountTo);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updateAccount method, of class ManagerImplementation.
//     */
//    @Test
//    public void testUpdateAccount() throws Exception {
//        System.out.println("updateAccount");
//        AccountBean account = null;
//        ManagerImplementation instance = new ManagerImplementation();
//        instance.updateAccount(account);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updateCustomer method, of class ManagerImplementation.
//     */
//    @Test
//    public void testUpdateCustomer() throws Exception {
//        System.out.println("updateCustomer");
//        CustomerBean customer = null;
//        ManagerImplementation instance = new ManagerImplementation();
//        instance.updateCustomer(customer);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of deleteCustomer method, of class ManagerImplementation.
     */
    //@Ignore
    @Test
    public void testDeleteCustomer() throws Exception {
        System.out.println("deleteCustomer");
        String customerId = "451";
        ManagerImplementation instance = new ManagerImplementation();
        boolean result = instance.deleteCustomer(customerId);
        assertTrue(result);
    }

    /**
     * Test of deleteAccount method, of class ManagerImplementation.
     */
    @Ignore
    @Test
    public void testDeleteAccount() throws Exception {
        System.out.println("deleteAccount");
        String accountId = "251";
        ManagerImplementation instance = new ManagerImplementation();
        boolean result = instance.deleteAccount(accountId);
        assertTrue(result);
    }
    
}
