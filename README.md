# Art Gallery System
ECSE321 (Fall 2020) - Group 06 project

## General
### Scenario
The art gallery in our neighbourhood has always done business the old-fashioned way: connecting local artists
with potential customers by showcasing their work at the gallery. The current pandemic (COVID-19) is forcing them to change
their business model and focus their business online. They hired us to help them design a new software system.

The gallery wants to focus on local artists and encourage them to use their website and app to promote and sell
their work. Artists register, create a profile, and present any pieces they have to sell. Interested customers can
browse and buy what they like. The gallery can also promote artwork available on their premises. Artists can deliver
their art to customers by mail or can leave it at the store for pickup. The gallery makes its income on commission
from sold artwork.

### Main scope of the project
In teams of five students, our goal is to gather requirements for the scenario presented above, design a multi-tier software solution to satisfy those
requirements, implement the system, validate that the system is satisfying the requirements, and develop a release
pipeline to automate the software delivery process.

Our application must support the scenarios described for every stakeholder. All functionality of the system needs
to be accessible via the web frontend for respective stakeholders. In addition, a mobile (Android) frontend shall
allow the execution of the most important functionality for the given stakeholder, i.e. it shall have both read and
write access to the backend via RESTful service calls. External systems or services are not required to be integrated. 

We will deliver the system in four main iterations during the term. The corresponding deliverables are to be
submitted at checkpoints throughout the term as described below.

### Team members
#### We're just a bunch of U2 software engineering students at McGill University!
 - Noah Chamberland
 - Justin Legrand
 - Olivier Normandin
 - André-Walter Panzini
 - Sen Wang
 
## Menu
### Heroku Application
### [Deliverable 1](https://github.com/McGill-ECSE321-Fall2020/project-group-06#Deliverable1)
### [Deliverable 2](https://github.com/McGill-ECSE321-Fall2020/project-group-06#Deliverable2)
### [Deliverable 3](https://github.com/McGill-ECSE321-Fall2020/project-group-06#Deliverable3)
### [Deliverable 4](https://github.com/McGill-ECSE321-Fall2020/project-group-06#Deliverable4)

### Heroku Application

Heroku is a cloud service platform that is easy to use for many development projects. It enables application development and deployment. Heroku platform manages hardware and servers, so developer using Heroku are able to focus on perfecting their applications. With the benefit of offloading many of the concerns around application deployment and operation, our project is deloyed on Heroku.

##### Backend
- https://art-gallery-backend.herokuapp.com

##### Web Frontend
- (To be added in later deliverables...)

## Deliverable 1
### Main tasks
- Requirement model: Functional & Non functional Requirements, Use cases, Use case specification
- Domain model
- Database design
- Test cases for persistence layer

### Documents
- Requirement model
  - [System Requirements](link to be added)
  - [Use Cases](link to be added)
  - [Use Case Diagram](link to be added)
  
- Domain model
  - [Class Diagram](link to be added)

- Reports
  - [Testing Report](link to be added)
  - [Deliverable 1 Project Report](link to be added)

- Minutes
  - [Meeting Minutes 1.1](link to be added)
  - [Meeting Minutes 1.2](link to be added)
  - [Meeting Minutes 1.3](link to be added)
  
- Effort Table
  - [Effort Table](https://github.com/McGill-ECSE321-Fall2020/project-group-06#Table)

### Table
Insert table

## Deliverable 2
### Main Tasks
(Backend and Quality Assurance)
### Documents

### Table

To be continued...

## Deliverable 3
### Main Tasks
(Web Frontend and Architecture)
### Documents

### Table

To be continued...

## Deliverable 4
### Main Tasks
(Mobile Frontend and Availability)
### Documents

### Table

To be continued...


 In addition, the README.md file should contain
an overview table with names, team roles, and individual efforts (in hours) with separated entries for each
deliverable. Project Deliverable 1 shall be accompanied with a succinct project report as part of the project wiki
which records the meeting minutes and the key design decisions taken by the team. This project report should be
navigable from the README.md file. Altogether, the team should comply with all the Technological Constraints.


Your project should adhere to the following technological constraints:
    1. For each sprint, your team must
        1.1. Provide project backlog using GitHub Projects.
        1.2. Use issues in GitHub to track development, release engineering, and documentation tasks.
        1.3. Define milestones of the project for each deliverable and assign all issues created during a sprint to its
             corresponding milestone.
        1.4. Provide documentation (e.g. meeting minutes with key decisions, effort table, models, supplementary
             images) using the wiki pages of the GitHub repository.
    2. Starting from Sprint 1 (Database), your team must
        2.1. Use UML to create a domain model.
        2.2. Implement a persistence layer using a Postgres database.
        2.3. Use the ORM technology Hibernate to map objects to database concepts.
        2.4. Create a Spring/Spring Boot project.
        2.5. Configure a build system using Gradle.
        2.6. Use a Continuous Integration process using Travis CI to build and test the database layer.
    3. Starting from Sprint 2 (Backend), your team must
        3.1. Implement RESTful web service using Java Spring/Spring Boot.
        3.2. Provide a suite of unit tests for the backend using JUnit.
        3.3. Deploy the project as a Heroku application in addition to the constraints above.
    4. Starting from Sprint 3 (Web), your team must
        4.1. Implement the web frontend using Vue.js.
    5. For Sprint 4 (Android), your team must
        5.1. Implement the mobile frontend using the Android SDK but without the need for continuous integration
             and deployment for the Android frontend.
             
