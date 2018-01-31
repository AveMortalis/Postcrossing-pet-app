package service;

import entity.Address;
import entity.AwaitList;
import entity.Parcel;
import entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    static {
        try {
            StandardServiceRegistry standardRegistry =
                    new StandardServiceRegistryBuilder().configure().build();
            Metadata metaData =
                    new MetadataSources(standardRegistry)
                            .addAnnotatedClass( Address.class )
                            .addAnnotatedClass( User.class )
                            .addAnnotatedClass(AwaitList.class)
                            .addAnnotatedClass(Parcel.class)
                            .getMetadataBuilder().build();
            sessionFactory = metaData.getSessionFactoryBuilder().build();
    }catch (Throwable th) {

            System.err.println("Enitial SessionFactory creation failed" + th);
            throw new ExceptionInInitializerError(th);

        }
    }

    public static Session getSession()throws HibernateException {
        return sessionFactory.openSession();
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}