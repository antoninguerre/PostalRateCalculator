# PostalRateCalculator
This is an application that computes the postal rates of standard and non-standard envelopes.

<h4>Authors:</h4>

- Antonin GUERRE
- Juliette DEBRAY
<br/>

# Running the Backend
## Component Versions
- IDE: Intellij Idea Ultimate Edition
- Gradle: version 6.8.3
- JDK: version 14.0.2
- PostgreSQL: version 13.2
- pgAdmin: version 4
- Spring CLI: version 2.4.2

## Setup Instructions

### PostgreSQL 13
Once the project has been imported from GitHub and all the mentioned components have been downloaded or upgraded to an adequate version, PostrgreSQL can be started on port 5432 (or another port if this one is unavailable). 

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img width="270" alt="Screen Shot 2021-11-06 at 7 03 56 PM" src="https://user-images.githubusercontent.com/71234445/140626300-4b2326dc-e2e6-44be-a040-ab0349c07993.png">

### pgAdmin 4
Throught pgAdmin, a new server called "localhost" can be created if it does not already exist. In this server, a new "Login/Group Role" can be created. It must be given all privileges.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img width="459" alt="Screen Shot 2021-11-06 at 7 02 13 PM" src="https://user-images.githubusercontent.com/71234445/140626269-9df9e89d-06b5-4f6a-bf1c-69997529dcd1.png">

After this has been done, a new database can be created in the "localhost" server group by right clicking on "Database".

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img width="450" alt="Screen Shot 2021-11-06 at 7 12 11 PM" src="https://user-images.githubusercontent.com/71234445/140626462-5bebdcca-1f6c-4ff9-a4ad-811ed252e154.png">

The database must be named and its owner must be set as the newly created user, as shown in the following example.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img width="703" alt="Screen Shot 2021-11-06 at 7 48 58 PM" src="https://user-images.githubusercontent.com/71234445/140627122-430d70c1-018f-439e-b935-a1881a279ccf.png">

### Run Configurations
In the IDE used to run the backend, the Spring Boot application's run configuration must be modified. The environment variable of the application must be set as: <br>
- SPRING_DATASOURCE_URL=jdbc:postgresql://localhost: __*port*__ / __*databaseName*__ ?user= __*userName*__ &password= __*userPassword*__

*__example__: SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/PostalRateCalculator?user=dev&password=dev*
<br/><br/>

After all of these steps have been followed, if the PostalRateCalculatorApplication class is run from the IDE, the following message will appear on localhost port 8082: "The Postal Rate Calculator application's backend is running!". <br>
Requests can now be made to the backend using the frontend or other applications such as Postman.
<br/><br/><br/>


# Running the Frontend
## Component Versions
- npm: version 7.6.0
- node: version 15.11.0

## Setup and Running
To run the frontend locally, you will need to have `npm` installed. With `PostalRateCalculator/PostalRateCalculator-Frontend` 
as your current directory, perform the following steps:

1. Run `npm install`
2. Run `npm run start`

Once this builds, the Postal Rate Calculator is accessible at localhost port 8087. This site will make requests against 
the localhost server mentioned above.<br>
To run the application with the backend, make sure PostgreSQL and the PostalRateApplication class are running.
<br/><br/><br/>


# Application Demo

https://user-images.githubusercontent.com/71234445/140685444-fe3a8f33-0fc3-4b16-8d36-a2096765a7f9.mov
