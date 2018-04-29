/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.LockModeType;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a customer.
 * <ul>
 *      <li><strong>{@link Customer#id}</strong> Unique identifier of a Customer</li> 
 *      <li><strong>{@link Customer#credentials}</strong> Customer credentials</li> 
 *      <li><strong>{@link Customer#lastName}</strong> Customer last name</li> 
 *      <li><strong>{@link Customer#firstName}</strong> Customer first name</li> 
 *      <li><strong>{@link Customer#street}</strong> Customer street</li> 
 *      <li><strong>{@link Customer#city}</strong> Customer city</li>
 *      <li><strong>{@link Customer#nation}</strong> Customer nation</li>
 *      <li><strong>{@link Customer#zip}</strong> Customer local area code</li>
 *      <li><strong>{@link Customer#phone}</strong> Customer phone number</li>
 *      <li><strong>{@link Customer#email}</strong> Customer contact email</li>
 *      <li><strong>{@link Customer#birthDate}</strong> Customer date of birth</li>
 *      <li><strong>{@link Customer#accounts}</strong> Customer accounts</li>
 * </ul>
 * 
 * @author Jon Zaballa Zarzosa
 * @version 1.0, 17 Feb 2018
 * @see bank.management.entity.Account
 * @see bank.management.entity.Credential
 * @see gestionbancariaserver.entity.Account#customers
 */
@Entity
@Table(name = "CUSTOMER", schema = "BANK_MANAGEMENT_DB")
@NamedQueries({
    @NamedQuery(
            name = "findCustomersByLogin",
            query = "SELECT c FROM Customer AS c WHERE c.credentials.login = :login",
            lockMode = LockModeType.NONE),
    @NamedQuery(
            name = "findCustomerById",
            query = "SELECT c FROM Customer AS c WHERE c.id = :id",
            lockMode = LockModeType.NONE),
    @NamedQuery(
            name = "findCustomerByIdPassword",
            query = "SELECT c FROM Customer AS c "
                    + "WHERE c.id = :id AND c.credentials.password = :password",
            lockMode = LockModeType.NONE)
})
@XmlRootElement(name="customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private Long id;
    
    @OneToOne(cascade = javax.persistence.CascadeType.ALL,
            fetch = javax.persistence.FetchType.LAZY)
    @MapsId
    @JoinColumn(name="ID")
    private Credential credentials;

    
    @Column(name = "LAST_NAME")
    private String lastName;

    
    @Column(name = "FIRST_NAME")
    private String firstName;

    private String street;

    private String city;

    private String nation;

    //TODO Better pattern zip
    
    private String zip;

    //TODO Better pattern phone
    
    private String phone;

    
    private String email;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "BIRTH_DATE")
    private Date birthDate;
    
    @ManyToMany(fetch = javax.persistence.FetchType.LAZY)
    @JoinTable(
            name = "CUSTOMER_ACCOUNTS",
            schema = "BANK_MANAGEMENT_DB",
            joinColumns
            = @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID"),
            inverseJoinColumns
            = @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ID"))
    private List<Account> accounts;
    
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
    @NotNull
    public String getLastName() {
        return lastName;
    }

    /**
     * Registers customer's last name
     * 
     * @param lastName  Customer last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retrieves customer's first name.
     * 
     * @return Customer first name
     */
    @NotNull
    public String getFirstName() {
        return firstName;
    }

    /**
     * Registers customer's first name.
     * 
     * @param firstName Customer first name
     */
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
    public void setNation(String nation) {
        this.nation = nation;
    }

    /**
     * Retrieves customer's date of birth.
     * 
     * @return Customer date of birth
     */
    @Past
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Registers customer's date of birth.
     * 
     * @param birthDate  Customer date of birth
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Retrieves customer's local area code.
     * 
     * @return Local area code
     */
    @Pattern(regexp = "[0-9]{5}", message = "Invalid zip format")
    public String getZip() {
        return zip;
    }

    /**
     * Registers customer's local area code.
     * 
     * @param zip  Customer local area code
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * Retrieves customer's phone number.
     * 
     * @return Customer phone number
     */
    @Pattern(regexp = "[0-9]{9}", message = "Invalid phone format")
    public String getPhone() {
        return phone;
    }

    /**
     * Registers customer's phone number.
     * 
     * @param phone  Customer phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Retrieves customer's contact email.
     * 
     * @return Customer contact email
     */
    @Pattern(
            regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`"
            + "{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z"
            + "0-9-]*[a-z0-9])?",
            message = "Invalid email format"
    )
    public String getEmail() {
        return email;
    }

    /**
     * Registers customer's contact email.
     * 
     * @param email  Customer contact email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves customer's credentials.
     * 
     * @return  Customer credentials
     * @see     bank.management.entity.Credential
     */
    public Credential getCredentials() {
        return credentials;
    }

    /**
     * Registers customer's credentials.
     * 
     * @param credentials   Customer credentials
     * @see                 bank.management.entity.Credential
     */
    public void setCredentials(Credential credentials) {
        this.credentials = credentials;
    }

    /**
     * Retrieves customer's accounts.
     * 
     * @return  Customer accounts
     * @see     bank.management.entity.Account 
     */
    public List<Account> getAccounts() {
        return accounts;
    }

    /**
     * Registers customer's accounts.
     * 
     * @param accounts  Customer accounts
     * @see             bank.management.entity.Account
     */
    public void setAccounts(List<Account> accounts) {
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
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
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
