# Tasks

## Task 1
* Create github project according to your domain
* Create basic spring project
* Push it to main branch.

See README.md for details 

## Task 2
* Create layer design (domain, repository, controllers, service)
* Add domain classes that model your domain
* Add empty appropriate classes to other layers
* Make new branch and push it to remote
* Make pull request

## Task 3
* Add 1 SQL  that contains CREATE TABLE sql queries. 
* There other ones that has exampls of INSERT, SELECT, UPDATE, DELETE queries

Useful links:
* Download PG SQL https://www.postgresql.org/download/windows/
* DOwnload PG Admin https://www.pgadmin.org/
* SQL tutorial https://www.w3schools.com/sql/

## Task 4
* Add implementation for appropriate repository
* Each repository should have CRUD (create, retrieve, update, delete) operations
* Add playground class with method marked `@EventListener(ApplicationReadyEvent.class)` that shows how each repository works

## Task 5
Add services implementation that should
* Add, update, delete, get by id entry
* When ever code tries to get single entry by id, it should check is resource exist by id. If no it should throw exception.
* Add a few validation. For example that some field cannot be negative

## Task 6
Add REST controllers that add, update, delete, get entries 
Useful links
* https://en.wikipedia.org/wiki/HTTP 
* https://en.wikipedia.org/wiki/HTTP_message_body
* https://en.wikipedia.org/wiki/List_of_HTTP_status_codes 
* https://dzone.com/articles/common-mistakes-in-rest-api-design

## Task 7
Cover service layer with tests. Cover knows cases.
Useful links:
* https://junit.org/junit5/
* https://site.mockito.org/ 
* [Mockito tutorial](https://www.baeldung.com/mockito-series)

## Task 8
Add postman collection to resource/postman folder
Useful links:
* https://learning.postman.com/docs/writing-scripts/script-references/test-examples/

## Task 9
Add sql migration system flyway. Add (or modify existing) script that cretes table
Useful links:
* https://www.baeldung.com/database-migrations-with-flyway
* [SQL idempotancy](https://medium.com/full-stack-architecture/idempotent-sql-ddl-ca354a1eee62)

## Task 10
Add spring data to project. Use JPA and hibernate repositories.
Useful links:
* [Spring data](https://spring.io/guides/gs/accessing-data-jpa/)
* [Learn JPA & Hibernate](https://www.baeldung.com/learn-jpa-hibernate)

## Task 11 
Add security to your project. There should be 3 authorities: DEFAULT, EDITOR, ADMIN. 
* DEFAULT can use only GET requests. 
* EDITOR only post, put and delete. 
* ADMIN can do any requests
Useful links:
* https://docs.spring.io/spring-security/reference/index.html

## Task 12
Add audit to your project. There should be added 4 fields to domain and appriate SQL tables
* Created by - username who added entry
* Modified by - username who modified entry
* Create at - the date when entry was added
* Modified at - the date when entry was changed
  Useful links:
* https://docs.spring.io/spring-data/jpa/reference/auditing.html

## Task 13
Add JMS listener that listen an event with message of ids for your child entries.
When ever application receives such message it should delete all existing child entries by that ids.

Useful links:
* [Download Artemis](https://activemq.apache.org/components/artemis/download/)
* https://spring.io/guides/gs/messaging-jms/
