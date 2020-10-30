# gym_equipment

## About
E-shop selling gym equipment created using the Spring MVC framework

# Build Info

### Maven project | dynamic web module 4 | JPA

## Targeted runtime enviroment
- Tomcat v9

## Requirements
- JDK 1.8+
- Maven 3.8+

## dependencies
- spring-webmvc
- jstl-api
- jsp-api
- commons-logging
- mysql-connector-java
- hibernate-core

## Setup the MySQL Database (DB name: gym_equipment, User: gym_equipment_user, Password: gym_equipment_password)
- Localhost mysql server is set to localhost:3306.
- Login to MySQL command line
- Create the database and a new controller account for it

` create database gym_equipment; `

` create user 'gym_equipment_user'@'%' identified by 'gym_equipment_password'; `

- Give user the necessary privileges

` grant all on gym_equipment.* to 'gym_equipment_user'@'%'; `

## Install locally
- Images upload folder initialized in path `C:\\users\\john\\Documents\\gym_equipment_uploads` changed it from com.john_deligiannis.gym_equipment.config.MVCconfig.java
- clone project into your documents
` git clone  `
- import maven project into your IDE
- mvn clean install
- start local server





