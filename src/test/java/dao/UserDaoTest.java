package dao;

import config.TestConfig;
import entity.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional
public class UserDaoTest {

    @Autowired
    IUserDao userDao;


    @BeforeEach
    void setUp(){

    }


    @Test
    @Sql({"classpath:data.sql"})
    void getAll() {
        List<User> list=userDao.getAll();
        Assertions.assertEquals(7,list.size());
    }

    @Test
    @Sql({"classpath:data.sql"})
    void getUserByIdWhenSuchUserExists() {
        User user=userDao.getUserById(2);
        Assertions.assertNotNull(user);
    }
    @Test
    @Sql({"classpath:data.sql"})
    void getUserByIdWhenThereIsNoSuchUser() {
        User user=userDao.getUserById(100);
        Assertions.assertNull(user);
    }

    @Test
    @Sql({"classpath:data.sql"})
    void deleteUser() {
        User user=new User();
        user.setId(2);
        userDao.deleteUser(user);
        User userAfterDeletion=userDao.getUserById(2);

    }

    @Test
    @Sql({"classpath:data.sql"})
    void addUser() {
        User user=new User();
        user.setLogin("AddUserTest");
        userDao.addUser(user);
        User userAfterAdding = userDao.getUserByLogin("AddUserTest");
        Assertions.assertNotNull(userAfterAdding);
    }

    @Test
    @Sql({"classpath:data.sql"})
    void getUserByLogin() {
        User user = userDao.getUserByLogin("test");
        Assertions.assertEquals("test",user.getLogin());
    }

    @Test
    @Sql({"classpath:data.sql"})
    void getRandomUserButNotCurrent() {
        User currentUser = userDao.getUserByLogin("test");
        User randomUser = userDao.getRandomUserButNotCurrent(currentUser);
        Assertions.assertNotNull(currentUser);
        Assertions.assertNotNull(randomUser);
        Assertions.assertNotEquals(currentUser,randomUser);
    }

    @Test
    @Sql({"classpath:data.sql"})
    void updateUser() {
        User currentUser = userDao.getUserByLogin("test");
        currentUser.setLogin("UpdatedLogin");
        userDao.updateUser(currentUser);
        User updatedUser = userDao.getUserById(currentUser.getId());
        Assertions.assertEquals("UpdatedLogin",updatedUser.getLogin());
    }

    @Test
    @Sql({"classpath:data.sql"})
    void getCountOfUsers() {
        Assertions.assertEquals(7,userDao.getTotalCountOfUsers());
    }

    @Test
    @Sql({"classpath:data.sql"})
    void getCountOfCountries() {
        Assertions.assertEquals(5,userDao.getTotalCountOfCountries());
    }

    @Test
    void searchingForLostUserParcels() {

    }


}
