create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(text, avg_age, discovery_date) values('jellyfish', 11000, '01.01.01');
insert into fauna(text, avg_age) values('unicorn', 20000);
insert into fauna(text, avg_age, discovery_date) values('pokemon', 1000000, '01.01.1996');

select * from fauna where name like '%fish%';
select * from fauna where  avg_age >= 1000 and avg_age <= 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '01.01.1950';