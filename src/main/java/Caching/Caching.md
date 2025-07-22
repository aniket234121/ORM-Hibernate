# 🔷 What is Caching?

Caching is the process of storing frequently accessed data in memory to avoid repeated database hits.

Hibernate supports multiple levels of caching to optimize performance.

| Benefit                   | Explanation                                        |
|---------------------------|----------------------------------------------------|
| Reduces DB calls          | Fetching from memory is faster than querying DB    |
| Improves performance      | Speeds up read-heavy applications                  |
| Lowers DB load            | Useful when many users access same data repeatedly |
| Minimizes network traffic | Fewer round-trips to DB server                     |

## Types (Levels) of Caching

| Cache Level      | Scope            | Enabled By Default | Purpose                               |
|------------------|------------------|--------------------|---------------------------------------|
| 1️⃣ First-Level  | Per Session      | ✅ Yes              | Reuses same object within session     |
| 2️⃣ Second-Level | SessionFactory   | ❌ No               | Shares entities between sessions      |
| 3️⃣ Query Cache  | Result Set Cache | ❌ No               | Caches query results (needs L2 cache) |


### 1. First-Level Cache (Default)

* Associated with the Session object

* Automatically enabled
 
* Caches objects within a session

Example
```
    Session session = sessionFactory.openSession();
    Student s1 = session.get(Student.class, 1);  // DB hit
    Student s2 = session.get(Student.class, 1);  // Cached
```
**⚠️ Cache is cleared when:**
1. **session.close()** is called
2. **session.clear()** or **session.evict()** is used

### 2. Second-Level Cache (L2 Cache)

* Associated with SessionFactory
* Must be enabled manually
* Stores entity data across multiple sessions

#### **Popular L2 cache providers** 

| Provider   | Use Case                 |
|------------|--------------------------|
| EHCache    | Lightweight, common      |
| Infinispan | Scalable, clustered apps |
| Redis      | Distributed caching      |

#### Steps to Enable EHCache in Hibernate
In Hibernate 6+:

* org.hibernate.cache.ehcache.EhCacheRegionFactory is removed.
* Hibernate now uses JCache (JSR-107) API for integration.
* EHCache 3+ is JSR-107 compliant and used via javax.cache.


1. Add Dependency
```
  
    <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-jcache</artifactId>
            <version>${hibernate.version}</version>
        </dependency>
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-ehcache</artifactId>
            <version>${hibernate.ehcache.version}</version>
        </dependency>
        <dependency>
            <groupId>org.ehcache</groupId>
            <artifactId>ehcache</artifactId>
            <version>${ehcache.version}</version>
        </dependency>
        <dependency>
            <groupId>com.sun.xml.bind</groupId>
            <artifactId>jaxb-core</artifactId>
            <version>${jaxb.core.version}</version>
        </dependency>
        <dependency>
            <groupId>javax.xml.bind</groupId>
            <artifactId>jaxb-api</artifactId>
            <version>${jaxb.api.version}</version>
        </dependency>
        <dependency>
            <groupId>com.sun.xml.bind</groupId>
            <artifactId>jaxb-impl</artifactId>
            <version>${jaxb.api.version}</version>
        </dependency>
```

* hibernate-ehcache: Bridges Hibernate with Ehcache.
* Ehcache Core: Contains the necessary classes to implement caching.
* XML Bind Api: To Convert the ehcache.xml configuration.

2. Configure in hibernate.cfg.xml
```
    <!-- Caching configuration -->
        <property name="hibernate.cache.region.factory_class">org.hibernate.cache.jcache.JCacheRegionFactory</property>
        <property name="hibernate.cache.use_second_level_cache">true</property>
        <property name="hibernate.cache.use_query_cache">true</property>
        <property name="javax.cache.provider">org.ehcache.jsr107.EhcacheCachingProvider</property>
        <property name="hibernate.javax.cache.uri">ehcache.xml</property>
```
3. Create ECache.xml in resources folder

```
    <config
        xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'
        xmlns='http://www.ehcache.org/v3'
        xsi:schemaLocation="
     https://www.ehcache.org/v3/ https://www.ehcache.org/schema/ehcache-core-3.0.xsd">

    <cache alias="entityCache">
        <expiry>
            <ttl unit="minutes">10</ttl>
        </expiry>
        <heap unit="entries">1000</heap>
    </cache>

    <cache alias="queryCache">
        <expiry>
            <ttl unit="minutes">5</ttl>
        </expiry>
        <heap unit="entries">500</heap>
    </cache>

    <cache alias="timestampsCache">
        <expiry>
            <ttl unit="minutes">60</ttl>
        </expiry>
        <heap unit="entries">200</heap>
    </cache>

    </config>
```
4. Annotate Entity class with cacheable

```
    @Entity
    @Cacheable
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public class Student {
        @Id
        private int id;
        private String name;
    }
```

#### CacheConcurrencyStrategy Options

| Strategy                   | When to Use                             |
|----------------------------|-----------------------------------------|
| **READ\_ONLY**             | Static data that never changes          |
| **READ\_WRITE**            | Default safe strategy, uses versioning  |
| **NONSTRICT\_READ\_WRITE** | Rare updates, doesn’t use versioning    |
| **TRANSACTIONAL**          | Used in JTA managed transactions (rare) |

### 3. Query Cache (Optional)
* Caches result sets of HQL / Criteria queries
* Requires L2 cache to be enabled

#### Config

    <property name="hibernate.cache.use_query_cache">true</property>

**Example** 

    Query<Student> q = session.createQuery("from Student where name = :name", Student.class);
    q.setParameter("name", "Tanya");
    q.setCacheable(true);
    List<Student> students = q.getResultList();


### Cache Clear

| Operation                  | Code Example                            |
|----------------------------|-----------------------------------------|
| Evict single entity        | `session.evict(student);`               |
| Clear entire session cache | `session.clear();`                      |
| Evict second-level region  | `sessionFactory.getCache().evictAll();` |
