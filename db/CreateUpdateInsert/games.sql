create table genre (
    id serial primary key,
    name text;
);

create table videogames (
    id serial primary key,
    name text,
    genre_id integer references genre(id)
);

insert into genre(text) values ('rpg');
insert into genre(text) values ('tbs');
insert into genre(text) values ('rts');

insert into videogames(name, genre_id) values ('Dungeons and Dragons', 1);
insert into videogames(name, genre_id) values ('Heroes of Might and Magic', 2);
insert into videogames(name) values ('Total Wars');

select * from videogames as vg join genre as g on videogames.genre_id = genre.id;

select vg.name, g.name from videogames as vg join genre as g on videogames.genre_id = genre.id;

select vg.name as "Название игры", g.name as Жанр from videogames as vg join genre as g
on videogames.genre_id = genre.id;