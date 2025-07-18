package learning.annotations.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student_details_annotations")
public class Student2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "s_name", length = 20, nullable = false, unique = false)
    private String name;
    @Column(name = "s_phone", length = 10, nullable = false, unique = false)
    private long phone;
    @Column(name = "s_guardian", length = 20, nullable = false, unique = false)
    private String guardian;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "s_address")
    private Address address;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Course> courses;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_faculties",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "faculty_id")
    )
    private List<Faculty> faculties;

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "student_course_annotation",
//            joinColumns = @JoinColumn(name = "student_id"),
//            inverseJoinColumns = @JoinColumn(name = "course_id")
//    )
//    private List<Courses> courses;

    public int getId() {
        return id;
    }

    public Student2() {
    }

//    public List<Courses> getCourses() {
//        return courses;
//    }
//
//    public void setCourses(List<Courses> courses) {
//        this.courses = courses;
//    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", guardian='" + guardian + '\'' +
                ", address=" + address +
//                ", courses=" + courses +
                '}';
    }

    public Student2(String name, long phone, String guardian, Address address) {
        this.name = name;
        this.phone = phone;
        this.guardian = guardian;
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getGuardian() {
        return guardian;
    }

    public void setGuardian(String guardian) {
        this.guardian = guardian;
    }
}
