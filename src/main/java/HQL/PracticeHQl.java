package HQL;


import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;
import util.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PracticeHQl {
    private static SessionFactory sessionFactory = null;
    private static Scanner scanner = null;

    static {
        if (scanner == null) scanner = new Scanner(System.in);
        if (sessionFactory == null) sessionFactory = HibernateUtil.getSessionFactory();
        System.out.println("connection established with db");
    }

    //select simple practice
    public static List<Student> getStudents() {
        String query = "from Student where name like 'a%' or name =:studentName ";
//      String query = "select count(*) from Student where name like 'a%' or name =:studentName ";

        Transaction transaction = null;
        List<Student> list = null;
        try (Session session = sessionFactory.openSession();) {
            transaction = session.beginTransaction();
            Query q = session.createQuery(query, Student.class);

//            for count
//          Query q = session.createQuery(query, Long.class);
            q.setParameter("studentName", "Tanya");
            list = q.getResultList();
//          System.out.println(q.getSingleResult());
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (transaction != null) transaction.rollback();
        }
        return list;
    }

    //pagination
    public static List<Student> getStudentPaginated(int startIndex, int maxRows) {
        List<Student> list = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            String query = "from Student";
            Query q = session.createQuery(query, Student.class);
            q.setFirstResult(startIndex);
            q.setMaxResults(maxRows);
            list = q.getResultList();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (transaction != null) transaction.rollback();
        }
        return list;
    }

    //update
    public static void updateStudent()
    {
        System.out.println("enter id to update student");
        int id=scanner.nextInt();
        scanner.nextLine();
        System.out.println("enter the new name");
        String newName=scanner.nextLine();
        Transaction transaction=null;
        try(Session session= sessionFactory.openSession())
        {
            transaction=session.beginTransaction();

            String hql="update Student set name = :newName where id = :id";
            Query query=session.createQuery(hql);
            query.setParameter("newName",newName);
            query.setParameter("id",id);
            int rowEffect=query.executeUpdate();
            System.out.println("rows effected: "+rowEffect);
            transaction.commit();
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            if(transaction!=null)transaction.rollback();
        }
    }
    //delete
    public static void deleteStudent()
    {
        System.out.println("enter id to delete student");
        int id=scanner.nextInt();

        Transaction transaction=null;
        try(Session session= sessionFactory.openSession())
        {
            transaction=session.beginTransaction();

            String hql="delete Student where id = :id";
            Query query=session.createQuery(hql);
            query.setParameter("id",id);
            int rowEffect=query.executeUpdate();
            System.out.println("rows effected: "+rowEffect);
            transaction.commit();
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            if(transaction!=null)transaction.rollback();
        }
    }


    public static void main(String[] args) {

//        get all students as list.
//        List<Student> list = getStudents();
//        if (list == null) System.out.println("no entry for student table");
//        else {
//            list.forEach(item -> {
//                System.out.println(item.getId() + " " + item.getName());
//            });
//        }

//      get Students list but paginated.....
//        List<Student> list1 = getStudentPaginated(10, 20);
//        if (list1 == null) System.out.println("no entry for student table");
//        else {
//            list1.forEach(item -> {
//                System.out.println(item.getId() + " " + item.getName());
//            });
//        }

//        updateStudent();
            deleteStudent();

    }
}
