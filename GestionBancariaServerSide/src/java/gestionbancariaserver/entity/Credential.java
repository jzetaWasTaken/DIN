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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents {@link Customer} credentials.
 * <ul>
 *      <li><strong>{@link Credential#id}</strong> Unique identifier of a Customer</li> 
 *      <li><strong>{@link Credential#login}</strong> The user NIF used to sign in</li> 
 *      <li><strong>{@link Credential#password}</strong> User password</li> 
 *      <li><strong>{@link Credential#lastModifiedOn}</strong> Last time the credentials where modified</li> 
 *      <li><strong>{@link Credential#lastSignedIn}</strong> Last time the customer signed in</li> 
 *      <li><strong>{@link Credential#createdOn}</strong> Date of customer creation</li>
 * </ul>
 * 
 * @author Jon Zaballa Zarzosa
 * @version 1.0, 17 Feb 2018
 * @see gestionbancariaserver.entity.Customer
 * @see gestionbancariaserver.entity.Customer#credentials
 */
@Entity
@Table(name="CREDENTIAL", schema="BANK_MANAGEMENT_DB")
@XmlRootElement(name="credential")
public class Credential implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    private String login;
    
    @NotNull
    private String password;
            
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastModifiedOn;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastSignedIn;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createdOn;

    /**
     * Retrieves the ID.
     * 
     * @return Credential ID
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Registers the ID.
     * 
     * @param id    Credential ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the login.
     * 
     * @return login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Registers the login.
     * 
     * @param login Customer login to sign in
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Retrieves the customer password.
     * 
     * @return Customer password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Registers the password.
     * 
     * @param password  Customer password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the date of the last modification to customer credentials.
     * 
     * @return Last modification date
     */
    public Date getLastModifiedOn() {
        return lastModifiedOn;
    }

    /**
     * Registers the last time the Customer credentials were modified.
     * 
     * @param lastModifiedOn    Date of last modification
     */
    public void setLastModifiedOn(Date lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }

    /**
     * Retrieves the date of the last time the user signed in.
     * 
     * @return Date of the last access
     */
    public Date getLastSignedIn() {
        return lastSignedIn;
    }
    
    /**
     * Registers the last date the customer accessed the application.
     * 
     * @param lastSignedIn  Date of the last access
     */
    public void setLastSignedIn(Date lastSignedIn) {
        this.lastSignedIn = lastSignedIn;
    }

    /**
     * Retrieves the date when the customer was created.
     * 
     * @return  Date of customer creation
     */
    public Date getCreatedOn() {
        return createdOn;
    }

    /**
     * Registers the date the user was created
     * 
     * @param createdOn Date of customer creation
     */
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * Retrieves objects hash code.
     * 
     * @return  Credential hash code
     * @see     Object#hashCode() 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Specifies the conditions for two credential objects to be equal.
     * 
     * @param object    Object to compare
     * @return          True if the objects are equal, false otherwise
     * @see             Object#equals
     */
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

    /**
     * Specifies how credentials are printed by default.
     * 
     * @return  The account string format
     * @see     Object#toString() 
     */
    @Override
    public String toString() {
        return "gestionbancariaserver.entity.Credential[ id=" + id + " ]";
    }
}
