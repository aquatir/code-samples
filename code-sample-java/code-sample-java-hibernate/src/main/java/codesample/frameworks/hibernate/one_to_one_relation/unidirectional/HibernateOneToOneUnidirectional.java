package codesample.frameworks.hibernate.one_to_one_relation.unidirectional;

import codesample.frameworks.hibernate.HibernateUtil;
import org.hibernate.Session;

import java.io.IOException;

public class HibernateOneToOneUnidirectional {
    public static void main(String[] args) throws IOException {
        DogUni korgi = new DogUni("Korgi");
        DogUni pug = new DogUni("Pug");

        OwnerUni ivan = new OwnerUni("Ivan", korgi);
        OwnerUni vladimir = new OwnerUni("Vladimir", pug);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            session.save(ivan);
            session.save(vladimir);

            System.out.println("Owners:");
            session.createQuery("From OwnerUni").list().forEach(owner -> System.out.println(owner));
            System.out.println("Dogs:");
            session.createQuery("From DogUni").list().forEach(dog -> System.out.println(dog));

            System.out.println();
            System.out.println("Deleting Ivan");
            session.delete(ivan);

            System.out.println("Owners:");
            session.createQuery("From OwnerUni").list().forEach(owner -> System.out.println(owner));
            System.out.println("Dogs:");
            session.createQuery("From DogUni").list().forEach(dog -> System.out.println(dog));
            System.out.println("Note that Ivan's dog (korgi) was also deleted");

            session.getTransaction().commit();
        }

        System.out.println("You can check DB state for now. Press any key to finish example and clear DB");
        System.in.read();
        HibernateUtil.getSessionFactory().close();
    }
}
