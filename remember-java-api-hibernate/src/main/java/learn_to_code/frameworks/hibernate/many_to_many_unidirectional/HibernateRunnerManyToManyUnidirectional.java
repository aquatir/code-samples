package learn_to_code.frameworks.hibernate.many_to_many_unidirectional;

import learn_to_code.frameworks.hibernate.HibernateUtil;
import org.hibernate.Session;


/**
 * Example of many-to-many relationship. We have 2 tables - OurUser (Because simple User is not an appropriate name for
 * postgres) and Option. Each user can have one or more special options turned on in his profile and we have to store
 * this setting somewhere. In this example we will use an auxiliary table to store options which are turned on.
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


                session.createQuery("from Option").list().forEach(System.out::println);
                session.getTransaction().commit();
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


                session.createQuery("from OurUser").list().forEach(System.out::println);
                System.out.println();

                System.out.println("Let's get Ivan's options");
                OurUser storedIvan = session.byId(OurUser.class).load(ivan.getId());
                System.out.println(storedIvan.toString());

                System.out.println();
                session.getTransaction().commit();
            }

        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }
}
