package learn_to_code.frameworks.hibernate.one_table_example;

import learn_to_code.frameworks.hibernate.HibernateUtil;
import org.hibernate.Session;

/**
 * Example of populating a table with data.
 *
 * Note that session object implements auto-closable, so using try-with-resources is encouraged.
 */
public class HibernateRunner {
    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            session.save(new Town("Chertanovo", 50));
            session.save(new Town("Moscow", 5));
            session.save(new Town("Voronej", 100));
            session.save(new Town("Paris", 500));

            session.createQuery("from Town").list().forEach(c -> System.out.println(c));
            session.getTransaction().commit();
        }
    }
}
