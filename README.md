# ORM

ORM stands for Object-Relational Mapping (ORM) is a programming technique for converting data between relational
databases and object-oriented programming languages.

#### ORM is a programming technique that:

1. Maps Java objects (POJOs) to database tables
2. Allows you to interact with the database using objects, not SQL queries
3. Think of ORM as a bridge between your Java code and relational databases.

### Problems with Traditional JDBC

Using JDBC (Java Database Connectivity) directly has several limitations:

| Problem               | Description                                                         |
|-----------------------|---------------------------------------------------------------------|
| Boilerplate Code      | Repetitive code for connection, statement, and result set handling. |
| Manual Object Mapping | You need to manually convert `ResultSet` rows to Java objects.      |
| Too Much Repetition   | Similar code across all DAOs, making maintenance harder.            |
| No Caching            | JDBC doesn't cache data between queries.                            |
| Poor Transaction Mgmt | Manual `commit()` and `rollback()` calls are error-prone.           |
| No Schema Generation  | Tables must be manually created with SQL DDL scripts.               |

### Why Hibernate? (ORM Framework)

Hibernate is the most popular ORM framework for Java.

It solves the problems of JDBC by:

| Advantage                | Description                                                              |
|--------------------------|--------------------------------------------------------------------------|
| ✅ Automatic Mapping      | Maps Java classes to database tables using annotations or XML.           |
| ✅ HQL Support            | Hibernate Query Language (HQL) allows object-based querying.             |
| ✅ Less Boilerplate       | No need for repetitive JDBC code.                                        |
| ✅ Caching Support        | First-level and optional second-level caching to improve performance.    |
| ✅ Schema Generation      | Can auto-create or update DB tables based on entity classes.             |
| ✅ Transaction Management | Built-in support for clean transaction handling.                         |
| ✅ Database Independence  | Easily switch between different RDBMS (MySQL, PostgreSQL, Oracle, etc.). |

## 2. Hibernate architecture
[Hibernate Architecture](src/main/java/util/Architecture.md)
## 3. Configuration
[Configuration notes](src/main/java/util/Configuration.md)

## 4. Object States
[Object states](src/main/java/util/ObjectStates.md)

## 5. Hibernate Annotations
[Hibernate Annotation notes](src/main/java/mapping/Annotations.md)

## 6. CRUD Operations in hibernate
[CRUD notes](src/main/java/CRUD/Crud.md)

## 7. Mappings
[For Relationship Mapping click here ](src/main/java/mapping/Mappings.md)

## 8. HQL (Hibernate Query language)
[Hql notes](src/main/java/HQL/HQL.md)

## 9. Native SQL
[Native SQL](src/main/java/HQL/NativeSQL.md)

## 10. Caching
[Caching notes](src/main/java/Caching/Caching.md)