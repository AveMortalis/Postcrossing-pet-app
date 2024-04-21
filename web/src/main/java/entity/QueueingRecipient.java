package entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "queueingrecipients")
public class QueueingRecipient implements Serializable {

    @Id
    @Generated(GenerationTime.INSERT)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",insertable = false)
    private int position;

    @OneToOne
    @JoinColumn(name = "user_id_fk")
    private User user;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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

        QueueingRecipient queueingRecipient = (QueueingRecipient) o;

        if (position != queueingRecipient.position) return false;
        return user != null ? user.equals(queueingRecipient.user) : queueingRecipient.user == null;
    }

    @Override
    public int hashCode() {
        int result = position;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
