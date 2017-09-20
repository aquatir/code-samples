package learn_to_code.frameworks.hibernate;

import org.hibernate.Session;

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

            session.close();
        }
    }
}
