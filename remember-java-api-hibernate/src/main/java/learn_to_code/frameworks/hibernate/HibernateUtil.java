package learn_to_code.frameworks.hibernate;


import learn_to_code.frameworks.hibernate.crud_operations_example.Town;
import learn_to_code.frameworks.hibernate.embedded_and_enum_and_super_class.Hero;
import learn_to_code.frameworks.hibernate.many_to_many.biridectional.Concert;
import learn_to_code.frameworks.hibernate.many_to_many.biridectional.Visitor;
import learn_to_code.frameworks.hibernate.many_to_many.unidirectional.Option;
import learn_to_code.frameworks.hibernate.many_to_many.unidirectional.OurUser;
import learn_to_code.frameworks.hibernate.many_to_one.Item;
import learn_to_code.frameworks.hibernate.many_to_one.Shipping;
import learn_to_code.frameworks.hibernate.many_to_one_to_many_with_map.Company;
import learn_to_code.frameworks.hibernate.many_to_one_to_many_with_map.CompanyContract;
import learn_to_code.frameworks.hibernate.many_to_one_to_many_with_map.CompanyWorker;
import learn_to_code.frameworks.hibernate.one_to_many.Production;
import learn_to_code.frameworks.hibernate.one_to_many.Worker;
import learn_to_code.frameworks.hibernate.one_to_one_relation.bidirectional.DogBidirectional;
import learn_to_code.frameworks.hibernate.one_to_one_relation.bidirectional.OwnerBidirectional;
import learn_to_code.frameworks.hibernate.one_to_one_relation.unidirectional.DogUni;
import learn_to_code.frameworks.hibernate.one_to_one_relation.unidirectional.OwnerUni;
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
                    .addAnnotatedClass(Production.class)
                    .addAnnotatedClass(Worker.class)
                    .addAnnotatedClass(Hero.class)
                    .addAnnotatedClass(DogUni.class)
                    .addAnnotatedClass(OwnerUni.class)
                    .addAnnotatedClass(DogBidirectional.class)
                    .addAnnotatedClass(OwnerBidirectional.class)
                    .addAnnotatedClass(Concert.class)
                    .addAnnotatedClass(Visitor.class)
                    .addAnnotatedClass(Company.class)
                    .addAnnotatedClass(CompanyContract.class)
                    .addAnnotatedClass(CompanyWorker.class)
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
