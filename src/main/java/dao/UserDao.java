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
        User user= session.createQuery("FROM entity.User where login=:login",User.class).setParameter("login",login).uniqueResult();
        return user;
    }

    public User getRandomUserButNotCurrent(User currentUser){

        //TODO find better solution
        Session session=sessionFactory.getCurrentSession();
        int minId = (Integer) session.createQuery("SELECT min(id) FROM entity.User").getSingleResult();
        int maxId = (Integer) session.createQuery("SELECT max(id) FROM entity.User").getSingleResult();
        User randomUser=null;
        Random random = new Random();
        int randomId;
        do {
            randomId= random.nextInt(maxId - minId + 1) + minId;
            randomUser = getUserById(randomId);
        }while (randomUser==null || currentUser.equals(randomUser));

        return randomUser;
    }
    
    public void updateUser(User user){
        Session session=sessionFactory.getCurrentSession();
        session.update(user);
    }

    public int getTotalCountOfUsers(){
        Session session=sessionFactory.getCurrentSession();
        return ((Number)(session.createQuery("select count(*) from entity.User")).uniqueResult()).intValue();
    }

    public int getTotalCountOfCountries(){
        Session session=sessionFactory.getCurrentSession();
        return ((Number)(session.createQuery("select count(DISTINCT address.country) FROM entity.User")).uniqueResult()).intValue();
    }

}
