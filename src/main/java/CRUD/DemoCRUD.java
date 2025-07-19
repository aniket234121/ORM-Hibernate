package CRUD;

import util.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.Scanner;

public class DemoCRUD {
    private static Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    public static void createStudent() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Student student = new Student();
        System.out.println("enter the student id:- ");
        student.setId(scanner.nextInt());
        scanner.nextLine();
        System.out.println("enter the student name");
        student.setName(scanner.nextLine());
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            session.persist(student);
            transaction.commit();
            System.out.println("student " + student.getName() + " is persisted");

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
    }

    public static void readStudent() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        System.out.println("enter the student id to get details");
        int id = scanner.nextInt();
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession();) {
            transaction = session.beginTransaction();
            Student st = session.get(Student.class, id);
            transaction.commit();
            ;
            if (st == null) {
                System.out.println("no student exist");
            } else System.out.println(st);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void updateStudent() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        System.out.println("enter the student id to get details");
        int id = scanner.nextInt();
        scanner.nextLine();
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession();) {
            transaction = session.beginTransaction();
            Student st = session.get(Student.class, id);

            if (st == null) {
                System.out.println("no student exist");
            } else {
                System.out.println("enter name to update");
                st.setName(scanner.nextLine());

            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }

    }

    public static void deleteStudent() {
        System.out.println("enter the student id to delete");
        int id = scanner.nextInt();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession();) {
            transaction=session.beginTransaction();
            Student s = session.get(Student.class, id);
            if(s!=null) session.delete(s);
            transaction.commit();
            System.out.println("deleted successfully student with id: " + id);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

        int choice=8;
        while(choice!=5)
        {


            System.out.println("1. create \n2. read \n3. update \n4. delete \n5. exit");
            System.out.println("enter your choice-");
            choice=scanner.nextInt();
            switch (choice)
            {
                case 1:
                    createStudent(); break;
                case 2:
                    readStudent(); break;
                case 3:
                    updateStudent(); break;
                case 4:
                    deleteStudent(); break;
                case 5:
                    System.out.println("exiting....");
                    break;
                default:
                    System.out.println("invalid input !!!!");
            }

        }


    }
}
