package mapping.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "student_course_annotation")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String course_name;
    @ManyToOne
    private Student2 student;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public Student2 getStudent() {
        return student;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", course_name='" + course_name + '\'' +
                ", student=" + student +
                '}';
    }

    public void setStudent(Student2 student) {
        this.student = student;
    }

    public Course() {
    }

    public Course(Student2 student, String course_name) {
        this.student = student;
        this.course_name = course_name;
    }
}
