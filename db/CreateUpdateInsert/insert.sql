insert into roles(name) values('admin');
insert into roles(name) values('user');

insert into users(name, role_id) values('Petr', 1);
insert into users(name, role_id) values('Masha', 2);
insert into users(name, role_id) values('Sveta', 2);

insert into rules(name) values('create');
insert into rules(name) values('read');
insert into rules(name) values('delete');

insert into roles_rules(role_id, rule_id) values(1, 1);
insert into roles_rules(role_id, rule_id) values(1, 2);
insert into roles_rules(role_id, rule_id) values(1, 3);
insert into roles_rules(role_id, rule_id) values(2, 1);
insert into roles_rules(role_id, rule_id) values(2, 2);

insert into categories(name) values('normal');
insert into categories(name) values('urgent');

insert into states(name) values('pending');
insert into states(name) values('done');

insert into items(message, user_id, category_id, state_id) values('normal trouble1', 2, 1, 1);
insert into items(message, user_id, category_id, state_id) values('urgent trouble1', 2, 2, 1);
insert into items(message, user_id, category_id, state_id) values('urgent trouble2', 3, 2, 2);

insert into ataches(title, file_size, item_id) values('file1', 100, 1);
insert into ataches(title, file_size, item_id) values('file2', 50, 1);
insert into ataches(title, file_size, item_id) values('file3', 10, 2);
insert into ataches(title, file_size, item_id) values('file4', 20, 3);

insert into comments(comment, item_id) values('comment1', 1);
insert into comments(comment, item_id) values('comment2', 1);
insert into comments(comment, item_id) values('comment3', 2);
insert into comments(comment, item_id) values('comment4', 3);