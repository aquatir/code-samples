package learn_to_code.frameworks.hibernate.foreign_key_example;

import learn_to_code.frameworks.hibernate.HibernateUtil;
import org.hibernate.Session;

import java.time.LocalDate;

/**
 * Example of using two tables linked by foreign key. Foreign key usage is shown in {@link Shipping}.
 * Here we will store some data in main table and then use the same objects while storing data
 * in a table with foreign key.
 *
 * Note that we create Item objects and Shipping objects in two transactions. This is not necessary, and actually
 * may be troublesome for following reasons:
 *
 * When session is being closed, all associated entity objects are being detached. This mean that any changes to this
 * objects will no be propagated to database. This will allow us to create the following error:
 * 1) Create some entity object (Item in this case)
 * 2) Open session, store them, close session
 * 3) CHANGE some fields of entity objects (Not id/natural id as you can not change them anyway)
 * 4) Create some other entity objects using the ones created in first place (Shipping in this case)
 * 5) Open session, store them, close session
 *
 * This scenario will work, because the link between two entities is done by id field. So changing any fields
 * in detached objects will not lead to this objects being changed, until you update their references by call to
 * session.saveOrUpdate(SomeEntity entity).
 */
public class HibernateRunnerForeignKey {
    public static void main(String[] args) {

        Item onion = new Item("Onion");
        Item screwdriver = new Item("Screwdriver");
        Item chair = new Item("Chair");
        Item headphone = new Item("Headphone");

        Shipping onionsShipping1 = new Shipping(LocalDate.now(), onion, 5);
        Shipping onionsShipping2 = new Shipping(LocalDate.of(2017, 9, 29), onion, 32);
        Shipping screwdriverShipping = new Shipping(LocalDate.of(2017, 3, 30), screwdriver, 1);
        Shipping chairShipping = new Shipping(LocalDate.of(2017,1,1), chair, 99);
        Shipping headphoneShipping = new Shipping(LocalDate.of(2017,3,20), headphone, 2);

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            session.save(onion);
            session.save(screwdriver);
            session.save(chair);
            session.save(headphone);
            session.getTransaction().commit();

            System.out.println("Items in item table:");
            session.createQuery("from Item").list().forEach(System.out::println);
            System.out.println();
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            session.save(onionsShipping1);
            session.save(onionsShipping2);
            session.save(screwdriverShipping);
            session.save(chairShipping);
            session.save(headphoneShipping);
            session.getTransaction().commit();

            System.out.println("Shipping in shipping table:");
            session.createQuery("from Shipping").list().forEach(System.out::println);
            System.out.println();
        }

        HibernateUtil.getSessionFactory().close();
    }
}
