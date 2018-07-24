package codesample.frameworks.hibernate.element_collection;

import codesample.frameworks.hibernate.HibernateUtil;
import org.hibernate.Session;

import java.io.IOException;

public class HibernateElementCollectionRunner {
    public static void main(String[] args) throws IOException {

        Pizza peperoniPizza = new Pizza("Peperoni");
        Pizza italianPizza = new Pizza("Italian");

        Ingredient peperoni = new Ingredient("peperoni");
        Ingredient tomato = new Ingredient("tomato");


        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            peperoniPizza.getIngredients().add(peperoni);
            italianPizza.getIngredients().add(peperoni);
            italianPizza.getIngredients().add(tomato);

            session.save(peperoniPizza);
            session.save(italianPizza);

            System.out.println("Available pizza:");
            session.createQuery("from Pizza").list().forEach(c -> System.out.println(c));

            System.out.println("Deleting peperoni pizza");
            session.delete(peperoniPizza);

            System.out.println("Available pizza");
            session.createQuery("from Pizza").list().forEach(c -> System.out.println(c));

            session.getTransaction().commit();

            System.out.println("Waiting for input to close");
            System.in.read();
        } finally {
            HibernateUtil.getSessionFactory().close();
        }
    }

}
