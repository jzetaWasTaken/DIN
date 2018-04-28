/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.ui.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author jon
 */
public class TransactionBean {
    
    private final SimpleIntegerProperty id;
    private final SimpleObjectProperty<Date> timeStamp;
    private final SimpleObjectProperty<BigDecimal> amount;
    private final SimpleObjectProperty<BigDecimal> balance;
    private final SimpleStringProperty description;
    private final SimpleObjectProperty<TransactionType> type;
    private final SimpleObjectProperty<AccountBean> account;
            
    public TransactionBean() {
        this.id = new SimpleIntegerProperty();
        this.timeStamp = new SimpleObjectProperty<>();
        this.amount = new SimpleObjectProperty<>();
        this.balance = new SimpleObjectProperty<>();
        this.description = new SimpleStringProperty();
        this.type = new SimpleObjectProperty<>();
        this.account = new SimpleObjectProperty<>();
    }

    public Integer getId() {
        return this.id.get();
    }

    public void setId(Integer id) {
        this.id.set(id);
    }
    
    public Date getTimeStamp() {
        return this.timeStamp.get();
    }
    
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp.set(timeStamp);
    }
    
    public BigDecimal getAmount() {
        return this.amount.get();
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount.set(amount);
    }
    
    public BigDecimal getBalance() {
        return this.balance.get();
    }
    
    public void setBalance(BigDecimal balance) {
        this.balance.set(balance);
    }
    
    public String getDescription() {
        return this.description.get();
    }
    
    public void setDescription(String description) {
        this.description.set(description);
    }
    
    public TransactionType getType() {
        return this.type.get();
    }
    
    public void setType(TransactionType type) {
        this.type.set(type);
    }
    
    public AccountBean getAccount() {
        return this.account.get();
    }
    
    public void setAccount(AccountBean account) {
        this.account.set(account);
    }
}
