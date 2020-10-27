package dao;

import entity.User;
import java.util.List;


public interface IUserDao {

    public List<User> getAll();

    public User getUserById(int id);

    public void deleteUser(User user);

    public void addUser(User user);

    public User getUserByLogin(String login);

    public User getRandomUserButNotCurrent(User currentUser);

    public void updateUser(User user);

    public User login(String login,String pass);

    public int getCountOfUsers();

    public int getCountOfCountries();

    public void searchingForLostUserParcels(User user);

}
