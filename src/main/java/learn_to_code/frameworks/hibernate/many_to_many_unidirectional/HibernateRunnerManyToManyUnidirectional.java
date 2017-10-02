package learn_to_code.frameworks.hibernate.many_to_many_unidirectional;

import learn_to_code.frameworks.hibernate.HibernateUtil;
import org.hibernate.Session;

/**
 * Example of using two table linked by foreign key. Foreign key usage is shown in {@link learn_to_code.frameworks.hibernate.foreign_key_example.Shipping}.
 * Here we will store some data in main table and then use the same objects while storing data
 * in a table with foreign key.
 * <p/>
 * Note that we create Item objects and Shipping objects in two transactions. This is not necessary, and actually
 * may be troublesome for following reasons:
 * <p/>
 * When session is being closed, all associated entity objects are being detached. This mean that any changes to this
 * objects will no be propagated to database. This will allow us to create the following error:
 * 1) Create some entity object (Item in this case)
 * 2) Open session, store them, close session
 * 3) CHANGE some fields of entity objects (Not id/natural id as you can not change them anyway)
 * 4) Create some other entity objects using the ones created in first place (Shipping in this case)
 * 5) Open session, store them, close session
 * <p/>
 * This scenario will work, because the link between two entities is done by id field. So changing any fields
 * in detached objects will not lead to this objects being changed, until you update their references by call to
 * session.saveOrUpdate(SomeEntity entity).
 */
public class HibernateRunnerManyToManyUnidirectional {
    public static void main(String[] args) {

        try {
            Option opt1 = new Option("Window mode");
            Option opt2 = new Option("x32 compatable");
            Option opt3 = new Option("Extra chees");
            Option opt4 = new Option("Extra beacon");

            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                session.beginTransaction();

                session.save(opt1);
                session.save(opt2);
                session.save(opt3);
                session.save(opt4);

                session.getTransaction().commit();

                session.createQuery("from Option").list().forEach(System.out::println);
                System.out.println();
            }

            OurUser ivan = new OurUser("Vegan");
            ivan.addOption(opt1);
            ivan.addOption(opt2);
            OurUser vasily = new OurUser("Vasily");
            vasily.addOption(opt1);
            vasily.addOption(opt2);
            vasily.addOption(opt4);
            OurUser masha = new OurUser("Masha");
            masha.addOption(opt2);


            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                session.beginTransaction();

                session.save(ivan);
                session.save(vasily);
                session.save(masha);

                session.getTransaction().commit();

                session.createQuery("from OurUser").list().forEach(System.out::println);
                System.out.println();
            }

        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }
}
