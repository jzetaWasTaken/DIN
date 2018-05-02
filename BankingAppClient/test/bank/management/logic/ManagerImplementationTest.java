/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.logic;

import bank.management.exception.ManagerException;
import bank.management.ui.model.AccountBean;
import bank.management.ui.model.AccountType;
import bank.management.ui.model.CredentialBean;
import bank.management.ui.model.CustomerBean;
import bank.management.ui.model.TransactionBean;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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
        String customerId = "501";
        ManagerImplementation instance = new ManagerImplementation();
        List<AccountBean> expResult = new ArrayList<>();
        AccountBean account = new AccountBean();
        account.setId(new Long(701));
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
        CustomerBean customer = new CustomerBean();
        customer.setId(new Long(501));
        List<CustomerBean> expResult = new ArrayList<>();
        expResult.add(customer);
        List<CustomerBean> result = instance.getCustomer(login);
        result.forEach(c->{
            if (c.getAccounts() != null) 
                c.getAccounts().forEach(a->System.out.println(a.getId().toString()));
        });
        assertEquals(expResult, result);
    }

    /**
     * Test of getAccountTransactions method, of class ManagerImplementation.
     */
    @Ignore
    @Test
    public void testGetAccountTransactions() throws Exception {
        System.out.println("getAccountTransactions");
        String accountId = "751";
        ManagerImplementation instance = new ManagerImplementation();
        Collection<TransactionBean> result = instance.getAccountTransactions(accountId);
        assertEquals(4, result.size());
    }

    /**
     * Test of getAccountTransfers method, of class ManagerImplementation.
     */
    @Ignore
    @Test
    public void testGetAccountTransfers() throws Exception {
        System.out.println("getAccountTransfers");
        String accountId = "751";
        ManagerImplementation instance = new ManagerImplementation();
        Collection<TransactionBean> result = instance.getAccountTransfers(accountId);
        assertEquals(2, result.size());
    }

    /**
     * Test of getAccountPayments method, of class ManagerImplementation.
     */
    @Ignore
    @Test
    public void testGetAccountPayments() throws Exception {
        System.out.println("getAccountPayments");
        String accountId = "751";
        ManagerImplementation instance = new ManagerImplementation();
        Collection<TransactionBean> result = instance.getAccountPayments(accountId);
        assertEquals(1, result.size());
    }

    /**
     * Test of getAccountDeposits method, of class ManagerImplementation.
     */
    @Ignore
    @Test
    public void testGetAccountDeposits() throws Exception {
        System.out.println("getAccountDeposits");
        String accountId = "751";
        ManagerImplementation instance = new ManagerImplementation();
        Collection<TransactionBean> result = instance.getAccountDeposits(accountId);
        assertEquals(1, result.size());
    }

    /**
     * Test of authenticate method, of class ManagerImplementation.
     */
    @Ignore
    @Test
    public void testAuthenticate() throws Exception {
        System.out.println("authenticate");
        String id = "501";
        String password = "jzaballa";
        ManagerImplementation instance = new ManagerImplementation();
        CustomerBean expResult = new CustomerBean();
        expResult.setId(new Long(501));
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
        credential.setLogin("javiuri");
        credential.setPassword("javiuri");
        CustomerBean customer = new CustomerBean();
        customer.setCredentials(credential);
        customer.setBirthDate(new Date());
        customer.setCity("Bilbao");
        customer.setEmail("javiuri@test.org");
        customer.setFirstName("Javier");
        customer.setLastName("Martín Uría");
        customer.setNation("Spain");
        customer.setPhone("789456124");
        customer.setStreet("Kalea street");
        customer.setZip("54443");
        ManagerImplementation instance = new ManagerImplementation();
        String result = instance.createCustomer(customer);
        assertNotNull(result);
        
    }

    /**
     * Test of createAccount method, of class ManagerImplementation.
     */
    //@Ignore
    @Test
    public void testCreateAccount() throws Exception {
        System.out.println("createAccount");
        ManagerImplementation instance = new ManagerImplementation();
        AccountBean account = new AccountBean();
        account.setAccountNumber("44");
        account.setBalance(new BigDecimal(5000));
        account.setBeginBalance(new BigDecimal(500));
        account.setCreditLine(new BigDecimal(100000));
        account.setDescription("Javi's account 2");
        account.setType(AccountType.CREDIT);
        CustomerBean customer = instance.getCustomer("javiuri").get(0);
        if (customer.getAccounts() != null) customer.getAccounts().add(account);
        else customer.setAccounts(new ArrayList<>(Arrays.asList(account)));
        boolean result = instance.updateCustomer(customer);
        assertTrue(result);
    }

    /**
     * Test of makeDeposit method, of class ManagerImplementation.
     */
    @Ignore
    @Test
    public void testMakeDeposit() throws Exception {
        System.out.println("makeDeposit");
        TransactionBean deposit = new TransactionBean();
        AccountBean account = new AccountBean();
        account.setId(new Long(751));
        account.setAccountNumber("2");
        account.setBalance(new BigDecimal(1000));
        account.setBeginBalance(new BigDecimal(1000));
        GregorianCalendar cal = new GregorianCalendar(2018, 3, 30);
        Date date = cal.getTime();
        account.setBeginBalanceDate(date);
        account.setCreditLine(new BigDecimal(500000));
        account.setDescription("My account 2");
        account.setType(AccountType.CREDIT);
        deposit.setAccount(account);
        deposit.setAmount(new BigDecimal(100));
        deposit.setDescription("Deposit 1");
        ManagerImplementation instance = new ManagerImplementation();
        String result = instance.makeDeposit(deposit);
        assertNotNull(result);
    }

    /**
     * Test of makePayment method, of class ManagerImplementation.
     */
    @Ignore
    @Test
    public void testMakePayment() throws Exception {
        System.out.println("makePayment");
        AccountBean account = new AccountBean();
        account.setId(new Long(751));
        account.setAccountNumber("2");
        account.setBalance(new BigDecimal(1100));
        account.setBeginBalance(new BigDecimal(1000));
        GregorianCalendar cal = new GregorianCalendar(2018, 3, 30);
        Date date = cal.getTime();
        account.setBeginBalanceDate(date);
        account.setCreditLine(new BigDecimal(500000));
        account.setDescription("My account 2");
        account.setType(AccountType.CREDIT);
        TransactionBean payment = new TransactionBean();
        payment.setAccount(account);
        payment.setAmount(new BigDecimal(200));
        payment.setDescription("Payment 1");
        ManagerImplementation instance = new ManagerImplementation();
        String resutlt = instance.makePayment(payment);
        assertNotNull(resutlt);
    }

    /**
     * Test of makeTransfer method, of class ManagerImplementation.
     */
    @Ignore
    @Test(expected = ManagerException.class)
    public void testMakeTransfer() throws Exception {
        System.out.println("makeTransfer");
        AccountBean account = new AccountBean();
        account.setId(new Long(801));
        account.setAccountNumber("3");
        account.setBalance(new BigDecimal(1000));
        account.setBeginBalance(new BigDecimal(1000));
        //GregorianCalendar cal = new GregorianCalendar(2018, 3, 30);
        //Date date = cal.getTime();
        //account.setBeginBalanceDate(date);
        //account.setCreditLine(new BigDecimal(500000));
        account.setDescription("My account 3");
        account.setType(AccountType.SAVINGS);
        TransactionBean transfer = new TransactionBean();
        transfer.setAccount(account);
        transfer.setAmount(new BigDecimal(1100));
        transfer.setDescription("Transfer 2");
        String accountTo = "4";
        ManagerImplementation instance = new ManagerImplementation();
        String result = instance.makeTransfer(transfer, accountTo);
        assertNotNull(result);
    }

    /**
     * Test of updateAccount method, of class ManagerImplementation.
     */
    @Ignore
    @Test
    public void testUpdateAccount() throws Exception {
        System.out.println("updateAccount");
        AccountBean account = new AccountBean();
        account.setId(new Long(801));
        account.setAccountNumber("3");
        account.setBalance(new BigDecimal(1000));
        account.setBeginBalance(new BigDecimal(1000));
        account.setDescription("My account 3 updated");
        account.setType(AccountType.SAVINGS);
        ManagerImplementation instance = new ManagerImplementation();
        boolean result = instance.updateAccount(account);
        assertTrue(result);
    }

    /**
     * Test of updateCustomer method, of class ManagerImplementation.
     */
    @Ignore
    @Test
    public void testUpdateCustomer() throws Exception {
        System.out.println("updateCustomer");
        ManagerImplementation instance = new ManagerImplementation();
        CustomerBean customer = instance.getCustomer("jzaballa").get(0);
        customer.setCity("Amurrio");
        boolean result = instance.updateCustomer(customer);
        assertTrue(result);
    }

    /**
     * Test of deleteCustomer method, of class ManagerImplementation.
     */
    @Ignore
    @Test
    public void testDeleteCustomer() throws Exception {
        System.out.println("deleteCustomer");
        String customerId = "951";
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
