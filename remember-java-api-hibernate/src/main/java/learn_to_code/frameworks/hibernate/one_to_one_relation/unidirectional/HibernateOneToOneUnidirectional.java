package learn_to_code.frameworks.hibernate.one_to_one_relation.unidirectional;

import learn_to_code.frameworks.hibernate.HibernateUtil;
import org.hibernate.Session;

public class HibernateOneToOneUnidirectional {
    public static void main(String[] args) {
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

        HibernateUtil.getSessionFactory().close();
    }
}
