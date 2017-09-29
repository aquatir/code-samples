package learn_to_code.frameworks.hibernate.single_table_example;

import learn_to_code.frameworks.hibernate.HibernateUtil;
import org.hibernate.CacheMode;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;

import java.util.UUID;

/**
 * Example of populating a table with data.
 * In this example we will use different hibernate techniques to store and query data
 * We will use a single table defined in {@link Town} class
 *
 * Note that session object implements auto-closable, so using try-with-resources is encouraged.
 */
public class HibernateRunnerSingleTable {
    public static void main(String[] args) {

        Town chertanovo = new Town("Chertanovo", 50, "Big awesome river");
        Town moscow = new Town ("Moscow", 5, "River of love");
        Town voronej = new Town("Voronej", 100, "Nil");

        System.out.println("Save 3 cities");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            session.save(chertanovo);
            session.save(moscow);
            session.save(voronej);
            session.getTransaction().commit();

            printTowns(session);
        }

        System.out.println();
        System.out.println("Deleting Moscow in another session");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.remove(moscow);
            session.getTransaction().commit();

            printTowns(session);
        }

        System.out.println();
        System.out.println("Get town from DB by normal id");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Town chertanovo2 = session.byId(Town.class).load(chertanovo.getId());
            System.out.println("Got Chertanovo by normal id");
            System.out.println(chertanovo2.toString());
            session.getTransaction().commit();

            printTowns(session);
        }

        /* You can get data by simple natural ID. Simple natural id (session.bySimpleNaturalId())  should be enough to determine a single row in database.
         * However, sometimes you need more then one key to determine a single row. In this case you should use multiple natural ids
          * and query all of them at once like in example */
        System.out.println();
        System.out.println("Get town from DB by natural id");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Town chertanovo2 = session.byNaturalId(Town.class)
                    .using("biggestRiver", "Big awesome river")
                    .using("townName", "Chertanovo")
                    .getReference();

            session.getTransaction().commit();
            System.out.println("Got Chertanovo by 2 naturals Ids");
            System.out.println(chertanovo2.toString());

            printTowns(session);
        }

        System.out.println();
        System.out.println("Now lets take chertanovo object, which was attached but now is detached from session, " +
                "change it and attach it again to persistent state");
        chertanovo.setDistance(666);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(chertanovo);
            session.getTransaction().commit();
            printTowns(session);
        }

        /* Note that session object cache all object created until you commit them. Because of
        * that you have to:
        * 1) set hibernate.jdbc.batch_size  property (see hibernate.cfg.xml)
        * 2) flush session periodically*/
        System.out.println();
        System.out.println("Batch inserts example");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            for (int i = 0; i < 10_000; i++) {
                Town town = new Town(UUID.randomUUID().toString(), i, UUID.randomUUID().toString());
                session.save(town);
                if (i % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            System.out.println("All towns inserted");
            session.getTransaction().commit();
        }

        /* If you have a lot of data to update, it never hurts to use database cursors, like in this example */
        System.out.println();
        System.out.println("Batch update example");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            ScrollableResults towns = session.createQuery("select town from Town town")
                    .setCacheMode(CacheMode.IGNORE)
                    .scroll(ScrollMode.FORWARD_ONLY);

            int i = 0;
            while (towns.next()) {
                Town town = (Town) towns.get(0);
                town.setDistance(i+ 10);
                i++;
                if (i % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            System.out.println("All towns updated");
            session.getTransaction().commit();
        }

        HibernateUtil.getSessionFactory().close();
    }





    private static void printTowns(Session session) {
        System.out.println("Printing list of towns:");
        session.createQuery("select town from Town town").list().forEach(System.out::println);
    }
}
