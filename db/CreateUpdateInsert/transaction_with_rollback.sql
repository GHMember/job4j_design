begin transaction;

insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 11, 64);

select * from products;

savepoint first_savepoint;

delete from products where id = 10;

select * from products;

rollback to first_savepoint;

select * from products;

insert into products (name, producer, count, price) VALUES ('product_5', 'producer_5', 12, 65);

select * from products;

rollback;

select * from products;

begin transaction;

insert into products (name, producer, count, price) VALUES ('product_5', 'producer_5', 12, 65);

select * from products;

commit;

select * from products;