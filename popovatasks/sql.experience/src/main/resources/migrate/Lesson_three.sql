
create database lesson_three;

create table corps (
	id serial primary key,
	name varchar(200)
);

create table engine (
	id serial primary key,
	name varchar(200)
);
create table gearbox (
	id serial primary key,
	name varchar(200)
);

insert into corps(name) values ('round case'),
('square case'),
('long body'),
('short body');

insert into engine(name) values ('diesel'),
('petrol');

insert into gearbox(name) values ('automatic'),
('hand');

create table car (
	id serial primary key,
	name varchar(200),
	corps_id int references corps(id) not null,
	engine_id int references engine(id) not null,
	gearbox_id int references gearbox(id) not null
);

insert into car(name, corps_id, engine_id, gearbox_id) values ('Lada', 1, 1, 1),
('BMW x5', 3, 2, 2),
('Lada Granta', 1, 1, 1),
('Lada Priora', 2, 1, 1),
('Lada Calina', 1, 1, 1);

/* Вывести список всех машин и все привязанные к ним детали.
*/
select c.name, co.name, e.name, g.name from car as c left outer  join corps as co on c.corps_id = co.id
left outer  join engine as e on c.engine_id = e.id
left outer  join gearbox as g on c.gearbox_id = g.id;

/*Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.*/


/*кузова*/
select c.name, co.name, e.name, g.name from car as c right outer  join corps as co on c.corps_id = co.id
left outer  join engine as e on c.engine_id = e.id
left outer  join gearbox as g on c.gearbox_id = g.id
where c.id is null;

/*двигатели*/
select c.name, co.name, e.name, g.name from car as c left outer  join corps as co on c.corps_id = co.id
right outer  join engine as e on c.engine_id = e.id
left outer  join gearbox as g on c.gearbox_id = g.id
where c.id is null;


/*коробки передач*/


select c.name, co.name, e.name, g.name from car as c left outer  join corps as co on c.corps_id = co.id
left outer  join engine as e on c.engine_id = e.id
right outer  join gearbox as g on c.gearbox_id = g.id
where c.id is null;
