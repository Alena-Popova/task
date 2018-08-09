
create table role (
	id serial primary key,
	value varchar(2000)
);
create table client (
	id serial primary key,
	name varchar(2000),
	role_id int references role(id)
);

create table rules (
	id serial primary key,
	name varchar(2000)
);

create table rules_roles (
	id serial primary key,
	rules_id int references rules(id),
	roles_id int references role(id)
);
create table item (
	id serial references client(id) not null primary key

);

create table comments  (
	id serial not null primary key,
	text varchar(2000),
	item_id int references item(id)

);

create table attachs   (
	id serial not null primary key,
	title varchar(2000),
	item_id int references item(id)
);

create table category    (
	id serial not null primary key,
	value varchar(2000)
);

alter table item add column category_id int references category(id);

create table state (
	id serial not null primary key,
	value varchar(2000)
);

alter table item add column state_id int references state(id);

insert into role (value) VALUES ('admin');
insert into role (value) VALUES ('user');

insert into client (name, role_id) VALUES ('Alex',1);
insert into client (name, role_id) VALUES ('Jon',1);
insert into client (name, role_id) VALUES ('Yugian',2);

insert into client (name, role_id) VALUES ('Alex',1),
('Victoria',1),
('DOnna',2);


insert into rules (name) VALUES ('Dont smoke'),
('Keep quiet'),
('Dont swear');

insert into rules_roles (rules_id, roles_id) VALUES (1, 1),
(2, 1),
(3, 1),
(3, 2);

insert into category (value) VALUES ('main'),
('medium'),
('low');
insert into state (value) VALUES ('active'),
('not active');

insert into item (category_id,state_id) VALUES (1,1),
(3,1),
(2,1),
(1,2),
(2,1),
(3,2);

insert into comments (text,item_id) VALUES ('Alex is a doctor',1),
('Jon is a',2),
('he lives in Moscow',2),
('Yugian is a cook',3),
('Alex is an engineer',4),
('he lives щт Bally',4),
('Victoria is a ticher',5),
('Donna is a student',6);

insert into attachs (title,item_id) VALUES ('photo',1),
('photo',2),
('inst',2),
('photo',3),
('photo',4),
('passport',4),
('photo',5),
('photo',6);
