package learn.orm;

import Entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;


public class App {
    public static void main(String[] args) {

        Student student = new Student();
        student.setId(5);
        student.setName("pragya");


        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction tx = null;

        try (Session session = sessionFactory.openSession();) {
            tx = session.beginTransaction();
            session.save(student);
            tx.commit();
            System.out.println("student data saved successfully");
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }


    }
}
