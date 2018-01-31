import config.PersistenceConfig;
import dao.AwaitListDao;
import dao.UserDao;
import entity.Address;
import entity.AwaitList;
import entity.Parcel;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import service.HibernateUtil;


public class main {

    @Autowired
    private SessionFactory sessionFactory;

    public static void main(String[] args) {
//        Session session= HibernateUtil.getSessionFactory().openSession();
//        Transaction tx=session.beginTransaction();

        ApplicationContext context =
                new AnnotationConfigApplicationContext(PersistenceConfig.class);
        SessionFactory sessionFactory= (SessionFactory) context.getBean("sessionFactory");
        Session session=sessionFactory.openSession();
        UserDao userDao=new UserDao(session);
        User mailer =userDao.getUserById(2);
        System.out.println("MAILER: "+mailer);


//        AwaitListDao awaitListDao=new AwaitListDao(session);
//        AwaitList awaitList=awaitListDao.getFirst(mailer);
//        User recipient=new User();
//        if(awaitList!=null) {
//            recipient = userDao.getUserById(awaitList.getUser_id());
//            System.out.println("RECIPIENT:"+recipient);
//            awaitListDao.delete(awaitList);
//            Parcel parcel = new Parcel();
//            parcel.setMailer(mailer);
//            parcel.setRecipient(recipient);
//            String regcode = (int) (Math.random() * 1000000) + mailer.getAddress().getCountryName();
//
//            parcel.setRegistrationCode(regcode);
//            parcel.setStatus("Sended");
//            session.save(parcel);
//            tx.commit();
//            session.close();
//        }

    }
}
