## How far i came.
I have managed to do every step, but somehow i keep getting problems with deploying my application to Azure.

## What are the benefits of using a RESTful API
The benefits of using a RESTful API, is that you makes sure that you avoid that a hacker can make those DDos attacks, that sends a lot of requests to a server. That is prevented by using a RESTful API.
## What is JSON, and why does JSON fit so well with REST?
JSON stands for Javascribt object notation and the reason it fits well with REST, is because it makes the data that the different REST functions, more easy to read if you want to see it as a text file.
## How you have designed simple CRUD endpoints using spring boot and DTOs to separate api from data  -> Focus on your use of DTO's
I have done that by naming the different methods with, the name of their functions, for example if i make an endpoint where i want to delete some data. Then i make a connection to the method that handles the deletions and so forth, with other endpoints.
## What is the advantage of using DTOs to separate api from data structure when designing rest endpoints
The advantage of using DTOs, is that then you have fully control over what data is being sent, as DTO stands for Data Transfer Objects. 

## Explain shortly the concept mocking in relation to software testing
The concept of mocking, is that you make a simulation of a database where you hardcode test data.
## How did you mock database access in your tests, using an in-memory database and/or mockito → Refer to your code
I did it like i explained in the previous question. I made a "database" where i wrote the data i wanted to test, to see if my different methods works as intended.
## Explain the concept Build Server and the role Github Actions play here
The concept of a build server is a way to check if, the code that you have pushed to your repository is buildable and doesnt make the program crash. Which can be checked by Github Actions, which we are using to check if its buildable.

## Explain maven, relevant parts in maven, and how maven is used in our CI setup. Explain where maven is used by your GitHub Actions Script(s)
I have used maven in my GitHub Action scribt, where i make the build in the Yaml file, by saying Build with Maven and run: mvn clean install

## Understand and chose cloud service models (IaaS, PaaS, SaaS, DBaaS)for your projects
Software as a Service (SaaS):
It is hosted and managed by the cloud provider, usually licensed with a monthly or annual subscription. Examples of SaaS software are: Microsoft 365, Skype and Dynamics CRM Online. SaaS requires the least amount of management by the customer. The provider is responsible for everthing exectept data, devices, accounts and identities.

Platform as a Service (PassS):
Is an environment for building, testing and deploying software applications. The goal PaaS is to help creating an application quickly without the underlying infrastructure. The cloud provider can with PaaS manage the hardware and operation systems, but the customer is responsible for the applications and data.

Infrastructure as a Service (IaaS):
Requires most management from the cloud customers. However the customers doesnt have any responsibilities for physical components like computers, the network or the security of the datacenters. But they still have some responsibilities for the software components such as the operating systems, applications and protecting the data.  

