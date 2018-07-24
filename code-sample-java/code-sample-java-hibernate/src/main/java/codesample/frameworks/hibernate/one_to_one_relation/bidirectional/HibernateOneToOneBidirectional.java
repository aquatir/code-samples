package codesample.frameworks.hibernate.one_to_one_relation.bidirectional;

import codesample.frameworks.hibernate.HibernateUtil;
import org.hibernate.Session;

public class HibernateOneToOneBidirectional {
    public static void main(String[] args) {
        DogBidirectional korgi = new DogBidirectional("korgi");
        DogBidirectional pug = new DogBidirectional("pug");
        DogBidirectional husky = new DogBidirectional("husky");

        OwnerBidirectional marina = new OwnerBidirectional("Marina", korgi);
        OwnerBidirectional katya = new OwnerBidirectional("Katya", pug);
        OwnerBidirectional julia = new OwnerBidirectional("Julia", husky);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            session.save(marina);
            session.save(katya);
            session.save(julia);

            System.out.println("Owners:");
            session.createQuery("From OwnerBidirectional").list().forEach(owner -> System.out.println(owner));
            System.out.println("Dogs");
            session.createQuery("From DogBidirectional").list().forEach(owner -> System.out.println(owner));

            System.out.println();
            System.out.println("Now we delete 1 dog (korgi) and 1 owner (katya, who own a pug). After this there " +
                    "will be only one owner and 1 dog left, because both tables have cascade deleted enabled");
            session.delete(korgi);
            session.delete(katya);

            System.out.println("Owners:");
            session.createQuery("From OwnerBidirectional").list().forEach(owner -> System.out.println(owner));
            System.out.println("Dogs");
            session.createQuery("From DogBidirectional").list().forEach(owner -> System.out.println(owner));

            session.getTransaction().commit();
        }

        HibernateUtil.getSessionFactory().close();
    }
}
