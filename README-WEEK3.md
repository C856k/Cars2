## Where and why you have used a @OneToMany annotation 
I have used the @OneToMany annotation in my Member class, because a member can have many carreservations on different cars, 
but a car cant have many reservations from different members on the same day.
## Where an why you have used a @ManyToOne annotation
The @ManyToOne annotations, is located in my code in the member, car and reservation classes, because a member can have many reservations over a period of time
and a car can have many reservations on it, as long as it isnt the same day and ect.
## The purpose of the CascadeType, FetchType and mappedBy attributes you can use with one-to-many
CascadeType:

FetchType:

mappedBy:

## How/where you have (if done) added user defined queries to you repositories

## a few words, explaining what you had to do on your Azure App Service in order to make your Spring Boot App connect to your Azure MySqlDatabase
I selected Azure Database for MySQL, where i had to provide the right information to make the SQL Database and then i made a connection on MySQL 
and checked if my connection to Azure worked. 
## a few words about where you have used inheritance in your project, and how it's reflected in your database

## What are the pros & cons of using the Single Table Strategy for inheritance?
Pros:

Cons:

## how are passwords stored in the database with the changes suggested in part-6 of the exercise
