create database lesson_two;

create table type (
	id serial primary key,
	name varchar(248)
);

create table product (
	id serial primary key,
	name varchar(248),
	type_id int references type(id),
	expired_date timestamp DEFAULT current_timestamp,
	price int
);

insert into type(name) values
('cheese'),
('meat'),
('fish'),
('vegetables');


insert into product(name, type_id, expired_date, price) values
('pork', 2,'2018-08-23',260),
('beef',2,'2018-08-27',360),
('soft cheese',1,'2018-08-29',80),
('hard cheese',1,'2018-09-01',200),
('Parmesan',1,'2018-09-02',300),
('Roquefort',1,'2018-09-03',310);

/*Написать запрос получение всех продуктов с типом "СЫР"*/
select * from product as p
inner join type as t on t.id = p.type_id where t.name = 'cheese';


insert into type(name) values ('milk');

insert into product(name, type_id, expired_date, price) values
('vanilla ice-cream', 5,'2018-08-26',110),
('raspberry ice-cream',5,'2018-08-21',180),
('mint ice-cream',5,'2018-08-20',90);

/*Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"*/
select * from product as p
where p.name like '%ice-cream%';

/*Написать запрос, который выводит все продукты,
срок годности которых заканчивается в следующем месяце*/
select * from product as p
where p.expired_date BETWEEN '2018-09-01' AND '2018-09-30';

/*Написать запрос, который вывод самый дорогой продукт.*/
select * from product  as p where p.price  = (select max(price) from product);

/*Написать запрос, который выводит количество всех продуктов определенного типа*/
select  COUNT(p.name),t.name  from product as p
inner join type as t on t.id = p.type_id  GROUP BY t.name;


/* Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"*/

select * from product as p
inner join type as t on t.id = p.type_id where t.name = 'cheese' or t.name = 'milk';

/*Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук*/

select COUNT(p.name),t.name from product as p
inner join type as t on t.id = p.type_id  GROUP BY t.name HAVING COUNT(p.name) < 10;

/*Вывести все продукты и их тип.*/
select p.name,t.name from product as p
inner join type as t on t.id = p.type_id;