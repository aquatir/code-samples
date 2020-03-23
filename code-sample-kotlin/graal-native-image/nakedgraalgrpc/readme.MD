### db

1. `docker run --name pg -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres`
2. `docker exec -it pg bash`
3. `psql -U postgres`
4. `create database test;`
5. `\c test`
6. `create table transactions (id int primary key not null, amount int not null);`
7. `insert into transactions(id, amount) values (1, 100);`
