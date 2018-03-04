/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_bancaria.ui.model;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
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
@XmlRootElement(name="credential")
public class CredentialBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String login;
    private String password;
    private Date lastModifiedOn;
    private Date lastSignedIn;
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
    @XmlElement(name="login")
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
    @XmlElement(name="password")
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
    @XmlElement(name="lastModifiedOn")
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
    @XmlElement(name="lastSignedIn")
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
    @XmlElement(name="createdOn")
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
        if (!(object instanceof CredentialBean)) {
            return false;
        }
        CredentialBean other = (CredentialBean) object;
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
