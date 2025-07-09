package learn.orm;

import Entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

    Student aniket=new Student();
    aniket.setId(1);
    aniket.setName("aniket");


        Configuration con=new Configuration().configure().addAnnotatedClass(Student.class);
        SessionFactory sf=con.buildSessionFactory();
        Session session =sf.openSession();

        Transaction tx=session.beginTransaction();
        session.save(aniket);
        tx.commit();
    }
}
