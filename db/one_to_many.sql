create table Bank_accaunts (
	id serial primary key,
	number integer,
	type varchar(100)	
);

create table Persons (
	id serial primary key,
	name varchar(100),
	account_id integer references Bank_accaunts(id) 
);