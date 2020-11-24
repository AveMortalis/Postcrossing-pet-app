package dao;

import entity.QueueingRecipient;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QueueingRecipientDaoDao implements IQueueingRecipientDao {

    private SessionFactory sessionFactory;

    @Autowired
    public QueueingRecipientDaoDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<QueueingRecipient> getAll(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM entity.QueueingRecipient", QueueingRecipient.class);
        return query.list();
    }
    
    public QueueingRecipient getFirstRecipientFromFromQueueingRecipientsButNotCurrent(User user){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM entity.QueueingRecipient where position=(select MIN(position) from entity.QueueingRecipient where user.id!=:userId)", QueueingRecipient.class).setParameter("userId",user.getId());
        if(query.list().isEmpty()){
            return null;
        }else {
            QueueingRecipient queueingRecipient =(QueueingRecipient) query.list().get(0);
            return queueingRecipient;
        }
    }

    public void delete(QueueingRecipient queueingRecipient){
        Session session = sessionFactory.getCurrentSession();
        session.delete(queueingRecipient);
    }

    public void add(QueueingRecipient queueingRecipient){
        Session session = sessionFactory.getCurrentSession();
        session.save(queueingRecipient);
    }


    public void addUserToAwaitList(User user){
        Session session = sessionFactory.getCurrentSession();
        QueueingRecipient queueingRecipient =new QueueingRecipient();
        queueingRecipient.setUser(user);
        session.save(queueingRecipient);
    }
}
