package dao;

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
public class UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<User> getAll(){
        Session session=sessionFactory.getCurrentSession();
        Query query=session.createQuery("FROM entity.User");
        return query.list();
    }

    public User getUserById(int id){
        Session session=sessionFactory.getCurrentSession();
        return session.get(User.class,id);
    }

    public void deleteUser(User user){
        Session session=sessionFactory.getCurrentSession();
        session.delete(user);
    }

    public void addUser(User user){
        Session session=sessionFactory.getCurrentSession();
        session.save(user);
    }
    
    public void updateUser(User user){
        Session session=sessionFactory.getCurrentSession();
        session.update(user);
    }

    public User login(String login,String pass){
        Session session=sessionFactory.getCurrentSession();
        Query query=session.createQuery("FROM entity.User where login=:login and password=:pass",User.class)
                .setParameter("login",login)
                .setParameter("pass",pass);
        if(query.list().isEmpty()!=true){
            User user=(User) query.list().get(0);
            return user;
        }else {
            return null;
        }
    }
}
