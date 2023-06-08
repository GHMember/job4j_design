create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values('ApplePhone', 10000.0);
insert into devices(name, price) values('SamsungPhone', 8000.0);
insert into devices(name, price) values('LenovoPhone', 4500.0);

insert into people(name) values('Petr');
insert into people(name) values('Alex');
insert into people(name) values('Masha');

insert into devices_people(device_id, people_id) values(2, 1);
insert into devices_people(device_id, people_id) values(3, 1);
insert into devices_people(device_id, people_id) values(1, 3);
insert into devices_people(device_id, people_id) values(2, 2);
insert into devices_people(device_id, people_id) values(3, 2);

select avg(price) from devices;

select pp.name , avg(d.price) from devices as d join devices_people as dp
on d.id = dp.device_id join people as pp on pp.id = dp.people_id group by pp.name;

select pp.name, avg(d.price) from devices as d join devices_people as dp
on d.id = dp.device_id join people as pp on pp.id = dp.people_id group by pp.name having avg(d.price) > 5000;

