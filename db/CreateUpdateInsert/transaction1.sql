select * from products

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 15, 32);
insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);

begin transaction isolation level serializable;
begin;

select sum(count) from products;

update products set count = 26 where name = 'product_1';

commit;