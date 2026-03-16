# PoliticsApp

## Simple microservice student project Spring Boot + Angular web app running in Docker.
### App developed for the needs of laboratories of the subject *Internet Services Architectures*

*There are 3 backend microservices:*
1. **Gateway** - microservice for http request route management
2. **PoliticsApp_Members** - microservice for political party members management
3. **PoliticsApp_PoliticalParies** - microservice for political paries management

*And frontend app*
* **PoliticsApp-AngularFrontend** - simple Angular frontend app

## How to run

Run by:
`docker compose up -d`
Stop by:
`docker compose down`

To open app go to your browser and type
`http://localhost:4200`