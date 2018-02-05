package service;

import dao.UserDao;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public User logIn(User userFromFrom){
        User user=userDao.login(userFromFrom.getLogin(),userFromFrom.getPassword());
        return user;
    }

    @Transactional
    public void updateUserDetails(User user){
        userDao.updateUser(user);
    }

    @Transactional
    public void saveUser(User user){
        userDao.addUser(user);
    }
}
