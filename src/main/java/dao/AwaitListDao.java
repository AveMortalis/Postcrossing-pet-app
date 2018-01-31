package dao;

import entity.AwaitList;
import entity.Parcel;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import service.HibernateUtil;

import java.util.List;

public class AwaitListDao {

    private Session session;

    public AwaitListDao(Session session) {
        this.session = session;
    }

    public List<AwaitList> getAll(){
        Query query = session.createQuery("FROM entity.AwaitList");
        return query.list();
    }
    
    public AwaitList getFirst(User user){
        Query query = session.createQuery("FROM entity.AwaitList where user_id!=:userId",AwaitList.class).setParameter("userId",user.getId());
        if(query.list().isEmpty()==false){
            AwaitList awaitList=(AwaitList) query.list().get(0);
            return awaitList;
        }else {
            return null;
        }
    }

    public void delete(AwaitList awaitList){
        session.delete(awaitList);
    }

    public void add(AwaitList awaitList){
        session.save(awaitList);
    }
    public void add(User user){
        AwaitList awaitList=new AwaitList();
        awaitList.setUser_id(user.getId());
        session.save(awaitList);
    }
}
