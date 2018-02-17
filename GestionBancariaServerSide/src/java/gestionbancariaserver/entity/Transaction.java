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
 * Represents a bank transaction.
 * <ul>
 *      <li><strong>{@link Transaction#id}</strong> Unique identifier of an Account</li> 
 *      <li><strong>{@link Transaction#timeStamp}</strong> Time when the transaction is made</li> 
 *      <li><strong>{@link Transaction#amount}</strong> Transaction amount</li> 
 *      <li><strong>{@link Transaction#balance}</strong> Account balance after the transaction</li> 
 *      <li><strong>{@link Transaction#description}</strong> Transaction description</li> 
 *      <li><strong>{@link Transaction#type}</strong> Transaction type {@link TransactionType}</li>
 *      <li><strong>{@link Transaction#account}</strong> Account the transaction belongs to</li>
 * </ul>
 * 
 * @author Jon Zaballa Zarzosa
 * @version 1.0, 17 Feb 2018
 * @see gestionbancariaserver.entity.Account
 * @see gestionbancariaserver.entity.TransactionType
 * @see gestionbancariaserver.entity.Account#transactions
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
    
    /**
     * Retrieves transaction ID.
     * 
     * @return  Transaction ID
     */
    public Integer getId() {
        return id;
    }
    
    /**
     * Registers transaction ID.
     * 
     * @param id Transaction ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves transaction date.
     * 
     * @return  Transaction date
     */
    public Date getTimeStamp() {
        return timeStamp;
    }

    /**
     * Registers transaction date.
     * 
     * @param timeStamp Transaction date
     */
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Retrieves transaction amount.
     * 
     * @return  Transaction amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Registers transaction amount.
     * 
     * @param amount Transaction amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Retrieves transaction balance.
     * 
     * @return  Transaction balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Registers transaction balance.
     * 
     * @param balance Transaction balance
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * Retrieves transaction description.
     * 
     * @return  Transaction description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Registers transaction description.
     * 
     * @param description Transaction description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves transaction type.
     * 
     * @return  Transaction type
     * @see     gestionbancariaserver.entity.TransactionType
     */
    public TransactionType getType() {
        return type;
    }

    /**
     * Registers transaction type.
     * 
     * @param type  Transaction type
     * @see         gestionbancariaserver.entity.TransactionType
     */
    public void setType(TransactionType type) {
        this.type = type;
    }

    /**
     * Retrieves transaction account.
     * 
     * @return  Transaction account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Registers transaction account.
     * 
     * @param account Transaction account
     */
    public void setAccount(Account account) {
        this.account = account;
    }
    
    /**
     * Retrieves objects hash code.
     * 
     * @return  Transaction hash code
     * @see     Object#hashCode() 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Specifies the conditions for two transaction objects to be equal.
     * 
     * @param object    Object to compare
     * @return          True if the objects are equal, false otherwise
     * @see             Object#equals
     */
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

    /**
     * Specifies how transactions are printed by default.
     * 
     * @return  The account string format
     * @see     Object#toString() 
     */
    @Override
    public String toString() {
        return "gestionbancariaserver.entity.Transaction[ id=" + id + " ]";
    }
    
}
