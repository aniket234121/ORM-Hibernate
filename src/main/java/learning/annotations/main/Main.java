package learning.annotations.main;

import learning.annotations.Entity.Address;
//import learning.annotations.Entity.Courses;
import learning.annotations.Entity.Course;
import learning.annotations.Entity.Student2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner;
    private static SessionFactory sessionFactory;

    static {
        scanner = new Scanner(System.in);
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public static List<Course> getCourseListFromUser(Student2 student)
    {
        System.out.println("enter the no. of courses you want");
        int no=scanner.nextInt();
        scanner.nextLine();
        List<Course> courses=new ArrayList<>();
        while(no!=0)
        {
            Course course=new Course();
            System.out.println("enter course"+no+" name");
            course.setCourse_name(scanner.nextLine());
            course.setStudent(student);
            courses.add(course);
            no--;
        }
        return courses;
    }
    public static void createStudentData() {

        Student2 student = new Student2();
        System.out.println("enter student details:-\n name");
        student.setName(scanner.nextLine());
        System.out.println("phone");
        student.setPhone(scanner.nextLong());
        scanner.nextLine();
        System.out.println("guardian name");
        student.setGuardian(scanner.nextLine());
        System.out.println("enter pincode and city");
        int pincode = scanner.nextInt();
        scanner.nextLine(); // consume \n
        String city = scanner.nextLine();
        Address address = new Address(pincode, city);
        address.setStudent(student);
        student.setAddress(address);

        student.setCourses(getCourseListFromUser(student));

        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(student);
            transaction.commit();
            System.out.println("successfully saved the student data...");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (transaction != null) transaction.rollback();
        }
    }

    public static Student2 readStudentData() {
        System.out.println("Enter the id to get student data");
        int id = scanner.nextInt();
        try (Session session = sessionFactory.openSession();) {
            Student2 student = session.get(Student2.class, id);
            if (student != null) {System.out.println(student+"\n courses: "); student.getCourses().forEach(
                    item-> System.out.println(item.getCourse_name())
            );}
            else System.out.println("not found student details with id: " + id);
            return student;
        } catch (Exception e) {
            System.out.println("cannot read student data:- " + e.getMessage());

        }
        return null;
    }

    public static void deleteStudentData() {
        Student2 student = readStudentData();
        Transaction transaction = null;
        if (student != null) {
            try (Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                session.remove(student);
                System.out.println("student details deleted");
                transaction.commit();
            } catch (Exception e) {
                System.out.println("cannot delete " + e.getMessage());
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        } else {
            System.out.println(" student is not present in db");
        }
    }

    public static void main(String[] args) {

        int choice = 0;
        while (choice != 4) {
            System.out.println("enter the option \n 1.create \n 2.read \n 3.delete");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    createStudentData();
                    break;
                case 2:
                    readStudentData();
                    break;
                case 3:
                    deleteStudentData();
                    break;
                case 4:
                    System.out.println("exiting....");
                    break;
            }

        }

    }
}
