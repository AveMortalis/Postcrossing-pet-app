package dao;

import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import service.HibernateUtil;

import java.util.List;

public class UserDao {

    private Session session;
    public UserDao(Session session) {
        this.session = session;
    }

    public List<User> getAll(){
        Query query=session.createQuery("FROM entity.User");
        return query.list();
    }

    public User getUserById(int id){
        return session.get(User.class,id);
    }

    public void deleteUser(User user){
        session.delete(user);
    }

    public void addUser(User user){
        session.save(user);
    }
    
    public void updateUser(User user){
        User userForUpdate= session.get(User.class,user.getId());
        userForUpdate.setName(user.getName());
        userForUpdate.setSurname(user.getSurname());
        userForUpdate.setEmail(user.getEmail());
        session.save(userForUpdate);
    }

    public User login(String login,String pass){
        Query query=session.createQuery("FROM entity.User where login=:login and password=:pass",User.class).setParameter("login",login).setParameter("pass",pass);
        if(query.list().isEmpty()!=true){
            User user=(User) query.list().get(0);
            return user;
        }else {
            return null;
        }
    }
}
