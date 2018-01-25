/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionbancariaserver.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.EnumType.STRING;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ubuntu
 */
@Entity
@Table(name="ACCOUNT", schema="BANK_MANAGEMENT_DB")
@NamedQueries({
    @NamedQuery(
            name="findAccountsByCustomerId",
            query="SELECT a FROM Account AS a, IN (a.customers) AS c WHERE c.id = :id")
})
public class Account implements Serializable {

    public static enum AccountType {SAVINGS, CHECK, CREDIT, IPF}
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="ACCOUNT_ID")
    private Integer accountId;
    
    @Enumerated(STRING)
    private AccountType type;
    
    private String description;
    
    @NotNull
    private BigDecimal balance;
    
    @Column(name="CREDIT_LINE")
    private BigDecimal creditLine;
    
    @NotNull
    @Column(name="BEGIN_BALANCE")
    private BigDecimal beginBalance;
    
    @Temporal(DATE)
    @Column(name="BEGIN_BALANCE_DATE")
    private Date beginBalanceDate;
    
    @ManyToMany(mappedBy = "accounts")
    private Collection<Customer> customers;
    
    @OneToMany(mappedBy = "account",
            fetch = javax.persistence.FetchType.LAZY,
            cascade = javax.persistence.CascadeType.REMOVE)
    private Collection<Transaction> transactions;
    
    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getCreditLine() {
        return creditLine;
    }

    public void setCreditLine(BigDecimal creditLine) {
        this.creditLine = creditLine;
    }

    public BigDecimal getBeginBalance() {
        return beginBalance;
    }

    public void setBeginBalance(BigDecimal beginBalance) {
        this.beginBalance = beginBalance;
    }

    public Date getBeginBalanceDate() {
        return beginBalanceDate;
    }

    public void setBeginBalanceDate(Date beginBalanceDate) {
        this.beginBalanceDate = beginBalanceDate;
    }

    public Collection<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Collection<Customer> customers) {
        this.customers = customers;
    }

    public Collection<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Collection<Transaction> transactions) {
        this.transactions = transactions;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountId != null ? accountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the accountId fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.accountId == null && other.accountId != null) || (this.accountId != null && !this.accountId.equals(other.accountId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gestionbancariaserver.entity.Account[ id=" + accountId + " ]";
    }
    
}
