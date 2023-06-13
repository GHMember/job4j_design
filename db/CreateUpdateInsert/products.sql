create table type(
    id serial primary key,
    name text
);

create table product(
    id serial primary key,
    name text,
    type_id integer references type(id),
    expired_date text,
    price numeric(5, 2)
);

insert into type(name) values ('Сыр');
insert into type(name) values ('Молоко');
insert into type(name) values ('Хлеб');

insert into product(name, type_id, expired_date, price) values ('Гауда', 1, '2023.08.01', 100.10);
insert into product(name, type_id, expired_date, price) values ('Эдам', 1, '2023.08.01', 110.20);
insert into product(name, type_id, expired_date, price) values ('Чечил', 1, 'expired', 80.10);
insert into product(name, type_id, expired_date, price) values ('Жирное молоко', 2, 'expired', 50.10);
insert into product(name, type_id, expired_date, price) values ('Нежирное молоко', 2, '2023.08.01', 40.10);
insert into product(name, type_id, expired_date, price) values ('Обезжиренное молоко', 2, '2023.08.01', 30.10);
insert into product(name, type_id, expired_date, price) values ('Батон', 3, '2023.08.01', 30.10);
insert into product(name, type_id, expired_date, price) values ('Ржаной', 3, '2023.08.01', 25.10);
insert into product(name, type_id, expired_date, price) values ('Лепешка с сыром', 3, 'expired', 25.10);
insert into product(name, type_id, expired_date, price) values ('Бри', 1, '2023.08.02', 110.20);

select p.name as product_name, t.name as product_type from product p join type t on p.type_id = t.id
where t.name='Сыр';

select * from product where name like '%мороженое%';

select * from product where expired_date = 'expired';

select name, price from product where price = (select max(price) from product);

select t.name as product_type, count(p.id) as product_count from type t join product p on t.id = p.type_id
group by t.name;

select p.name as product_name, t.name as product_type from product p join type t on p.type_id = t.id
where t.name='Сыр' or t.name='Молоко';

select t.name as product_type, count(p.id) as product_count from type t join product p on t.id = p.type_id
group by t.name having count(p.id) < 10;