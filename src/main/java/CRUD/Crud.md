# CRUD

CRUD stands for:

1. Create – Insert a new record
2. Read – Retrieve records
3. Update – Modify existing records
4. Delete – Remove records

Hibernate provides methods like **persist(), get(), update(), and delete()** to perform these operations via **ORM (
Object
Relational Mapping).**

#### 🛠 Prerequisites

* Annotated entity class (@Entity)
* Hibernate configuration (hibernate.cfg.xml) (mapping class in configurations)
* HibernateUtil class with a configured SessionFactory or create configuration object and sessionFactory object

**CREATE**
```

    Student student = new Student();
    student.setId(1);
    student.setName("Aniket");
    Transaction=null;
    
    Try(Session session = sessionFactory.openSession();)
    {
        transaction = session.beginTransaction();
        session.persist(student);
        tx.commit();
    }catch(Exception e){
    
     e.printStackTrace();
     if(transaction==null)transaction.rollback();
     
     }
    

```

**READ**

get () Returns the object if found, else null.

Use load() if you're sure the object exists (throws Exception if not).
```
    int id = 1;

    Session session = sessionFactory.openSession();
    Student student = session.get(Student.class, id);
    session.close();
    
    System.out.println(student);

```
**UPDATE**

Hibernate automatically tracks changes made inside a transaction and issues the appropriate UPDATE query.
```
    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    
    Student student = session.get(Student.class, id);
    if (student != null) {
        student.setName("Updated Name"); // automatic dirty checking
    }
    
    tx.commit();
    session.close();

```
**DELETE**

* delete() requires a persistent object.
* Ensure the object exists before deleting.
```
    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    
    Student student = session.get(Student.class, id);
    if (student != null) {
        session.delete(student);
    }
    
    tx.commit();
    session.close();

```
#### 🔐 Transaction Management
* Always open a Transaction for each CRUD operation.
* Commit only after operation succeeds.
* Rollback if an exception occurs.

Keep SessionFactory as a singleton or static field (use HibernateUtil).
```java
    public class HibernateUtil {
        private static SessionFactory sessionFactory;
    
        static {
            Configuration cfg = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class);
            sessionFactory = cfg.buildSessionFactory();
        }
    
        public static SessionFactory getSessionFactory() {
            return sessionFactory;
        }
    }

```