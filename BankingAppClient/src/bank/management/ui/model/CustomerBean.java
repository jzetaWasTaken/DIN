/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.ui.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="customer")
public class CustomerBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private final SimpleLongProperty id;
    private final SimpleObjectProperty<CredentialBean> credentials;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty street;
    private final SimpleStringProperty city;
    private final SimpleStringProperty nation;
    private final SimpleStringProperty zip;
    private final SimpleStringProperty phone;
    private final SimpleStringProperty email;
    private final SimpleObjectProperty<Date> birthDate;
    private List<AccountBean> accounts;
    
    public CustomerBean() {
        this.id = new SimpleLongProperty();
        this.credentials = new SimpleObjectProperty<>();
        this.lastName = new SimpleStringProperty();
        this.firstName = new SimpleStringProperty();
        this.street = new SimpleStringProperty();
        this.city = new SimpleStringProperty();
        this.nation = new SimpleStringProperty();
        this.zip = new SimpleStringProperty();
        this.phone = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.birthDate = new SimpleObjectProperty<>();
        this.accounts = new ArrayList<>();
    }
    
    /**
     * Retrieves customer ID.
     * 
     * @return  Customer ID
     */
    public Long getId() {
        return this.id.get();
    }

    /**
     * Registers customer ID.
     * 
     * @param id 
     */
    public void setId(Long id) {
        this.id.set(id);
    }

    /**
     * Retrieves customer's last name.
     * 
     * @return  Customer last name
     */
    public String getLastName() {
        return this.lastName.get();
    }

    /**
     * Registers customer's last name
     * 
     * @param lastName  Customer last name
     */
    @XmlElement(name="lastName")
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    /**
     * Retrieves customer's first name.
     * 
     * @return Customer first name
     */
    public String getFirstName() {
        return this.firstName.get();
    }

    /**
     * Registers customer's first name.
     * 
     * @param firstName Customer first name
     */
    @XmlElement(name="firstName")
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    /**
     * Retrieves customer's address street.
     * 
     * @return Customer street
     */
    public String getStreet() {
        return this.street.get();
    }

    /**
     * Registers customer's address street.
     * 
     * @param street Customer street
     */
    @XmlElement(name="street")
    public void setStreet(String street) {
        this.street.set(street);
    }

    /**
     * Retrieves customer's city.
     * 
     * @return Customer city
     */
    public String getCity() {
        return this.city.get();
    }

    /**
     * Registers customer's city.
     * 
     * @param city  Customer city
     */
    @XmlElement(name="city")
    public void setCity(String city) {
        this.city.set(city);
    }

    /**
     * Retrieves customer's nation.
     * 
     * @return Customer nation
     */
    public String getNation() {
        return this.nation.get();
    }

    /**
     * Registers customer's nation.
     * 
     * @param nation  Customer nation
     */
    @XmlElement(name="nation")
    public void setNation(String nation) {
        this.nation.set(nation);
    }

    /**
     * Retrieves customer's date of birth.
     * 
     * @return Customer date of birth
     */
    public Date getBirthDate() {
        return this.birthDate.get();
    }

    /**
     * Registers customer's date of birth.
     * 
     * @param birthDate  Customer date of birth
     */
    @XmlElement(name="birthDate")
    public void setBirthDate(Date birthDate) {
        this.birthDate.set(birthDate);
    }

    /**
     * Retrieves customer's local area code.
     * 
     * @return Local area code
     */
    public String getZip() {
        return this.zip.get();
    }

    /**
     * Registers customer's local area code.
     * 
     * @param zip  Customer local area code
     */
    @XmlElement(name="zip")
    public void setZip(String zip) {
        this.zip.set(zip);
    }

    /**
     * Retrieves customer's phone number.
     * 
     * @return Customer phone number
     */
    public String getPhone() {
        return this.phone.get();
    }

    /**
     * Registers customer's phone number.
     * 
     * @param phone  Customer phone number
     */
    @XmlElement(name="phone")
    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    /**
     * Retrieves customer's contact email.
     * 
     * @return Customer contact email
     */
    public String getEmail() {
        return this.email.get();
    }

    /**
     * Registers customer's contact email.
     * 
     * @param email  Customer contact email
     */
    @XmlElement(name="email")
    public void setEmail(String email) {
        this.email.set(email);
    }

    /**
     * Retrieves customer's credentials.
     * 
     * @return  Customer credentials
     * @see     gestionbancariaserver.entity.Credential
     */
    public CredentialBean getCredentials() {
        return this.credentials.get();
    }

    /**
     * Registers customer's credentials.
     * 
     * @param credentials   Customer credentials
     * @see                 gestionbancariaserver.entity.Credential
     */
    @XmlElement(name="credentials")
    public void setCredentials(CredentialBean credentials) {
        this.credentials.set(credentials);
    }

    /**
     * Retrieves customer's accounts.
     * 
     * @return  Customer accounts
     * @see     gestionbancariaserver.entity.Account 
     */
    public List<AccountBean> getAccounts() {
        return accounts;
    }

    /**
     * Registers customer's accounts.
     * 
     * @param accounts  Customer accounts
     * @see             gestionbancariaserver.entity.Account
     */
    @XmlElement(name="accounts")
    public void setAccounts(List<AccountBean> accounts) {
        this.accounts = accounts;
    }
    
    /**
     * Retrieves objects hash code.
     * 
     * @return  Customer hash code
     * @see     Object#hashCode() 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Specifies the conditions for two customers objects to be equal.
     * 
     * @param object    Object to compare
     * @return          True if the objects are equal, false otherwise
     * @see             Object#equals
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CustomerBean)) {
            return false;
        }
        CustomerBean other = (CustomerBean) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    /**
     * Specifies how customers are printed by default.
     * 
     * @return  The account string format
     * @see     Object#toString() 
     */
    @Override
    public String toString() {
        return "bank.management.ui.model.CustomerBean[id=" + this.getId() + " ]";
    }
}
