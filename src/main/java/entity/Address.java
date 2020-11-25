package entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "address")
public class Address implements Serializable {

    @Id
    @Generated(GenerationTime.INSERT)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",insertable = false)
    private int id;

    @OneToOne
    @JoinColumn(name="country_shortcut")
    private Country country;

    @Column(name = "city")
    @NotNull
    @Size(min = 2,max = 25,message="Название города должено быть длиннее 2 и короче 25 символов")
    private String city;

    @Column(name = "address")
    @NotNull
    @Size(min = 5,max = 45,message="Поле адресс должен быть длиннее 5 и короче 45 символов")
    private String address;

    @Column(name = "postcode")
    @NotNull
    @Size(min = 2,max = 15,message="Почтовый индекс должен быть длиннее 2 и короче 15 символов")
    private String postcode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
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

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return id == address1.id &&
                country.equals(address1.country) &&
                city.equals(address1.city) &&
                address.equals(address1.address) &&
                postcode.equals(address1.postcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city, address, postcode);
    }
}
