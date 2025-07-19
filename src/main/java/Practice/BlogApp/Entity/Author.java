package Practice.BlogApp.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "author_name")
    private String name;
    @Column(name = "author_age")
    private byte age;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private List<Post> posts;

    public Author() {
    }

    public Author(String name, byte age) {
        this.name = name;
        this.age = age;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public int getId() {
        return id;
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

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }
}
