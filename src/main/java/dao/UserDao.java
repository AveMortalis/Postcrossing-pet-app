package dao;

import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Repository
public class UserDao implements IUserDao {

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

    public User getUserByLogin(String login){
        Session session=sessionFactory.getCurrentSession();
        User user= session.createQuery("FROM entity.User where login=:login",User.class).setParameter("login",login).list().get(0);
        return user;
    }

    public User getRandomUserButNotCurrent(User currentUser){
        Session session=sessionFactory.getCurrentSession();
        //list().get(0);
        int minId = (Integer) session.createQuery("SELECT min(id) FROM entity.User").uniqueResult();
        int maxId = (Integer )session.createQuery("SELECT max(id) FROM entity.User").uniqueResult();
        User randomUser=null;
        Random random = new Random();
        int randomId;
        do {
            randomId= random.nextInt(maxId - minId + 1) + minId;
            randomUser = session.createQuery("FROM entity.User where id=:randomId", User.class).setParameter("randomId", randomId).list().get(0);
        }while (randomUser==null || currentUser.equals(randomUser));

        return randomUser;
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

    public int getCountOfUsers(){
        Session session=sessionFactory.getCurrentSession();
        return ((Number)(session.createQuery("select count(*) from entity.User")).uniqueResult()).intValue();
    }

    public int getCountOfCountries(){
        Session session=sessionFactory.getCurrentSession();
        return ((Number)(session.createQuery("select count(DISTINCT address.country) FROM entity.User")).uniqueResult()).intValue();
    }

    public void searchingForLostUserParcels(User user){
        Session session=sessionFactory.getCurrentSession();
        session.createQuery("update entity.Parcel set status=:status where mailer=:mailer and sendDate<:sendDate and status=:oldStatus")
                .setParameter("status","Lost")
                .setParameter("oldStatus","Sended")
                .setParameter("mailer",user)
                .setParameter("sendDate",Date.valueOf(LocalDate.now().minusDays(50)))
                .executeUpdate();
    }
}
