package dao;

import entity.AwaitList;
import entity.Parcel;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import service.HibernateUtil;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class AwaitListDao {

    private SessionFactory sessionFactory;

    @Autowired
    public AwaitListDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<AwaitList> getAll(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM entity.AwaitList");
        return query.list();
    }
    
    public AwaitList getFirst(User user){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM entity.AwaitList where user_id!=:userId",AwaitList.class).setParameter("userId",user.getId());
        if(query.list().isEmpty()==false){
            AwaitList awaitList=(AwaitList) query.list().get(0);
            return awaitList;
        }else {
            return null;
        }
    }

    public void delete(AwaitList awaitList){
        Session session = sessionFactory.getCurrentSession();
        session.delete(awaitList);
    }

    public void add(AwaitList awaitList){
        Session session = sessionFactory.getCurrentSession();
        session.save(awaitList);
    }
    public void add(User user){
        Session session = sessionFactory.getCurrentSession();
        AwaitList awaitList=new AwaitList();
        awaitList.setUser_id(user.getId());
        session.save(awaitList);
    }
}
