create table car_bodies(
    id serial primary key,
    name text
);

create table car_engines(
    id serial primary key,
    name text
);

create table car_transmissions(
    id serial primary key,
    name text
);

create table cars(
    id serial primary key,
    name text,
    body_id integer references car_bodies(id),
    engine_id integer references car_engines(id),
    transmission_id integer references car_transmissions(id)
);

insert into car_bodies(name) values ('body1');
insert into car_bodies(name) values ('body2');
insert into car_bodies(name) values ('body3');
insert into car_bodies(name) values ('body4');

insert into car_engines(name) values ('engine1');
insert into car_engines(name) values ('engine2');
insert into car_engines(name) values ('engine3');

insert into car_transmissions(name) values ('transmission1');
insert into car_transmissions(name) values ('transmission2');
insert into car_transmissions(name) values ('transmission3');
insert into car_transmissions(name) values ('transmission4');

insert into cars(name, body_id, engine_id, transmission_id) values ('car1', 1, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id) values ('car2', 2, 2, 2);
insert into cars(name, body_id, engine_id, transmission_id) values ('car3', 1, 2, 3);
insert into cars(name, body_id, engine_id,) values ('car4', 1, 2);
insert into cars(name, engine_id, transmission_id) values ('car5', 2, 3);
insert into cars(name, body_id, transmission_id) values ('car6', 1, 1);

select c.id, c.name as car_name, cb.name as body_name, ce.name as engine_name, ct.name as transmission_name from cars as c
left join car_bodies as cb on c.body_id = cb.id
left join car_engines as ce on c.engine_id = ce.id
left join car_transmissions as ct on c.transmission_id = ct.id

select cb.name from car_bodies cb left join cars c on cb.id = c.body_id where c.body_id is null;

select ce.name from car_engines ce left join cars c on ce.id = c.engine_id where c.engine_id is null;

select ct.name from car_transmissions ct left join cars c on ct.id = c.transmission_id where c.transmission_id is null;
