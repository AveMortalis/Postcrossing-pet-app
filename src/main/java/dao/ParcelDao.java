package dao;

import entity.Parcel;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import service.HibernateUtil;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ParcelDao {

    private SessionFactory sessionFactory;

    @Autowired
    public ParcelDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Parcel> getAll() {
        Session session=sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM entity.Parcel");
        return query.list();
    }
    public Parcel getParcelById(int id){
        Session session=sessionFactory.getCurrentSession();
        return session.get(Parcel.class,id);
    }

    public void delete(Parcel parcel){
        Session session=sessionFactory.getCurrentSession();
        session.delete(parcel);
    }

    public void add(Parcel parcel){
        Session session=sessionFactory.getCurrentSession();
        session.save(parcel);
    }

    public void update(Parcel parcel){
        Session session=sessionFactory.getCurrentSession();
        Parcel parcelForUpdate= session.get(Parcel.class,parcel.getId());
        parcelForUpdate.setStatus(parcel.getStatus());
        parcelForUpdate.setDetails(parcel.getDetails());
    }

    public Parcel getParcelByRecipientAndRegcode(User recipient,String regcode){
        Session session=sessionFactory.getCurrentSession();
        List<Parcel> parcelList=session.createQuery("from entity.Parcel where recipient=:recipient and status=:status and registrationCode=:regcode",Parcel.class)
                .setParameter("recipient",recipient)
                .setParameter("status","Sended")
                .setParameter("regcode",regcode)
                .list();
        return parcelList.isEmpty()?null:parcelList.get(0);
    }

    public List<Parcel> getAllSendedUserParcels(User user){
        Session session=sessionFactory.getCurrentSession();
        List<Parcel> parcelList=session.createQuery("from entity.Parcel where mailer=:mailer",Parcel.class)
                .setParameter("mailer",user)
                .list();
        return parcelList.isEmpty()?null:parcelList;
    }

    public List<Parcel> getAllRecivedUserParcels(User user){
        Session session=sessionFactory.getCurrentSession();
        List<Parcel> parcelList=session.createQuery("from entity.Parcel where recipient=:recipient",Parcel.class)
                .setParameter("recipient",user)
                .list();
        return parcelList.isEmpty()?null:parcelList;
    }

}
