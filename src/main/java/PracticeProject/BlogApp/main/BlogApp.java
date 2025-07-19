package PracticeProject.BlogApp.main;

import PracticeProject.BlogApp.Entity.Author;
import PracticeProject.BlogApp.Entity.Post;
import PracticeProject.BlogApp.Entity.Tag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BlogApp {
    private static SessionFactory sessionFactory = null;
    private static Scanner scanner = null;

    static {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        if (scanner == null) scanner = new Scanner(System.in);
    }

    public static void postdata()
    {
        //creating author
        Author author = new Author();
        System.out.println("enter your name");
        author.setName(scanner.nextLine());
        System.out.println("enter your age");
        author.setAge(scanner.nextByte());
        scanner.nextLine();

        List<Post> posts = new ArrayList<>();

        //creating post

        Post post = new Post();
        System.out.println("enter the post title");
        post.setTitle(scanner.nextLine());

        System.out.println("enter the post content");
        post.setContent(scanner.nextLine());
        post.setAuthor(author);

        //adding tags to post
        List<Tag> tags = new ArrayList<>();

        System.out.println("enter the no. of tags to add to this post");
        int count = scanner.nextInt();
        scanner.nextLine();
        while (count != 0) {
            Tag tag = new Tag();
            System.out.println("enter the tag name");
            tag.setName(scanner.nextLine());
            List<Post> tagRelatedPost = new ArrayList<>();
            tagRelatedPost.add(post);
            tag.setPosts(tagRelatedPost);

            tags.add(tag);
            count--;
        }
        post.setTags(tags);
        posts.add(post);
        author.setPosts(posts);

        Transaction transaction = null;

        //persisting data to db
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(author);

            transaction.commit();
            System.out.println("successfully persisted to the Database..");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
                System.out.println("rollback the persisted data");
            }
        }

    }

    public static void readPost()
    {
        System.out.println("enter the id of author");
        int id=scanner.nextInt();
        try(Session session=sessionFactory.openSession())
        {
            Author author=session.get(Author.class,id);
            if(author==null) throw new Exception("author with id not found");
            else
            {
                System.out.println("Author name: "+author.getName());
                List<Post> posts=author.getPosts();
                System.out.println("---------All posts------");
                posts.forEach(item->{
                    System.out.println("title:- "+item.getTitle());
                    System.out.println("content:- "+item.getContent());
                    List<Tag> tags=item.getTags();
                    StringBuilder sb=new StringBuilder();
                    for (Tag tag : tags) {
                        sb.append(tag.getName()+" ");
                    }
                    System.out.println("tags:- "+sb.toString());
                });
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage()+" cannot get the author data");
        }
    }
    public static void main(String[] args) {
        System.out.println("Welcome to Blog App...");

//        postdata();
        readPost();
    }
}
