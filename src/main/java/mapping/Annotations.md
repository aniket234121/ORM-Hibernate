# Annotations

the motive of using a hibernate is to skip the SQL part and focus on core java concepts.

Generally, in hibernate, we use
XML mapping files for converting our POJO classes data to database data and vice-versa.

But using XML becomes a little
confusing so, in replacement of using XML, we use annotations inside our POJO classes directly to declare the changes.

Also using annotations inside out POJO classes makes things simple to remember and easy to use.

Annotation is a powerful method of providing metadata for the database tables and also it gives brief information
about the database table structure and also POJO classes simultaneously.

### 1. Entity mapping annotations

| Annotation                                                          | Description                                                          | Usage Location |
|---------------------------------------------------------------------|----------------------------------------------------------------------|----------------|
| `@Entity`                                                           | Marks a class as a persistent Hibernate entity                       | Class          |
| `@Table(name = "table_name")`                                       | Maps entity to a specific database table                             | Class          |
| `@Id`                                                               | Marks a field as the primary key                                     | Field / Getter |
| `@GeneratedValue(strategy = ...)`                                   | Auto-generates primary key (`AUTO`, `IDENTITY`, `SEQUENCE`, `TABLE`) | Field / Getter |
| `@Column(name = "...", nullable = ..., unique = ..., length = ...)` | Customizes the column mapping                                        | Field / Getter |
| `@Transient`                                                        | Prevents a field from being persisted                                | Field          |
| `@Access(AccessType.FIELD/PROPERTY)`                                | Controls access strategy (field or getter/setter)                    | Field / Class  |

1. Pick one access strategy - Either all annotations on **fields** OR all on **getters**. Not mixed
2. `@Id` decides access type - If `@Id` is on getter → Hibernate uses **property access**.
3. Don’t mix annotations - If `@Id` is on a getter, put all annotations on getters (not fields).

### 2. mapping annotations

| Annotation                                                              | Description                                               | Usage Location |
|-------------------------------------------------------------------------|-----------------------------------------------------------|----------------|
| `@OneToOne`                                                             | Defines a one-to-one relationship                         | Field          |
| `@OneToMany`                                                            | Defines a one-to-many relationship                        | Field          |
| `@ManyToOne`                                                            | Defines a many-to-one relationship                        | Field          |
| `@ManyToMany`                                                           | Defines a many-to-many relationship                       | Field          |
| `@JoinColumn(name = "...")`                                             | Specifies the foreign key column                          | Field          |
| `@JoinTable(name = "...", joinColumns = ..., inverseJoinColumns = ...)` | Defines the join table for `@ManyToMany`                  | Field          |
| `@MappedBy`                                                             | Indicates the inverse (non-owning) side of a relationship | Field          |
| `@Cascade({...})`                                                       | (Hibernate-only) Controls cascading beyond standard JPA   | Field          |
| `@Fetch(FetchMode.JOIN/SELECT/SUBSELECT)`                               | (Hibernate-only) Defines fetch strategy                   | Field          |

### 3. Entity lifecycle callback annotations

| Annotation     | Description                            | Usage Location |
|----------------|----------------------------------------|----------------|
| `@PrePersist`  | Called before saving a new entity      | Method         |
| `@PostPersist` | Called after saving a new entity       | Method         |
| `@PreUpdate`   | Called before updating an entity       | Method         |
| `@PostUpdate`  | Called after updating an entity        | Method         |
| `@PreRemove`   | Called before deleting an entity       | Method         |
| `@PostRemove`  | Called after deleting an entity        | Method         |
| `@PostLoad`    | Called after entity is fetched from DB | Method         |

### 4. Fetching ,caching & optimization

| Annotation               | Description                              | Usage Location |
|--------------------------|------------------------------------------|----------------|
| `@Fetch(FetchMode.JOIN)` | (Hibernate) Customize fetch strategy     | Field          |
| `@BatchSize(size = 10)`  | (Hibernate) Controls batch fetching size | Field or Class |
| `@Cache(usage = ...)`    | (Hibernate) Enables second-level caching | Class / Field  |

### 5. Inheritance mapping annotations

| Annotation                           | Description                                         | Usage Location |
|--------------------------------------|-----------------------------------------------------|----------------|
| `@Inheritance(strategy = ...)`       | Defines inheritance strategy (`SINGLE_TABLE`, etc.) | Class          |
| `@DiscriminatorColumn(name = "...")` | Column used to distinguish subclass types           | Class          |
| `@DiscriminatorValue("...")`         | Value assigned to subclass in discriminator column  | Class          |

### more...

| Annotation                                    | Description                                          | Usage Location |
|-----------------------------------------------|------------------------------------------------------|----------------|
| `@Lob`                                        | Marks field as Large Object (`CLOB` or `BLOB`)       | Field          |
| `@Temporal(TemporalType.DATE/TIME/TIMESTAMP)` | Specifies how `Date` or `Calendar` fields are stored | Field          |
| `@Enumerated(EnumType.STRING/ORDINAL)`        | Maps enums as string or ordinal                      | Field          |
| `@Version`                                    | Enables optimistic locking                           | Field          |
| `@CreationTimestamp`                          | (Hibernate) Auto-populates timestamp on insert       | Field          |
| `@UpdateTimestamp`                            | (Hibernate) Auto-populates timestamp on update       | Field          |
