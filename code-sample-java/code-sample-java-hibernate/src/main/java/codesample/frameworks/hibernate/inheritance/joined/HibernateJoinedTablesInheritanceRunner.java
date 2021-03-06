package codesample.frameworks.hibernate.inheritance.joined;

import codesample.frameworks.hibernate.HibernateUtil;
import org.hibernate.Session;

import java.io.IOException;

class HibernateJoinedTablesInheritanceRunner {
    public static void main(String[] args) throws IOException {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            JoinedGrassWorm ivanWorm = new JoinedGrassWorm("GrassIvan", "Green grass");
            JoinedGrassWorm marinaWorm = new JoinedGrassWorm("GrassMarina", "Red grass");
            JoinedBookWorm katyaWorm = new JoinedBookWorm("Katya", "Hygge");
            JoinedBookWorm joraWorm = new JoinedBookWorm("Jora", "War and Peace");

            session.save(ivanWorm);
            session.save(marinaWorm);
            session.save(katyaWorm);
            session.save(joraWorm);

            System.out.println("Grass worms:");
            session.createQuery("from JoinedGrassWorm").list().forEach(System.out::println);

            System.out.println();
            System.out.println("Book worms:");
            session.createQuery("from JoinedBookWorm").list().forEach(System.out::println);

            System.out.println();
            System.out.println("All worms");
            session.createQuery("from JoinedWorm").list().forEach(System.out::println);

            session.getTransaction().commit();

            System.out.println();
            System.out.println("Waiting for your input to destroy DB");
            System.in.read();

        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }
}
