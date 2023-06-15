begin transaction isolation level serializable;
begin;

select sum(count) from products;

update products set count = 26 where name = 'product_2';

commit;