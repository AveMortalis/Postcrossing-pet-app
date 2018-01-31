package entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "address")
public class Address implements Serializable {

    @Id
    @Generated(GenerationTime.INSERT)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",insertable = false)

    private int id;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "country_shortcut")
    private String countryShortcut;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @Column(name = "postcode")
    private int postcode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryShortcut() {
        return countryShortcut;
    }

    public void setCountryShortcut(String countryShortcut) {
        this.countryShortcut = countryShortcut;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address1 = (Address) o;

        if (id != address1.id) return false;
        if (postcode != address1.postcode) return false;
        if (countryName != null ? !countryName.equals(address1.countryName) : address1.countryName != null)
            return false;
        if (countryShortcut != null ? !countryShortcut.equals(address1.countryShortcut) : address1.countryShortcut != null)
            return false;
        if (city != null ? !city.equals(address1.city) : address1.city != null) return false;
        return address != null ? address.equals(address1.address) : address1.address == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (countryName != null ? countryName.hashCode() : 0);
        result = 31 * result + (countryShortcut != null ? countryShortcut.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + postcode;
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", countryName='" + countryName + '\'' +
                ", countryShortcut='" + countryShortcut + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", postcode=" + postcode +
                '}';
    }
}
