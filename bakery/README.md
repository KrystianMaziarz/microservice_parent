# Requirements

- Docker
- JVM 17

### Docker

* use command `docker run -d --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=postgres postgres:13.2` to run docker container


### Formatting

* use command 'mvn spotless:apply' to format
* use command 'mvn spotless:check' to check if everything is formatted