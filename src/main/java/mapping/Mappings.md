# Mapping Relations in Hibernate
## 1️⃣ @OneToOne

🔹 One entity instance is related to exactly one instance of another entity.

🔹 Usage:
```
    @Entity
    public class Student {
    @Id
    private int id;
    
        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "address_id")
        private Address address;
    }
```

```
    @Entity
    public class Address {
    @Id
    private int id;
    private String city;
    }
```

🔹 Notes:
* @JoinColumn creates a foreign key in the owning table (e.g., Student table has address_id column). 


* cascade = CascadeType.ALL propagates all operations (save, delete) to related entity.
 

* By default, unidirectional. You can make it bidirectional by adding mappedBy in the other class.


## 2️⃣ @OneToMany and @ManyToOne

🔹 OneToMany: One entity is related to many instances of another entity.

🔹 ManyToOne: Many entities are related to one parent.

🔹 Usage (Bidirectional Example):
```
@Entity
public class Department {
@Id
private int id;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employees;
}
```
```
@Entity
public class Employee {
@Id
private int id;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;
}
```
🔹 Notes:
* mappedBy indicates which side is non-owning (inverse).


* @JoinColumn is used only on the owning side (Employee).


* Use **cascade = CascadeType.ALL**  if you want to save parent + children in one call.

## 3️⃣ @ManyToMany
🔹 Meaning:
Multiple entities are related to multiple other entities. A join table is required.

🔹 Usage:
```
@Entity
public class Student {
@Id
private int id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;
}
```
```

@Entity
public class Course {
@Id
private int id;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students;
}
```
🔹 Notes:
* Creates a third (join) table: student_course.


* joinColumns = this class foreign key.

 
* inverseJoinColumns = opposite class foreign key.

 
* mappedBy in opposite class defines the inverse (non-owning) side.

 


## 🧪 Tips for Practicing

* Add @CascadeType.ALL for testing purposes, but use carefully in production.


* Always test bidirectional relationships using toString() cautiously (avoid recursion).


* Use mappedBy to make relationships bidirectional.


* Fetch strategies (EAGER, LAZY) can be tuned using @OneToMany(fetch = FetchType.LAZY).

