package codesample.frameworks.hibernate.inheritance.table_per_class;

import codesample.frameworks.hibernate.HibernateUtil;
import org.hibernate.Session;

import java.io.IOException;

public class HibernateTablePerClassRunner {
    public static void main(String[] args) throws IOException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            TablePerClassGrassWorm ivanWorm = new TablePerClassGrassWorm("GrassIvan", "Green grass");
            TablePerClassGrassWorm marinaWorm = new TablePerClassGrassWorm("GrassMarina", "Red grass");
            TablePerClassBookWorm katyaWorm = new TablePerClassBookWorm("Katya", "Hygge");
            TablePerClassBookWorm joraWorm = new TablePerClassBookWorm("Jora", "War and Peace");

            session.save(ivanWorm);
            session.save(marinaWorm);
            session.save(katyaWorm);
            session.save(joraWorm);
            session.getTransaction().commit();

            session.beginTransaction();
            System.out.println("Grass worms:");
            session.createQuery("from TablePerClassGrassWorm").list().forEach(c -> System.out.println(c));

            System.out.println();
            System.out.println("Book worms:");
            session.createQuery("from TablePerClassBookWorm").list().forEach(c -> System.out.println(c));

            System.out.println();
            System.out.println("All worms");
            session.createQuery("from TablePerClassWorm").list().forEach(c -> System.out.println(c));

            session.getTransaction().commit();

            System.out.println();
            System.out.println("Waiting for your input to destroy DB");
            System.in.read();

        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }
}
