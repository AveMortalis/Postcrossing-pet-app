package entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="parcels")
public class Parcel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id",insertable=false)
    private int id;

    @OneToOne
    @JoinColumn(name="mailer_fk")
    private User mailer;

    @OneToOne
    @JoinColumn(name="recipient_fk")
    private User recipient;

    @Column(name = "send_date")
    private Date sendDate;

    @Column(name = "registration_code")
    private String registrationCode;

    @Column(name = "status")
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getMailer() {
        return mailer;
    }

    public void setMailer(User mailer) {
        this.mailer = mailer;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parcel parcel = (Parcel) o;

        if (id != parcel.id) return false;
        if (mailer != null ? !mailer.equals(parcel.mailer) : parcel.mailer != null) return false;
        if (recipient != null ? !recipient.equals(parcel.recipient) : parcel.recipient != null) return false;
        if (sendDate != null ? !sendDate.equals(parcel.sendDate) : parcel.sendDate != null) return false;
        if (registrationCode != null ? !registrationCode.equals(parcel.registrationCode) : parcel.registrationCode != null)
            return false;
        return status != null ? status.equals(parcel.status) : parcel.status == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (mailer != null ? mailer.hashCode() : 0);
        result = 31 * result + (recipient != null ? recipient.hashCode() : 0);
        result = 31 * result + (sendDate != null ? sendDate.hashCode() : 0);
        result = 31 * result + (registrationCode != null ? registrationCode.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
