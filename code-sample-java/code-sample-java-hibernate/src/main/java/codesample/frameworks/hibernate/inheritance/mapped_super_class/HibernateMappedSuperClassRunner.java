package codesample.frameworks.hibernate.inheritance.mapped_super_class;

import codesample.frameworks.hibernate.HibernateUtil;
import org.hibernate.Session;

import java.io.IOException;

public class HibernateMappedSuperClassRunner {
    public static void main(String[] args) throws IOException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            MappedSuperGrassWorm ivanWorm = new MappedSuperGrassWorm("GrassIvan", "Green grass");
            MappedSuperGrassWorm marinaWorm = new MappedSuperGrassWorm("GrassMarina", "Red grass");
            MappedSuperBookWorm katyaWorm = new MappedSuperBookWorm("Katya", "Hygge");
            MappedSuperBookWorm joraWorm = new MappedSuperBookWorm("Jora", "War and Peace");

            session.save(ivanWorm);
            session.save(marinaWorm);
            session.save(katyaWorm);
            session.save(joraWorm);
            session.getTransaction().commit();

            session.beginTransaction();
            System.out.println("Grass worms:");
            session.createQuery("from MappedSuperGrassWorm").list().forEach(c -> System.out.println(c));

            System.out.println();
            System.out.println("Book worms:");
            session.createQuery("from MappedSuperBookWorm").list().forEach(c -> System.out.println(c));

            session.getTransaction().commit();

            System.out.println();
            System.out.println("Waiting for your input to destroy DB");
            System.in.read();

        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }
}
