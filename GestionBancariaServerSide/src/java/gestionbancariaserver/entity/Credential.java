/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionbancariaserver.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.LockModeType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ubuntu
 */
@Entity
@Table(name="CREDENTIAL", schema="BANK_MANAGEMENT_DB")
@NamedQueries({
    @NamedQuery(
        name="findCustomerIdByLogin",
        query="SELECT c.id FROM Credential AS c WHERE c.login = :login AND c.passw = :passw",
        lockMode = LockModeType.NONE)
})
public class Credential implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    private String login;
    
    @NotNull
    private String passw;
            
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastModifiedOn;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastSignedIn;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createdOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public Date getLastModifiedOn() {
        return lastModifiedOn;
    }

    public void setLastModifiedOn(Date lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }

    public Date getLastSignedIn() {
        return lastSignedIn;
    }

    public void setLastSignedIn(Date lastSignedIn) {
        this.lastSignedIn = lastSignedIn;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Credential)) {
            return false;
        }
        Credential other = (Credential) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gestionbancariaserver.entity.Credential[ id=" + id + " ]";
    }
    
}
