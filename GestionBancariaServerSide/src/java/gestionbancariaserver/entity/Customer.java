/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionbancariaserver.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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

/**
 *
 * @author ubuntu
 */
@Entity
@Table(name = "CUSTOMER", schema = "BANK_MANAGEMENT_DB")
@NamedQueries({
    @NamedQuery(
            name = "findCustomerById",
            query = "SELECT c FROM Customer AS c WHERE c.id = :id")
})
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long id;

    @NotNull
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotNull
    @Column(name = "FIRST_NAME")
    private String firstName;

    private String street;

    private String city;

    private String nation;

    //TODO pattern zip
    @Pattern(regexp = "[0-9]{5}",
            message = "Invalid zip format")
    private String zip;

    //TODO pattern phone
    @Pattern(regexp = "[0-9]{9}",
            message = "Invalid phone format")
    private String phone;

    //TODO pattern email
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`"
            + "{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z"
            + "0-9-]*[a-z0-9])?",
            message = "Invalid email format")
    private String email;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Past
    @Column(name = "BIRTH_DATE")
    private Date birthDate;
    ;
    
    @OneToOne(cascade = javax.persistence.CascadeType.ALL,
            fetch = javax.persistence.FetchType.LAZY)
    @MapsId
    private Credential credentials;

    @ManyToMany(fetch = javax.persistence.FetchType.LAZY)
    @JoinTable(name = "CUSTOMER_ACCOUNTS",
            joinColumns
            = @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID"),
            inverseJoinColumns
            = @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ACCOUNT_ID")
    )
    private Collection<Account> accounts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Credential getCredentials() {
        return credentials;
    }

    public void setCredentials(Credential credentials) {
        this.credentials = credentials;
    }

    public Collection<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Collection<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gestionbancariaserver.entity.Customer[ id=" + id + " ]";
    }

}
