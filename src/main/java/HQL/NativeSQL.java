package HQL;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;
import util.Student;

import java.util.List;

public class NativeSQL {
    private static SessionFactory sessionFactory;

    static {
        if (sessionFactory == null) sessionFactory = HibernateUtil.getSessionFactory();
    }

    public static void getStudents() {
        Transaction transaction = null;
        List<Student> list = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            String sql = "select * from Student where name like 's%'";
            Query query = session.createNativeQuery(sql, Student.class);
            list = query.getResultList();
            if (list == null) System.out.println("cannot get the data");
            else {
                list.forEach(item -> System.out.println(item.getId() + " " + item.getName()));
            }
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (transaction != null) transaction.rollback();
        }
    }

    public static void saveStudent(int id,String name) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            String sql="insert into Student (id,name) values(:id,:name)";
            Query query=session.createNativeQuery(sql);
            query.setParameter("id",id);
            query.setParameter("name",name);

            int rowsEffect=query.executeUpdate();
            if(rowsEffect>0) System.out.println("effected rows: "+rowsEffect);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (transaction != null) transaction.rollback();
        }
    }

    public static void main(String[] args) {
        saveStudent(75,"sakshi chauhan ");
        getStudents();

    }
}
