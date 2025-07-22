package Caching;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.Scanner;

public class CacheMain {
    private static SessionFactory sessionFactory;
    static{
        sessionFactory= HibernateUtil.getSessionFactory();
    }
    public static EmployeeEntity getData()
    {
        EmployeeEntity emp=null;
        Transaction transaction=null;
        try(Session session =sessionFactory.openSession())
        {
            transaction=session.beginTransaction();

            emp=session.get(EmployeeEntity.class,2);

            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if(transaction!=null)transaction.rollback();
        }
        return emp;
    }
    public static void main(String[] args) {

        System.out.println(getData().getId()+" "+ getData().getName());
        System.out.println("data stored in L2 cache");
        System.out.println(getData().getId()+" "+ getData().getName());

        System.out.println("cache cleared again call to db");
        sessionFactory.getCache().evictAll();   //clear all cache
        System.out.println(getData().getId()+" "+ getData().getName());
    }
}
