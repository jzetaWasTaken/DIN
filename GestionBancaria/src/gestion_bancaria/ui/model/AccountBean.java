/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_bancaria.ui.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a banking account.
 * <ul>
 *      <li><strong>{@link Account#id}</strong> Unique identifier of an Account</li> 
 *      <li><strong>{@link Account#accountNumber}</strong> The account IBAN</li> 
 *      <li><strong>{@link Account#type}</strong> The {@link AccountType}</li> 
 *      <li><strong>{@link Account#description}</strong> Account extra information</li> 
 *      <li><strong>{@link Account#balance}</strong> Account current balance</li> 
 *      <li><strong>{@link Account#creditLine}</strong> Account maximum credit</li> 
 *      <li><strong>{@link Account#beginBalance}</strong> Balance when account is created</li>
 *      <li><strong>{@link Account#beginBalanceDate}</strong> Date of account creation</li>
 *      <li><strong>{@link Account#customers}</strong> Owners of the account</li>
 *      <li><strong>{@link Account#transactions}</strong> Account transactions</li> 
 * </ul>
 * 
 * @author Jon Zaballa Zarzosa
 * @version 1.0, 17 Feb 2018
 * @see gestionbancariaserver.entity.AccountType
 * @see gestionbancariaserver.entity.Customer
 * @see gestionbancariaserver.entity.Transaction
 */

@XmlRootElement(name="account")
public class AccountBean implements Serializable {

    /**
     * A unique serial version identifier
     * @see Serializable
     */
    private static final long serialVersionUID = 1L;

    private Long id;
    private String accountNumber;
    //private AccountType type;
    private String description;
    private BigDecimal balance;
    private BigDecimal creditLine;
    private BigDecimal beginBalance;
    private Date beginBalanceDate;
    private Collection<CustomerBean> customers;
    
    //private Collection<TransactionBean> transactions;

    /**
     * Retrieves the account ID.
     * 
     * @return  Account ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Registers the account ID.
     * 
     * @param id    Account ID
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Retrieves the account number.
     * 
     * @return  Account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Registers the account number.
     * 
     * @param accountNumber Account number
     */
    @XmlElement(name="accountNumber")
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    /**
     * Retrieves the account type.
     * 
     * @return  Account type
     * @see     gestionbancariaserver.entity.AccountType
     */
//    public AccountType getType() {
//        return type;
//    }
    
    /**
     * Registers the account type.
     * 
     * @param type  Account type
     * @see         gestionbancariaserver.entity.AccountType
     */
//    public void setType(AccountType type) {
//        this.type = type;
//    }

    /**
     * Retrieves the account description.
     * 
     * @return  Account description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Registers the account description.
     * 
     * @param description   Account description
     */
    @XmlElement(name="description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the account balance.
     * 
     * @return  Account balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Registers the account balance.
     * 
     * @param balance   Account balance
     */
    @XmlElement(name="balance")
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * Retrieves the account maximum credit.
     * 
     * @return  Account credit line
     */
    public BigDecimal getCreditLine() {
        return creditLine;
    }

    /**
     * Registers the account maximum credit.
     * 
     * @param creditLine    Account credit line
     */
    @XmlElement(name="creditLine")
    public void setCreditLine(BigDecimal creditLine) {
        this.creditLine = creditLine;
    }

    /**
     * Retrieves the account begin balance.
     * 
     * @return  Account begin balance
     */
    public BigDecimal getBeginBalance() {
        return beginBalance;
    }

    /**
     * Registers the account begin balance.
     * 
     * @param beginBalance  Account begin balance
     */
    @XmlElement(name="beginBalance")
    public void setBeginBalance(BigDecimal beginBalance) {
        this.beginBalance = beginBalance;
    }

    /**
     * Retrieves the account creation date.
     * 
     * @return  Account begin balance date
     */
    public Date getBeginBalanceDate() {
        return beginBalanceDate;
    }

    /**
     * Registers the account creation date.
     * 
     * @param beginBalanceDate  Account begin balance date
     */
    @XmlElement(name="beginBalanceDate")
    public void setBeginBalanceDate(Date beginBalanceDate) {
        this.beginBalanceDate = beginBalanceDate;
    }
    
    /**
     * Retrieves the account owner list.
     * 
     * @return  Account customers
     * @see     gestionbancariaserver.entity.Customer
     */
    public Collection<CustomerBean> getCustomers() {
        return customers;
    }

    /**
     * Registers the account owners.
     * 
     * @param customers Account owners
     * @see             gestionbancariaserver.entity.Customer
     */
    @XmlElement(name="customers")
    public void setCustomers(Collection<CustomerBean> customers) {
        this.customers = customers;
    }
    
    /**
     * Retrieves the account transaction list.
     * 
     * @return  Account transactions
     * @see     gestionbancariaserver.entity.Transaction
     */
//    public Collection<Transaction> getTransactions() {
//        return transactions;
//    }

    /**
     * Registers the account transactions.
     * 
     * @param transactions  Account transactions
     * @see                 gestionbancariaserver.entity.Transaction
     */
//    public void setTransactions(Collection<Transaction> transactions) {
//        this.transactions = transactions;
//    }
    
    /**
     * Retrieves objects hash code.
     * 
     * @return  Account hash code
     * @see     Object#hashCode() 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountNumber != null ? accountNumber.hashCode() : 0);
        return hash;
    }

    /**
     * Specifies the conditions for two accounts to be equal.
     * 
     * @param object    Object to compare
     * @return          True if the objects are equal, false otherwise
     * @see             Object#equals
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AccountBean)) {
            return false;
        }
        AccountBean other = (AccountBean) object;
        if ((this.accountNumber == null && other.accountNumber != null) || (this.accountNumber != null && !this.accountNumber.equals(other.accountNumber))) {
            return false;
        }
        return true;
    }

    /**
     * Specifies how accounts is printed by default.
     * 
     * @return  The account string format
     * @see     Object#toString() 
     */
    @Override
    public String toString() {
        return "gestionbancariaserver.entity.Account[ id=" + accountNumber + " ]";
    }
    
}
