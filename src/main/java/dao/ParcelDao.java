package dao;

import entity.Parcel;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParcelDao implements IParcelDao {

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
        session.update(parcel);
    }

    public void updateParcelStatus(Parcel parcel){
        Session session=sessionFactory.getCurrentSession();
        Parcel parcelForUpdate= session.get(Parcel.class,parcel.getId());
        parcelForUpdate.setStatus(parcel.getStatus());
    }

    public Parcel getParcelByRecipientAndRegcode(User recipient,String regcode){
        Session session=sessionFactory.getCurrentSession();
        List<Parcel> parcelList=session.createQuery("from entity.Parcel where recipient=:recipient and status=:status and registrationCode=:regcode",Parcel.class)
                .setParameter("recipient",recipient)
                .setParameter("status","Sent")
                .setParameter("regcode",regcode)
                .list();
        return parcelList.isEmpty()?null:parcelList.get(0);
    }

    public List<Parcel> getAllSentUserParcelsHaveReceived(User mailer){
        Session session=sessionFactory.getCurrentSession();
        List<Parcel> parcelList=session.createQuery("from entity.Parcel where mailer=:mailer and status=:status",Parcel.class)
                .setParameter("mailer",mailer)
                .setParameter("status","Received")
                .list();
        return parcelList;
    }

    public List<Parcel> getAllTravelingUserParcels(User mailer){
        Session session=sessionFactory.getCurrentSession();
        List<Parcel> parcelList=session.createQuery("from entity.Parcel where mailer=:mailer and status=:status",Parcel.class)
                .setParameter("mailer",mailer)
                .setParameter("status","Sent")
                .list();
        return parcelList;
    }

    public List<Parcel> getAllSentUserParcels(User user){
        Session session=sessionFactory.getCurrentSession();
        List<Parcel> parcelList=session.createQuery("from entity.Parcel where mailer=:mailer",Parcel.class)
                .setParameter("mailer",user)
                .list();
        return parcelList.isEmpty()?null:parcelList;
    }

    public List<Parcel> getAllReceivedParcelsByUser(User user){
        Session session=sessionFactory.getCurrentSession();
        List<Parcel> parcelList=session.createQuery("from entity.Parcel where recipient=:recipient and status=:status",Parcel.class)
                .setParameter("recipient",user)
                .setParameter("status","Received")
                .list();
        return parcelList.isEmpty()?null:parcelList;
    }

    public int getCountOfParcels(){
        Session session=sessionFactory.getCurrentSession();
        return ((Number)(session.createQuery("select count(*) from entity.Parcel")).uniqueResult()).intValue();
    }

    public int getCountOfReceivedParcels(){
        Session session=sessionFactory.getCurrentSession();
        return ((Number)(session.createQuery("select count(*) from entity.Parcel where status=:status")
                .setParameter("status","Received")
                .getSingleResult())).intValue();

    }


}
