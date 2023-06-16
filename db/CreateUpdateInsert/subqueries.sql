CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

insert into customers(first_name, last_name, age, country) values('Иван', 'Иванов', 18, 'Россия');
insert into customers(first_name, last_name, age, country) values('Петр', 'Петров', 19, 'Россия');
insert into customers(first_name, last_name, age, country) values('Мария', 'Иванова', 18, 'Россия');
insert into customers(first_name, last_name, age, country) values('Светлана', 'Петрова', 19, 'Россия');

select * from customers where age = (select min(age) from customers);

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into orders(amount, customer_id) values(10, 1);
insert into orders(amount, customer_id) values(20, 2);

select * from customers where id not in (select customer_id from orders);