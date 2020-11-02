package dao;

import entity.AwaitList;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AwaitListDao implements IWaiters{

    private SessionFactory sessionFactory;


    @Autowired
    public AwaitListDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<AwaitList> getAll(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM entity.AwaitList",AwaitList.class);
        return query.list();
    }
    
    public AwaitList getFirstRecordFromAwaitList(User user){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM entity.AwaitList where user.id!=:userId and id=(select MIN(id) from entity.AwaitList where user.id!=:userId)",AwaitList.class).setParameter("userId",user.getId());
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


    public void addUserToAwaitList(User user){
        Session session = sessionFactory.getCurrentSession();
        AwaitList awaitList=new AwaitList();
        awaitList.setUser(user);
        session.save(awaitList);
    }
}
