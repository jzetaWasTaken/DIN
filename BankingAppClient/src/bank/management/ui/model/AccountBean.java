/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.ui.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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

    private final SimpleLongProperty id;
    private final SimpleStringProperty accountNumber;
    private final SimpleObjectProperty<AccountType> type;
    private final SimpleStringProperty description;
    private final SimpleObjectProperty<BigDecimal> balance;
    private final SimpleObjectProperty<BigDecimal> creditLine;
    private final SimpleObjectProperty<BigDecimal> beginBalance;
    private final SimpleObjectProperty<Date> beginBalanceDate;
    private final SimpleObjectProperty<List<CustomerBean>> customers;
    private final SimpleObjectProperty<List<TransactionBean>> transactions;

    public AccountBean() {
        this.id = new SimpleLongProperty();
        this.accountNumber = new SimpleStringProperty();
        this.type = new SimpleObjectProperty<>();
        this.description = new SimpleStringProperty();
        this.balance = new SimpleObjectProperty<>();
        this.creditLine = new SimpleObjectProperty<>();
        this.beginBalance = new SimpleObjectProperty<>();
        this.beginBalanceDate = new SimpleObjectProperty<>();
        this.transactions = new SimpleObjectProperty<>();
        this.customers = new SimpleObjectProperty<>();
    }
    
    /**
     * Retrieves the account ID.
     * 
     * @return  Account number
     */
    public Long getId() {
        return this.id.get();
    }
    
    /**
     * Registers the account id.
     * 
     * @param id Account ID
     */
    public void setId(Long id) {
        this.id.set(id);
    }
    
    /**
     * Retrieves the account number.
     * 
     * @return  Account number
     */
    public String getAccountNumber() {
        return this.accountNumber.get();
    }

    /**
     * Registers the account number.
     * 
     * @param accountNumber Account number
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber.set(accountNumber);
    }
    
    /**
     * Retrieves the account type.
     * 
     * @return  Account type
     * @see     gestionbancariaserver.entity.AccountType
     */
    public AccountType getType() {
        return this.type.get();
    }
    
    /**
     * Registers the account type.
     * 
     * @param type  Account type
     * @see         gestionbancariaserver.entity.AccountType
     */
    public void setType(AccountType type) {
        this.type.set(type);
    }

    /**
     * Retrieves the account description.
     * 
     * @return  Account description
     */
    public String getDescription() {
        return this.description.get();
    }

    /**
     * Registers the account description.
     * 
     * @param description   Account description
     */
    public void setDescription(String description) {
        this.description.set(description);
    }

    /**
     * Retrieves the account balance.
     * 
     * @return  Account balance
     */
    public BigDecimal getBalance() {
        return this.balance.get();
    }

    /**
     * Registers the account balance.
     * 
     * @param balance   Account balance
     */
    public void setBalance(BigDecimal balance) {
        this.balance.set(balance);
    }

    /**
     * Retrieves the account maximum credit.
     * 
     * @return  Account credit line
     */
    public BigDecimal getCreditLine() {
        return creditLine.get();
    }

    /**
     * Registers the account maximum credit.
     * 
     * @param creditLine    Account credit line
     */
    public void setCreditLine(BigDecimal creditLine) {
        this.creditLine.set(creditLine);
    }

    /**
     * Retrieves the account begin balance.
     * 
     * @return  Account begin balance
     */
    public BigDecimal getBeginBalance() {
        return this.beginBalance.get();
    }

    /**
     * Registers the account begin balance.
     * 
     * @param beginBalance  Account begin balance
     */
    public void setBeginBalance(BigDecimal beginBalance) {
        this.beginBalance.set(beginBalance);
    }

    /**
     * Retrieves the account creation date.
     * 
     * @return  Account begin balance date
     */
    public Date getBeginBalanceDate() {
        return this.beginBalanceDate.get();
    }

    /**
     * Registers the account creation date.
     * 
     * @param beginBalanceDate  Account begin balance date
     */
    public void setBeginBalanceDate(Date beginBalanceDate) {
        this.beginBalanceDate.set(beginBalanceDate);
    }
    
    /**
     * Retrieves the account owner list.
     * 
     * @return  Account customers
     * @see     gestionbancariaserver.entity.Customer
     */
    @XmlTransient
    public List<CustomerBean> getCustomers() {
        return this.customers.get();
    }

    /**
     * Registers the account owners.
     * 
     * @param customers Account owners
     * @see             gestionbancariaserver.entity.Customer
     */
    public void setCustomers(List<CustomerBean> customers) {
        this.customers.set(customers);
    }
    
    /**
     * Retrieves the account transaction list.
     * 
     * @return  Account transactions
     * @see     gestionbancariaserver.entity.Transaction
     */
    @XmlTransient
    public List<TransactionBean> getTransactions() {
        return this.transactions.get();
    }

    /**
     * Registers the account transactions.
     * 
     * @param transactions  Account transactions
     * @see                 gestionbancariaserver.entity.Transaction
     */
    public void setTransactions(List<TransactionBean> transactions) {
        this.transactions.set(transactions);
    }
    
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
        if ((this.getId() == null && 
                other.getId() != null) || 
                (this.getId() != null && 
                !this.getId().equals(other.getId()))) {
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
        return "bank.management.ui.model.AccountBean[ id=" + this.getId() + " ]";
    }
    
}
