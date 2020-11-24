package dao;

import config.TestConfig;
import entity.QueueingRecipient;
import entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
class QueueingRecipientDaoTest {

    @Autowired
    IQueueingRecipientDao waitersDao;

    @Test
    @Sql({"classpath:data.sql"})
    void getAll() {
        int count=waitersDao.getAll().size();

        Assertions.assertEquals(3,count);
    }

    @Test
    @Sql({"classpath:data.sql"})
    void getFirstRecordFromAwaitList() {
        User currentUser = new User();
        currentUser.setId(6);

        QueueingRecipient waiter = waitersDao.getFirstRecipientFromFromQueueingRecipientsButNotCurrent(currentUser);

        Assertions.assertEquals(21,waiter.getPosition());

    }

    @Test
    @Sql({"classpath:data.sql"})
    void delete() {

        List<QueueingRecipient> waiters = waitersDao.getAll();
        int count = waiters.size();
        QueueingRecipient waiter =waiters.get(0);


        waitersDao.delete(waiter);

        Assertions.assertEquals(count,waitersDao.getAll().size()+1);
        Assertions.assertFalse(waitersDao.getAll().stream().anyMatch(x->x.getPosition()==waiter.getPosition()));
    }

    @Test
    @Sql({"classpath:data.sql"})
    void add() {

        int count = waitersDao.getAll().size();
        QueueingRecipient waiter = new QueueingRecipient();
        waiter.setPosition(100);

        waitersDao.add(waiter);

        Assertions.assertEquals(count,waitersDao.getAll().size()-1);
        Assertions.assertTrue(waitersDao.getAll().stream().anyMatch(x->x.getPosition()==waiter.getPosition()));
    }

    @Test
    @Sql({"classpath:data.sql"})
    void addUserToAwaitList() {

        int count = waitersDao.getAll().size();
        User user = new User();
        user.setId(3);

        waitersDao.addUserToQueueingRecipients(user);

        Assertions.assertEquals(count,waitersDao.getAll().size()-1);
        Assertions.assertTrue(waitersDao.getAll().stream().anyMatch(x->x.getUser().getId()==user.getId()));
    }
}