package codesample.frameworks.hibernate.one_to_many;

import codesample.frameworks.hibernate.HibernateUtil;
import org.hibernate.Session;

public class HibernateOneToManyManyToOneExample {
    public static void main(String[] args) {

        Production eifelTower = new Production("Eifel tower");
        Worker ivan = new Worker("Ivan", eifelTower);
        Worker ashot = new Worker("Ahot", eifelTower);
        eifelTower.addWorker(ivan);
        eifelTower.addWorker(ashot);

        Production schlossLindstedt = new Production("Schloss Lindstedt");
        Worker vasiliy = new Worker("Vasiliy", schlossLindstedt);
        schlossLindstedt.addWorker(vasiliy);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            session.save(eifelTower);
            session.save(ivan);
            session.save(ashot);
            session.save(schlossLindstedt);
            session.save(vasiliy);


            session.createQuery("from Production").list().forEach(System.out::println);
            System.out.println();
            session.createQuery("from Worker").list().forEach(System.out::println);
            session.getTransaction().commit();

        }

        HibernateUtil.getSessionFactory().close();
    }
}
