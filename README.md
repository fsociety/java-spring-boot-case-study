# Java Spring Boot Todo App

--------
## Installation

### Backend
Before we start make sure that **docker** is installed in your system.

to install postgresql with docker
```bash
  docker compose up -d db
```

after the installation is complete, run these commands to create a database

```bash
  docker compose exec db bash
```
in shell run this
```bash
  psql -U springcase
```

then create database
```bash
  CREATE DATABASE todo;
```

Finally, open this project with "IntelliJ IDEA"

-----
*if you want to get postman collection click this button*

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/14338677-e6289786-3f11-44af-a170-30ace80785c3?action=collection%2Ffork&collection-url=entityId%3D14338677-e6289786-3f11-44af-a170-30ace80785c3%26entityType%3Dcollection%26workspaceId%3Ded128ba6-2cb9-40ef-925b-9fe50c14e4bf)