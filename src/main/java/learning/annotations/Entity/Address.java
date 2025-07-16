package learning.annotations.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "address_annotations")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String city;
    private int pincode;
    @OneToOne(mappedBy = "address")
    private Student2 student;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public Student2 getStudent() {
        return student;
    }

    public void setStudent(Student2 student) {
        this.student = student;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public Address(int pincode, String city) {
        this.pincode = pincode;
        this.city = city;
    }

    public Address() {
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", pincode=" + pincode +
                '}';
    }
}
