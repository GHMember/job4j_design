create table Husbands (
	id serial primary key,	
	name varchar(100)	
);

create table Wifes (
	id serial primary key,
	name varchar(100)	
);

create table Husbands_Wifes (
	id serial primary key,
	husband_id integer references Husbands(id) unique,
	wife_id integer references Wifes(id) unique
);