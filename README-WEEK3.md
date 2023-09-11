I keep getting problems with running the program, sometimes it works and sometimes it doesnt.
I have made roughly to step 8 or 9 in this weeks exercise 

## Where and why you have used a @OneToMany annotation 
I have used the @OneToMany annotation in my Member class, because a member can have many carreservations on different cars, 
but a car cant have many reservations from different members on the same day.
## Where an why you have used a @ManyToOne annotation
The @ManyToOne annotations, is located in my code in the member, car and reservation classes, because a member can have many reservations over a period of time
and a car can have many reservations on it, as long as it isnt the same day and ect.
## The purpose of the CascadeType, FetchType and mappedBy attributes you can use with one-to-many
CascadeType:
CascadeType is being used, when you want to determin, if the rest of the data in a column is getting deleted or not, when you delete some of the data in of the rows. If you want to make the data persist after the deletion, then you put PERSISTS after it. But make sure to have used the @Cascade annotation.
FetchType:

EAGER:
FetchType.EAGER makes the data, that needs to be sent to the database faster and takes the parent entity with it when it gets sent. 

LAZY:
FetchType.LAZY tells Hibernate to only fetch the related entities from the database when you use the relationship.
mappedBy:
The purpose of using mappedBy is to define the connection from one class to another when using the @OneToMany annotation.
## How/where you have (if done) added user defined queries to you repositories
I have attempted to make a query in one of my repository classes and added the required query with the @Query annotation.

## a few words, explaining what you had to do on your Azure App Service in order to make your Spring Boot App connect to your Azure MySqlDatabase
I selected Azure Database for MySQL, where i had to provide the right information to make the SQL Database and then i made a connection on MySQL 
and checked if my connection to Azure worked. 
## a few words about where you have used inheritance in your project, and how it's reflected in your database
The only place that i have used inheritance, is in my member class where i extend to the userWithRoles and i have used the @Inheritance annotation to put the inheritanceType to it, to show what type of strategy the inheritance should use.
## What are the pros & cons of using the Single Table Strategy for inheritance?

Pros:
The pros about using the Single Table Strategy, is that then you have a simplier overview of the tables in your database and its easy to add new classes to your database, but using columns to the database.

Cons:
The cons about using the Single Table Strategy, is then all the data that might not be need in one of the tables of the database.
## how are passwords stored in the database with the changes suggested in part-6 of the exercise
The way that the passwords is being stored in the database, is that they are being encrypted so the users passwords are safe, in case that a hacker gains access to the database.
