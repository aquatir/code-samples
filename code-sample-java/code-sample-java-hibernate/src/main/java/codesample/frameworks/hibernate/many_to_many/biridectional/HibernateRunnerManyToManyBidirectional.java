package codesample.frameworks.hibernate.many_to_many.biridectional;

import codesample.frameworks.hibernate.HibernateUtil;
import org.hibernate.Session;

import java.io.IOException;

/**
 * Here we have an example of many_to_many relationship on both sides. Each
 * {@link Visitor} can visit multiple {@link Concert}'s and each Concert can have multiple visitors.
 *
 * Usually this many_to_many bidirectional mapping consist of thing which can live without each other, thus
 * no CascadeType.ALL option is rare.
 */
class HibernateRunnerManyToManyBidirectional {
    public static void main(String[] args) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            Visitor ivan = new Visitor("Ivan");
            Visitor marina = new Visitor("Marina");
            Visitor vyacheslav = new Visitor("Vyacheslav");

            Concert linkinPark = new Concert("Linking park");
            Concert threeDaysGrace = new Concert("Three days grace");

            ivan.addConcert(linkinPark);
            marina.addConcert(linkinPark);
            marina.addConcert(threeDaysGrace);
            vyacheslav.addConcert(threeDaysGrace);

            session.save(ivan);
            session.save(marina);
            session.save(vyacheslav);
            session.save(linkinPark);
            session.save(threeDaysGrace);

            System.out.println("Visitors:");
            session.createQuery("from Visitor").list().forEach(System.out::println);
            System.out.println("\n\nConcerts:");
            session.createQuery("from Concert").list().forEach(System.out::println);

            System.out.println("\nDelete marina");

            /* Note that we refresh both concerts after removing marina. This is because the changes to database from this
            * delete will not be propagated to your persistent classes before you refresh them.
            *
            * Try removing refresh lines and see what happens. */
            session.remove(marina);
            session.refresh(linkinPark);
            session.refresh(threeDaysGrace);
            System.out.println("Visitors:");
            session.createQuery("from Visitor").list().forEach(System.out::println);
            System.out.println("\n\nConcerts:");
            session.createQuery("from Concert").list().forEach(System.out::println);

            session.getTransaction().commit();

            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }
}
