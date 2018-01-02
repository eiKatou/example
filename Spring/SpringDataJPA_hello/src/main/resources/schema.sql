drop table if exists customers;
create table customers (
    id int auto_increment primary key,
    first_name varchar(30),
    last_name varchar(30));
