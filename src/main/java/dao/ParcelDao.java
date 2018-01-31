package dao;

import entity.Parcel;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import service.HibernateUtil;

import java.util.List;

public class ParcelDao {

    private Session session;

    public ParcelDao(Session session) {
        this.session = session;
    }

    public List<Parcel> getAll() {
        Query query = session.createQuery("FROM entity.Parcel");
        return query.list();
    }
    public Parcel getParcelById(int id){
        return session.get(Parcel.class,id);
    }

    public void delete(Parcel parcel){
        session.delete(parcel);
    }

    public void add(Parcel parcel){
        session.save(parcel);
    }

    public void update(Parcel parcel){
        Parcel parcelForUpdate= session.get(Parcel.class,parcel.getId());
        parcelForUpdate.setStatus(parcel.getStatus());
        parcelForUpdate.setDetails(parcel.getDetails());
    }

    public List<Parcel> getParcelByRecipientAndRegcode(User recipient,String regcode){
        List<Parcel> parcelList=session.createQuery("from entity.Parcel where recipient=:recipient and status=:status and registrationCode=:regcode",Parcel.class)
                .setParameter("recipient",recipient)
                .setParameter("status","Sended")
                .setParameter("regcode",regcode)
                .list();
        return parcelList;
    }
}
