/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionbancariaserver.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.EnumType.STRING;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.LockModeType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ubuntu
 */
@Entity
@Table(name="BANK_TRANSACTION", schema="BANK_MANAGEMENT_DB")
@NamedQueries({
    @NamedQuery(
        name="findTransactionsByAccount",
        query="SELECT t FROM Transaction AS t WHERE t.account.id = :accountId ORDER BY t.timeStamp",
        lockMode = LockModeType.NONE),
    @NamedQuery(
        name="findDepositsByAccount",
        query="SELECT t FROM Transaction AS t "
                + "WHERE t.type = gestionbancariaserver.entity.TransactionType.DEPOSIT "
                + "AND t.account.id = :accountId "
                + "ORDER BY t.timeStamp",
        lockMode = LockModeType.NONE),
    @NamedQuery(
        name="findTransfersByAccount",
        query="SELECT t FROM Transaction AS t "
                + "WHERE t.type = gestionbancariaserver.entity.TransactionType.TRANSFER "
                + "AND t.account.id = :accountId "
                + "ORDER BY t.timeStamp",
        lockMode = LockModeType.NONE),
    @NamedQuery(
        name="findPaymentsByAccount",
        query="SELECT t FROM Transaction AS t "
                + "WHERE t.type = gestionbancariaserver.entity.TransactionType.PAYMENT "
                + "AND t.account.id = :accountId "
                + "ORDER BY t.timeStamp",
        lockMode = LockModeType.NONE),
})
@XmlRootElement(name="transaction")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="TRANSACTION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Temporal(DATE)
    @Column(name="TIME_STAMP")
    private Date timeStamp;
    
    @NotNull
    private BigDecimal amount;
    
    @NotNull
    private BigDecimal balance;
    
    private String description;
    
    @Enumerated(STRING)
    private TransactionType type;
    
    @ManyToOne(fetch=javax.persistence.FetchType.LAZY)
    @JoinColumn(name="ACCOUNT_ID", nullable=false)
    private Account account;

    public Transaction() {
    }

    public Transaction(Date timeStamp, BigDecimal amount, BigDecimal balance, String description, TransactionType type, Account account) {
        this.timeStamp = timeStamp;
        this.amount = amount;
        this.balance = balance;
        this.description = description;
        this.type = type;
        this.account = account;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Transaction)) {
            return false;
        }
        Transaction other = (Transaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gestionbancariaserver.entity.Transaction[ id=" + id + " ]";
    }
    
}
