package entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "awaitlist")
public class AwaitList implements Serializable {
    @Id
    @Generated(GenerationTime.INSERT)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",insertable = false)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id_fk")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AwaitList awaitList = (AwaitList) o;

        if (id != awaitList.id) return false;
        return user != null ? user.equals(awaitList.user) : awaitList.user == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
