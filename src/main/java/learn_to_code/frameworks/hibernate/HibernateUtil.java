package learn_to_code.frameworks.hibernate;

import learn_to_code.frameworks.hibernate.foreign_key_example.Item;
import learn_to_code.frameworks.hibernate.foreign_key_example.Shipping;
import learn_to_code.frameworks.hibernate.many_to_many_unidirectional.Option;
import learn_to_code.frameworks.hibernate.many_to_many_unidirectional.OurUser;
import learn_to_code.frameworks.hibernate.single_table_example.Town;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * It is recommended to create hibernate SessionFactory once for each of your databases so we use a singleton here.
 *
 * You can also replace call to {@link HibernateUtil#getSessionFactory()} with something line getSession().
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration()
                    .addPackage("learn_to_code.frameworks.hibernate")
                    .addAnnotatedClass(Town.class)
                    .addAnnotatedClass(Item.class)
                    .addAnnotatedClass(Shipping.class)
                    .addAnnotatedClass(Option.class)
                    .addAnnotatedClass(OurUser.class)
                    .configure()
                    .buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

}
