# Cars
## The idea with, and reasons for why to use, a ORM-mapper:

The idea of using a ORM-mapper, is that even though you dont have created columns in your DB, then you can create them from your IDE. 
Which is nice because, then you save a lot of time and dont have to both be on your DB manager and IDE at the same time.

## The meaning of the terms JPA, Hibernate and Spring Data JPA and how they are connected:

### JPA:
JPA provides a common API that can be implemented by various ORM frameworks, allowing developers to write database-agnostic code. It abstracts away the details of how data is stored and retrieved from the database.
### Hibernate:
Hibernate simplifies database interaction in Java applications by handling the underlying SQL and database-specific details, allowing developers to work with Java objects instead of writing raw SQL queries.
### Sprint Data JPA:
Spring Data JPA is part of the broader Spring Data project and is a higher-level abstraction built on top of JPA and Hibernate. It simplifies database access in Spring-based applications.
### How they are connected:
JPA is a specification, Hibernate is one of its implementations, and Spring Data JPA is a higher-level framework built on top of JPA and Hibernate to simplify database access in Spring applications. They work together to enable efficient database interactions in Java applications, particularly in the Spring ecosystem.
### How to create simple Java entities and map them to a database via the Spring Data API:



### How to control the mapping between individual fields in an Entity class and their matching columns in the database:

I did that, by providing the different information that a columns needs and when i run the program, the different columns would be created with the provided variables, that i made in the classes in the Entity packages.

### How to auto generate IDs, and how to ensure we are using a specific database's preferred way of doing it (Auto Increment in our case for  MySQL):
   
I did that with using the @ID command and locating it over the different variable that was going to be the ID in the different collumns and so on.

### How to use and define repositories and relevant query methods using Spring Data JPAs repository pattern:
   
I did that by, making them an interface class, that extends the JPA reporsitory libary
### How to write simple "integration" tests, using H2 as a mock-database instead of MySQL:

I did that by hard code some data that couldve been in the MySQL and then made some very simple tests, to make sure that my, in this case repositories. And made sure, that the "data" could be read and deleted by running tests.

### How to add (dev) connection details for you local MySQL database:

You do that in the projects application properties, where you makes it read your username and password, that you have provided in the configuration settings.
