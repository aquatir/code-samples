package learn_to_code.frameworks.hibernate.embedded_and_enum_and_super_class;

import learn_to_code.frameworks.hibernate.HibernateUtil;
import org.hibernate.Session;

public class HibernateEmbeddedEnumSuperRunner {
    public static void main(String[] args) {
        Hero superMan = new Hero("Super man", new Power("Laser eyes", PowerLevel.MASTER));
        Hero programmerMan = new Hero("Programmer man", new Power("Coffee addiction", PowerLevel.MASTER));
        Hero fireFighter = new Hero("Firefighter Bob", new Power("Fire... fighting!", PowerLevel.ADEPT));

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(superMan);
            session.save(programmerMan);
            session.save(fireFighter);

            session.createQuery("From Hero").list().forEach(hero -> System.out.println(hero));

            session.getTransaction().commit();
        }

        HibernateUtil.getSessionFactory().close();
    }
}
