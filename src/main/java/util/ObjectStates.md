# Object States

| **State**      | **Meaning**                                                                  | **Managed by Hibernate?** | **Stored in DB?**          |
|----------------|------------------------------------------------------------------------------|---------------------------|----------------------------|
| **Transient**  | Object is created in Java, not associated with any Hibernate `Session`.      | ❌ No                      | ❌ No                       |
| **Persistent** | Object is associated with a Hibernate `Session` and mapped to DB.            | ✅ Yes                     | ✅ Yes (after flush/commit) |
| **Detached**   | Object was persistent but the `Session` is now closed or object was evicted. | ❌ No                      | ✅ Yes (until updated)      |
| **Removed**    | Object is marked for deletion (will be deleted on `commit`).                 | ✅ Yes                     | ⚠️ Pending deletion        |

example

```

1.Transient

Student s = new Student();  // Just a POJO
    s.setName("John");          // No DB interaction
    
    2.Persistent

Session session = factory.openSession();
    session.beginTransaction();
    
    session.save(s);   // Now Hibernate tracks 's' (it's persistent)
    
    session.getTransaction().commit();
    
    3.Detached
           
    session.close();    // After session closed, 's' becomes detached
    s.setName("Updated");  // This change won’t be tracked until reattached
    
    4.Removed
    
    session.delete(s);  // Marks object as removed (still in memory until commit)

```