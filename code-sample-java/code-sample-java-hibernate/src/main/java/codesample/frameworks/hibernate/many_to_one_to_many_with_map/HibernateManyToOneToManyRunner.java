package codesample.frameworks.hibernate.many_to_one_to_many_with_map;

import codesample.frameworks.hibernate.HibernateUtil;
import org.hibernate.Session;

import java.io.IOException;

class HibernateManyToOneToManyRunner {
    public static void main(String[] args) throws IOException {
        Company cocaCola = new Company("Coca cola");
        CompanyWorker ivan = new CompanyWorker("Ivan");
        CompanyContract contract = new CompanyContract("Lucky");

        cocaCola.getContracts().put(ivan, contract);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(cocaCola);
            session.save(ivan);
            session.save(contract);

            System.out.println("Company workers: ");
            session.createQuery("from CompanyWorker").list().forEach(System.out::println);

            System.out.println();
            System.out.println("Company contracts");
            session.createQuery("from CompanyContract").list().forEach(System.out::println);

            System.out.println();
            System.out.println("Company");
            session.createQuery("from Company").list().forEach(System.out::println);

            session.getTransaction().commit();
            System.out.println("Waiting for your input before closing");
            int input = System.in.read();
            System.out.println(input);

        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }
}
