# HQL (Hibernate Query Language)
* HQL stands for Hibernate Query Language.


* It is object-oriented and similar to SQL but works with entity classes and attributes, not table/column names.


* It is database independent and uses Java class and property names.

### Why use HQL?

| Benefit                | Description                                               |
|------------------------|-----------------------------------------------------------|
|  Object-oriented       | Queries use Java classes and fields.                      |
|  Portable across DBs  | No need to change code for different databases.           |
|  Simplified queries   | Cleaner and shorter than SQL.                             |
|  Hibernate integration | Works well with caching, lazy loading, and relationships. |

HQL is case-sensitive for class and property names.

### 🔸 5. Types of HQL Queries

### 1. Select 
A. 📘 Select (Retrieve all)

    String hql = "from Student";

B. 📘 Select with where clause

    String hql = "from Student where name = :studentName";
    query.setParameter("studentName", "Aniket");

C. 📘 Select specific fields

    String hql = "select name, phone from Student";
    List<Object[]> data = query.list();

### 2. Named Parameters
Use :paramName to pass values

    Query<Student> q = session.createQuery("from Student where id = :sid", Student.class);
    q.setParameter("sid", 101);

### 3. Aggregate Functions
| Function  | Description   | Example                          |
|-----------|---------------|----------------------------------|
| `count()` | Count rows    | `select count(*) from Student`   |
| `avg()`   | Average       | `select avg(phone) from Student` |
| `sum()`   | Sum values    | `select sum(phone) from Student` |
| `min()`   | Minimum value | `select min(phone) from Student` |
| `max()`   | Maximum value | `select max(phone) from Student` |


### 4. Pagination

    Query<Student> q = session.createQuery("from Student", Student.class);
    q.setFirstResult(0); // Offset
    q.setMaxResults(10); // Limit

### 5. Update Query

    String hql = "update Student set name = :newName where id = :id";
    Query q = session.createQuery(hql);
    q.setParameter("newName", "Rahul");
    q.setParameter("id", 101);
    int rowsAffected = q.executeUpdate();

### 6. Delete Query

    String hql = "delete from Student where id = :id";
    Query q = session.createQuery(hql);
    q.setParameter("id", 102);
    q.executeUpdate();

### Conditional Clauses
| Clause     | Example                                   |
|------------|-------------------------------------------|
| `LIKE`     | `from Student where name like 'A%'`       |
| `BETWEEN`  | `from Student where id between 10 and 20` |
| `IN`       | `from Student where id in (1, 2, 3)`      |
| `IS NULL`  | `from Student where phone is null`        |
| `ORDER BY` | `from Student order by name desc`         |

