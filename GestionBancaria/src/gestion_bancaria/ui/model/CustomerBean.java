/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_bancaria.ui.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="customer")
public class CustomerBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id;
    private CredentialBean credentials;
    private String lastName;
    private String firstName;
    private String street;
    private String city;
    private String nation;
    private String zip;
    private String phone;
    private String email;
    private Date birthDate;
    private Collection<AccountBean> accounts;
    
    /**
     * Retrieves customer ID.
     * 
     * @return  Customer ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Registers customer ID.
     * 
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves customer's last name.
     * 
     * @return  Customer last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Registers customer's last name
     * 
     * @param lastName  Customer last name
     */
    @XmlElement(name="lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retrieves customer's first name.
     * 
     * @return Customer first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Registers customer's first name.
     * 
     * @param firstName Customer first name
     */
    @XmlElement(name="firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Retrieves customer's address street.
     * 
     * @return Customer street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Registers customer's address street.
     * 
     * @param street Customer street
     */
    @XmlElement(name="street")
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Retrieves customer's city.
     * 
     * @return Customer city
     */
    public String getCity() {
        return city;
    }

    /**
     * Registers customer's city.
     * 
     * @param city  Customer city
     */
    @XmlElement(name="city")
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Retrieves customer's nation.
     * 
     * @return Customer nation
     */
    public String getNation() {
        return nation;
    }

    /**
     * Registers customer's nation.
     * 
     * @param nation  Customer nation
     */
    @XmlElement(name="nation")
    public void setNation(String nation) {
        this.nation = nation;
    }

    /**
     * Retrieves customer's date of birth.
     * 
     * @return Customer date of birth
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Registers customer's date of birth.
     * 
     * @param birthDate  Customer date of birth
     */
    @XmlElement(name="birthDate")
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Retrieves customer's local area code.
     * 
     * @return Local area code
     */
    public String getZip() {
        return zip;
    }

    /**
     * Registers customer's local area code.
     * 
     * @param zip  Customer local area code
     */
    @XmlElement(name="zip")
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * Retrieves customer's phone number.
     * 
     * @return Customer phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Registers customer's phone number.
     * 
     * @param phone  Customer phone number
     */
    @XmlElement(name="phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Retrieves customer's contact email.
     * 
     * @return Customer contact email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Registers customer's contact email.
     * 
     * @param email  Customer contact email
     */
    @XmlElement(name="email")
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves customer's credentials.
     * 
     * @return  Customer credentials
     * @see     gestionbancariaserver.entity.Credential
     */
    public CredentialBean getCredentials() {
        return credentials;
    }

    /**
     * Registers customer's credentials.
     * 
     * @param credentials   Customer credentials
     * @see                 gestionbancariaserver.entity.Credential
     */
    @XmlElement(name="credentials")
    public void setCredentials(CredentialBean credentials) {
        this.credentials = credentials;
    }

    /**
     * Retrieves customer's accounts.
     * 
     * @return  Customer accounts
     * @see     gestionbancariaserver.entity.Account 
     */
    public Collection<AccountBean> getAccounts() {
        return accounts;
    }

    /**
     * Registers customer's accounts.
     * 
     * @param accounts  Customer accounts
     * @see             gestionbancariaserver.entity.Account
     */
    @XmlElement(name="accounts")
    public void setAccounts(Collection<AccountBean> accounts) {
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
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
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
        return "gestionbancariaserver.entity.Customer[ id=" + id + " ]";
    }
}
