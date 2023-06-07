create table Developers (
	id serial primary key,	
	name varchar(100)	
);

create table Apps (
	id serial primary key,
	name varchar(100)	
);

create table Developers_Apps (
	id serial primary key,
	developer_id integer references Developers(id),
	app_id integer references Apps(id)
);