create table departments(
    id serial primary key,
    name text
);

create table employees(
    id serial primary key,
    name text,
    dep_id integer references departments(id)
);

insert into departments(name) values ('Dep1');
insert into departments(name) values ('Dep2');
insert into departments(name) values ('Dep3');

insert into employees(name, dep_id) values ('employee1', 1);
insert into employees(name, dep_id) values ('employee2', 1);
insert into employees(name, dep_id) values ('employee3', 2);
insert into employees(name, dep_id) values ('employee4', 2);
insert into employees(name) values ('employee5');

select d.name from departments d left join employees e on d.id = e.dep_id where e.id is null;

select d.id, d.name, e.id, e.name, e.dep_id from departments d left join employees e on d.id = e.dep_id;
select d.id, d.name, e.id, e.name, e.dep_id from employees e right join departments d on e.dep_id = d.id;

select * from departments d full join employees e on d.id = e.dep_id;

create table teens(
    id serial primary key,
    name text,
    gender text
);

insert into teens(name, gender) values ('Петр', 'мужской');
insert into teens(name, gender) values ('Мария', 'женский');
insert into teens(name, gender) values ('Василий', 'мужской');
insert into teens(name, gender) values ('Светлана', 'женский');

select * from teens as t1 cross join teens as t2 where t1.gender = 'мужской' and t2.gender = 'женский';