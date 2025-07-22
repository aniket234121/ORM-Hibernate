# Native SQL

Native SQL = Plain SQL (not HQL)

Hibernate allows you to run raw SQL queries directly on the database.

Useful when:

* You need to use database-specific functions
* You want full control over joins, performance
* You're using complex queries that are hard to express in HQL

### 🔧 Syntax: Native SQL in Hibernate

    String sql = "SELECT * FROM student";
    Query<Student> query = session.createNativeQuery(sql, Student.class);
    List<Student> students = query.getResultList();
    createNativeQuery(String sql, Class resultClass) is used to map result directly to entity.

### 🧪 Example: Fetch All Students

    public static void getAllStudentsNative() {
    Transaction tx = null;
    try (Session session = sessionFactory.openSession()) {
    tx = session.beginTransaction();
    
            String sql = "SELECT * FROM student"; // table name must match DB table
            Query<Student> query = session.createNativeQuery(sql, Student.class);
            List<Student> students = query.getResultList();
    
            for (Student s : students) {
                System.out.println(s);
            }
    
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
### 📝 Example: Insert Using Native SQL

    String sql = "INSERT INTO student (id, name) VALUES (:id, :name)";
    Query<?> query = session.createNativeQuery(sql);
    query.setParameter("id", 101);
    query.setParameter("name", "Amit");
    int rows = query.executeUpdate();

🔍 Example: Use Native SQL With Parameters

    String sql = "SELECT * FROM student WHERE name = :name";
    Query<Student> query = session.createNativeQuery(sql, Student.class);
    query.setParameter("name", "Tanya");
    List<Student> list = query.getResultList();
