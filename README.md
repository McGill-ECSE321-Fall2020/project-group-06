# Art Gallery System [![Build Status](https://travis-ci.com/McGill-ECSE321-Fall2020/project-group-06.svg?token=g4pq4Csck3WcLdnC6sWk&branch=master)](https://travis-ci.com/McGill-ECSE321-Fall2020/project-group-06)
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
- [Heroku Application](https://github.com/McGill-ECSE321-Fall2020/project-group-06/blob/master/README.md#heroku-application)
- [Deliverable 1](https://github.com/McGill-ECSE321-Fall2020/project-group-06/blob/master/README.md#deliverable-1)
- [Deliverable 2](https://github.com/McGill-ECSE321-Fall2020/project-group-06/blob/master/README.md#deliverable-2)
- [Deliverable 3](https://github.com/McGill-ECSE321-Fall2020/project-group-06/blob/master/README.md#deliverable-3)
- [Deliverable 4](https://github.com/McGill-ECSE321-Fall2020/project-group-06/blob/master/README.md#deliverable-4)

## Heroku Application

Heroku is a cloud service platform that is easy to use for many development projects. It enables application development and deployment. Heroku platform manages hardware and servers, so developer using Heroku are able to focus on perfecting their applications. With the benefit of offloading many of the concerns around application deployment and operation, our project is deloyed on Heroku.

#### Backend
- https://art-gallery-backend.herokuapp.com<br>
The redirect link to the heroku backend will display a 403 error since a user account must be created by the client in order to access application functionalities. In order to do so, follow the create user instructions in the [endpoint manual](https://github.com/McGill-ECSE321-Fall2020/project-group-06/wiki/Endpoint-Wiki). 

#### Web Frontend
- (To be added in later deliverables...)

## Deliverable 1
### Main tasks
- Requirement model: Functional & Non functional Requirements, Use cases, Use case specification
- Domain model
- Database design
- Test cases for persistence layer

### Documents
- Requirement model
  - [System Requirements](https://github.com/McGill-ECSE321-Fall2020/project-group-06/wiki/System-Requirements)
  - [Use Cases](https://github.com/McGill-ECSE321-Fall2020/project-group-06/wiki/5-Use-Case-Specifications)
  - [Use Case Diagram](https://github.com/McGill-ECSE321-Fall2020/project-group-06/wiki/Use-Case-Diagram)
  
- Domain model
  - [Class Diagram](https://github.com/McGill-ECSE321-Fall2020/project-group-06/wiki/Domain-Model)

- Report
  - [Deliverable 1 Project Report](https://github.com/McGill-ECSE321-Fall2020/project-group-06/wiki/Deliverable-1-Report)

- Minutes
  - [Meeting Minutes 1.1](https://github.com/McGill-ECSE321-Fall2020/project-group-06/wiki/Meeting-Minutes-1.1)
  - [Meeting Minutes 1.2](https://github.com/McGill-ECSE321-Fall2020/project-group-06/wiki/Meeting-Minutes-1.2)
  - [Meeting Minutes 1.3](https://github.com/McGill-ECSE321-Fall2020/project-group-06/wiki/Meeting-Minutes-1.3)
  - [Meeting Minutes 1.4](https://github.com/McGill-ECSE321-Fall2020/project-group-06/wiki/Meeting-Minutes-1.4)

### Table
#### Overview Table
**Name** | **Total Working Hours** | **Total Working Hours in Meetings** | Requirements Model | Domain Model | Persistence Layer | Testing of Persistence Layer | Build System and Continuous Integration | Documentation
-------- | ----------------------- | -------- | ------------------ | ------------ | ----------------- | ---------------------------- | ---------------------------------------- | -------------
Noah Chamberland | 12 | 10 | 2 | 1 | 2 | 3 | 1 | 3 
Justin Legrand | 12 | 10 | 2 | 3 | 2 | 2 | 1 | 2 
Olivier Normandin | 12 | 10 | 3 | 1 | 1 | 3 | 1 | 3 
André - Walter Panzini | 12 | 10 | 3 | 2 | 1 | 2 | 1 | 3 
Sen Wang | 12 | 10 | 3 | 1 | 3 | 2 | 2 | 1 

## Deliverable 2
### Main Tasks
- Backend Implementation and Evolution of Persistence Layer
- Software Quality Assurance Plan
- Unit Testing of Backend
- Integration Testing of Backend Services

### Documents
- Software Quality Assurance Plan
  - [Software Quality Assurance Plan and Report](https://github.com/McGill-ECSE321-Fall2020/project-group-06/wiki/Software-Quality-Assurance-Plan-and-Report)

- Endpoint Wiki
  - [Endpoint Wiki](https://github.com/McGill-ECSE321-Fall2020/project-group-06/wiki/Endpoint-Wiki)

- Report
  - [Deliverable 2 Project Report](https://github.com/McGill-ECSE321-Fall2020/project-group-06/wiki/Deliverable-2-Report)

- Minutes
  - [Meeting Minutes 2.1](https://github.com/McGill-ECSE321-Fall2020/project-group-06/wiki/Meeting-Minutes-2.1)
  - [Meeting Minutes 2.2](https://github.com/McGill-ECSE321-Fall2020/project-group-06/wiki/Meeting-Minutes-2.2)
  - [Meeting Minutes 2.3](https://github.com/McGill-ECSE321-Fall2020/project-group-06/wiki/Meeting-Minutes-2.3)
  - [Meeting Minutes 2.4](https://github.com/McGill-ECSE321-Fall2020/project-group-06/wiki/Meeting-Minutes-2.4)
  
### Table
#### Overview Table
**Name** | **Total Working Hours** | Service Methods | REST Methods | DTO methods | Software Quality Assurance Plan | Unit Testing of Backend | Integration Testing of Backend Services | Build System and Continuous Integration | Project Management
-------- | ----------------------- | --------------- | ------------ | ----------- | ------------------------------- | ----------------------- | ---------------------------------------- | ---------------------------------------- | -----------------
Noah Chamberland | 16 | x | x | x |  | x | x |  | 
Justin Legrand | 17 | x | x | x | x | x | x | x | x
Olivier Normandin | 16 | x | x |  | x | x | x |  | x
André - Walter Panzini | 16 | x | x |  | x | x | x |  | x
Sen Wang | 18 | x | x | x |  | x | x | x |


## Deliverable 3
### Main Tasks
- Architecture Modeling
- Web Frontend
- Integration of Web Frontend with Backend Services

### Documents

### Table
#### Overview Table
**Name** | **Total Working Hours** | Architecture Modeling | Web Frontend Components | Web Frontend Pages | Integration of Web Frontend with Backend | Project Management
-------- | ----------------------- | --------------------- | ----------------------- | ------------------ | ---------------------------------------- | ------------------
Noah Chamberland | 27 | x | x | x | x | x 
Justin Legrand | 27 | x | x | x | x | x 
Olivier Normandin | 27 | x | x | x | x | x 
André - Walter Panzini | 27 | x | x | x | x | x 
Sen Wang | 27 | x | x | x | x | x 

## Deliverable 4
### Main Tasks
(Mobile Frontend and Availability)
### Documents

### Table
#### Overview Table
**Name** | **Total Working Hours** | Meetings | Requirements Model | Domain Model | Persistence Layer | Testing of Persistence Layer | Build System and Continuous Integration | Documentation
-------- | ----------------------- | -------- | ------------------ | ------------ | ----------------- | ---------------------------- | ---------------------------------------- | -------------
Noah Chamberland | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 
Justin Legrand | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 
Olivier Normandin | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 
André - Walter Panzini | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 
Sen Wang | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 
To be continued...
