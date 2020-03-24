## Network

1. `docker network create mynet`

### start DB
1. `docker run --name pg -e POSTGRES_PASSWORD=postgres --net mynet -p 5432:5432 -d postgres`
2. `docker exec -it pg bash`
3. `psql -U postgres`
4. `create database test;`
5. `\c test`
6. `create table transactions (id int primary key not null, amount int not null);`
7. `insert into transactions(id, amount) values (1, 100);`


### Compose Kafka and Consul
1. `docker-compose up`

### App
1. `docker run -p 50051:50051 --net mynet --name app nakedgraalgrpc` 


### Local agent build
1. `$JAVA_HOME/bin/java -agentlib:native-image-agent=config-output-dir=/home/pavel/image_stuff -cp build/libs/nakedgraalgrpc-0.1-all.jar codesample.kotlin.graalvm.Application`