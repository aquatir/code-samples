package learn_to_code.frameworks.hibernate.inheritance.joined;

import learn_to_code.frameworks.hibernate.HibernateUtil;
import org.hibernate.Session;

import java.io.IOException;


public class HibernateJoinedTablesInheritanceRunner {
    public static void main(String[] args) throws IOException {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            JoinedWorm ivanWorm = new JoinedGrassWorm("GrassIvan", "Green grass");
            JoinedWorm marinaWorm = new JoinedGrassWorm("GrassMarina", "Red grass");
            JoinedWorm katyaWorm = new JoinedBookWorm("Katya", "Hygge");
            JoinedWorm joraWorm = new JoinedBookWorm("Jora", "War and Peace");

            session.save(ivanWorm);
            session.save(marinaWorm);
            session.save(katyaWorm);
            session.save(joraWorm);

            System.out.println("Grass worms:");
            session.createQuery("from JoinedGrassWorm").list().forEach(c -> System.out.println(c));

            System.out.println();
            System.out.println("Book worms:");
            session.createQuery("from JoinedBookWorm").list().forEach(c -> System.out.println(c));

            System.out.println();
            System.out.println("All worms");
            session.createQuery("from JoinedWorm").list().forEach(c -> System.out.println(c));

            session.getTransaction().commit();

            System.out.println();
            System.out.println("Waiting for your input to destroy DB");
            System.in.read();

        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }
}
