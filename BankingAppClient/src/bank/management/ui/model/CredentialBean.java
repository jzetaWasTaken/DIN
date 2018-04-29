/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.ui.model;

import java.io.Serializable;
import java.util.Date;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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
    
    private final SimpleLongProperty id;
    private final SimpleStringProperty login;
    private final SimpleStringProperty password;
    
    public CredentialBean() {
        this.id = new SimpleLongProperty();
        this.login = new SimpleStringProperty();
        this.password = new SimpleStringProperty();
    }

    /**
     * Retrieves the ID.
     * 
     * @return Credential ID
     */
    public Long getId() {
        return this.id.get();
    }
    
    /**
     * Registers the ID.
     * 
     * @param id    Credential ID
     */
    public void setId(Long id) {
        this.id.set(id);
    }

    /**
     * Retrieves the login.
     * 
     * @return login
     */
    public String getLogin() {
        return this.login.get();
    }

    /**
     * Registers the login.
     * 
     * @param login Customer login to sign in
     */
    @XmlElement(name="login")
    public void setLogin(String login) {
        this.login.set(login);
    }

    /**
     * Retrieves the customer password.
     * 
     * @return Customer password
     */
    public String getPassword() {
        return this.password.get();
    }

    /**
     * Registers the password.
     * 
     * @param password  Customer password
     */
    @XmlElement(name="password")
    public void setPassword(String password) {
        this.password.set(password);
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
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
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
        return "bank.management.ui.model.CredentialBean[ id=" + this.getId() + " ]";
    }
}
