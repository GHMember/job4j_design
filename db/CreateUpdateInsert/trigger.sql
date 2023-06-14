create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    tax integer,
    price integer
);

create trigger statement_tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure statement_markup();

create or replace function statement_markup()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + tax
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger row_tax_trigger
    before insert
    on products
    for each row
    execute procedure row_markup();

create or replace function row_markup()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + tax
        where id = new.id;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create trigger row_history_trigger
    after insert
    on products
    for each row
    execute procedure history_log();

create or replace function history_log()
    returns trigger as
$$
    BEGIN
        insert into history_of_price(name, price, date) values(new.name, new.price, now());
		return new;
    END;
$$
LANGUAGE 'plpgsql';
