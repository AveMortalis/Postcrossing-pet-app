package entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

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

    @Column(name = "user_id_fk")
    private int user_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AwaitList awaitList = (AwaitList) o;

        if (id != awaitList.id) return false;
        return user_id == awaitList.user_id;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + user_id;
        return result;
    }

    @Override
    public String toString() {
        return "AwaitList{" +
                "id=" + id +
                ", user_id=" + user_id +
                '}';
    }
}
