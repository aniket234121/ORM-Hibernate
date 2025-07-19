# Configuration

Hibernate needs mapping information to link Java classes with DB tables.

Configuration is provided via:

1. hibernate.properties (Java Properties file)
2. hibernate.cfg.xml (XML configuration file) → commonly used

Place the config file at the root of the classpath.

### Properties inside hibernate configurations

| **Property**                        | **Description**                                                                         |
|-------------------------------------|-----------------------------------------------------------------------------------------|
| `hibernate.dialect`                 | Specifies the SQL dialect Hibernate should use (e.g., MySQL, Oracle, PostgreSQL).       |
| `hibernate.connection.driver_class` | The JDBC driver class used to connect to the database.                                  |
| `hibernate.connection.url`          | JDBC URL to locate and connect to the database instance.                                |
| `hibernate.connection.username`     | Username for accessing the database.                                                    |
| `hibernate.connection.password`     | Password corresponding to the database user.                                            |
| `hibernate.hbm2ddl.auto`            | Auto schema generation: `create`, `update`, `validate`, `create-drop`, or `none`.       |
| `hibernate.show_sql`                | Displays generated SQL statements in the console (mainly for debugging).                |
| `hibernate.format_sql`              | Formats SQL output in the console to be more readable.                                  |
| `hibernate.connection.pool_size`    | Sets the number of database connections in Hibernate's internal connection pool.        |
| `hibernate.connection.autocommit`   | Enables/disables JDBC autocommit mode (set to `false` for manual transaction handling). |

### hbm2ddl.auto property :-

The hbm2ddl.auto property in Hibernate defines how your database schema is handled.

Possible values are:

| **Value**     | **Meaning**                                      |
|---------------|--------------------------------------------------|
| `create`      | Creates DB schema; drops existing tables.        |
| `update`      | Updates existing schema without data loss.       |
| `validate`    | Validates schema; throws error if mismatch.      |
| `create-drop` | Creates schema on startup; drops it on shutdown. |
| `none`        | Does nothing with schema.                        |

### hibernate Dialect

A database dialect is a configuration option that allows software to translate general SQL statements into
vendor-specific DDL and DML.

Different database products, such as PostgreSQL, MySQL, Oracle, and SQL Server, have their
own variant of SQL, which are called SQL dialects.

**common dialects**

| **Database**      | **Dialect Class**                         |
|-------------------|-------------------------------------------|
| MySQL 8.x         | `org.hibernate.dialect.MySQL8Dialect`     |
| Oracle 12c        | `org.hibernate.dialect.Oracle12cDialect`  |
| PostgreSQL        | `org.hibernate.dialect.PostgreSQLDialect` |
| H2 (in-memory DB) | `org.hibernate.dialect.H2Dialect`         |
