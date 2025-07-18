package learning.annotations.Entity;

import jakarta.persistence.*;

import java.util.List;
@Entity
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @ManyToMany(mappedBy = "faculties")
    private List<Student2> students;

    public String getName() {
        return name;
    }

    public Faculty(String name, List<Student2> students) {
        this.name = name;
        this.students = students;
    }

    public Faculty() {
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students=" + students +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student2> getStudents() {
        return students;
    }

    public void setStudents(List<Student2> students) {
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
