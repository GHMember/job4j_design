create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace procedure delete_data(del_id integer)
language 'plpgsql'
as $$
    BEGIN
        delete from products
        where id = del_id;
    END
$$;

call delete_data(1);

create or replace function f_dalete_data(del_id integer)
returns void
language 'plpgsql'
as
$$
    begin
        if point > 3 THEN
            delete from products where id = del_id + 1;
        else
            delete from products where id = del_id;
        end if;
    end;
$$;

select f_dalete_data(4);