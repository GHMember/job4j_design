create table users (
    id serial primary key,
    name varchar(100),
    role_id integer references roles(id)
);

create table roles (
    id serial primary key,
    name varchar(100)
);

create table rules (
    id serial primary key,
    name varchar(100)
);

create table roles_rules (
    id serial primary key,
    role_id integer references roles(id),
    rule_id integer references rules(id)
);

create table comments (
    id serial primary key,
    comment text,
    item_id integer references items(id)
);

create table attachs (
    id serial primary key,
    title varchar(100),
    file_size integer,
    item_id integer references items(id)
);

create table categories (
    id serial primary key,
    name varchar(100)
);

create table states (
    id serial primary key,
    name varchar(100)
);

create table items (
    id serial primary key,
    message text
    user_id integer references users(id),
    category_id integer references categories(id),
    state_id integer references states(id)
);
