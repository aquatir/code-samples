package learn_to_code.frameworks.hibernate;

import org.hibernate.Session;

public class HibernateRunner {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(new Towns("Chertanovo", 50));
        //session.createQuery("From towns").list().forEach(c -> System.out.println(c));
        session.getTransaction().commit();
    }
}
