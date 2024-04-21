package entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@javax.persistence.Table(name = "users")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id",insertable=false)
    private int id;

    @Column(name= "login")
    @NotNull
    @Size(min=2,max=15,message = "Логин должен быть длиннее 2 и короче 15 символов")
    private String login;

    @Column(name="password")
    @NotNull
    @Size(min = 3,max = 100,message="Пароль должен быть длиннее 3 и короче 25 символов")
    private String password;

    @Column(name="email")
    @NotNull
    @Email(message = "Invalid email")
    private String email;

    @Column(name="name")
    @NotNull
    @Size(min = 3,max = 25,message="Имя должено быть длиннее 3 и короче 25 символов")
    private String name;

    @Column(name="surname")
    @NotNull
    @Size(min = 3,max = 25,message="Фамилия должена быть длиннее 3 и короче 25 символов")
    private String surname;

    @OneToOne( cascade = CascadeType.ALL )
    @JoinColumn(name = "address_id")
    @Valid
    private Address address;

    @Column(name="details")
    @Size(max = 250,message="Описание должно быть не длиннее 250 символов")
    private String details;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        if (address != null ? !address.equals(user.address) : user.address != null) return false;
        return details != null ? details.equals(user.details) : user.details == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address=" + address +
                ", details='" + details + '\'' +
                '}';
    }
}
