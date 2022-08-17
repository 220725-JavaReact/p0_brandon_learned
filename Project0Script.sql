---create a trainer table
--in table creation, naming convention is plural names for table, and snake_case

drop table if exists storefront_items cascade;
drop table if exists order_items cascade;
drop table if exists orders cascade;
drop table if exists storefronts cascade;
drop table if exists employees cascade;
drop table if exists products cascade;
drop table if exists customers cascade;


create table customers (
--column_name, data type, constraints
customer_id serial primary key, -- serial for auto increment
first_name varchar(50) not null,
last_name varchar(50) not null,
username varchar(20) unique not null,
pw varchar(20) not null,
email varchar(50) not null
);

create table products (
product_id serial primary key,
product_name varchar(50) not null,
price decimal(10, 2) not null,
description varchar(100) not null,
quality varchar(50) not null
);

create table employees (
employee_id serial primary key,
username varchar(20) not null, 
pw varchar(20) not null
);

create table storefronts (
storefront_id serial primary key,
storefront_name varchar(50),
address varchar(100)
);
--Everything after this relies on something above

-- DROP TABLE IF EXISTS table_name CASCADE/RESTRICT; 

create table orders (
order_id serial primary key,
customer_id int references customers(customer_id),
storefront_id int references storefronts(storefront_id)
);

create table order_items (
order_id int references orders(order_id),
product_id int references products(product_id),
quantity int
);

create table storefront_items(
storefront_id int references storefronts(storefront_id),
product_id int references products(product_id),
quantity int
);

--ADD PRODUCTS
insert into products(product_name, price, description, quality) values ('Rubber Duckie', 2.99, 'A basic rubber duckie', 'Mildly Duckie');
insert into products(product_name, price, description, quality) values ('Rubber Duckie', 4.99, 'A basic rubber duckie', 'Somewhat Duckie');
insert into products(product_name, price, description, quality) values ('Rubber Duckie', 8.99, 'A basic rubber duckie', 'Somewhat Duckie');
insert into products(product_name, price, description, quality) values ('Red Rubber Duckie', 3.99, 'A basic red rubber duckie', 'Mildly Duckie');
insert into products(product_name, price, description, quality) values ('Alien Rubber Duckie', 9.99, 'Keep this duckie away from cows', 'Quite Duckie');
insert into products(product_name, price, description, quality) values ('Daffy Duckie', 6.99, 'It''s duck season, and I say ''fire''!', 'Very Duckie');
insert into products(product_name, price, description, quality) values ('Donald Duckie', 9.95, 'A classic duckie that is very difficult to understand at times', 'Quite Duckie');
insert into products(product_name, price, description, quality) values ('Darkwing Duckie', 13.99, 'Drake Mallard Duckie dons a mask to fight crime. Let''s get dangerous!', 'Very Stealthy and Very Duckie');
insert into products(product_name, price, description, quality) values ('Mighty Duckie', 6.99, 'Ever seen a duck fight? No. Why? Because other animals are afraid', 'The Mightiest and Duckiest');
insert into products(product_name, price, description, quality) values ('Howard the Duckie', 20.99, 'No one laughs are the master of Quack-Fu!', 'Marvelously Duckie');
insert into products(product_name, price, description, quality) values ('Psyduckie', 24.99, 'This duckie is very prone to massive headaches', 'Very Duckie');
insert into products(product_name, price, description, quality) values ('Golduckie', 30.99, 'The evolved Psyduckie!', 'The Duckiest');
insert into products(product_name, price, description, quality) values ('Scrooge McDuckie', 44.99, 'A very greedy duckie', 'Very Greedy and Very Duckie');
insert into products(product_name, price, description, quality) values ('Duckie Lasek', 27.99, 'Skateboarding pro Duckie', 'Very Steezy and Very Duckie');
insert into products(product_name, price, description, quality) values ('Jon Duckett Duckie', 15.79, 'Comes with a free copy of HTML and CSS: Design and Build Websites (1st Edition) by: Jon Duckett', 'Super Duper Duckie');
insert into products(product_name, price, description, quality) values ('[LIMITED EDITION] Black Mage Donald Duckie', 79.99, 'ZETAFLAAAAARE!!!!!!', 'Rare Edition Duckie');

--ADD EMPLOYEES
insert into employees(username, pw) values ('prowler4', 'password');

--ADD STOREFRONTS
insert into storefronts(storefront_name, address) values ('Good Duckin'' Duckies', '100 Lakewood Drive, Duckvile, Duckington, 12345');
insert into storefronts(storefront_name, address) values ('Discount Duckies', '5732 Pond Road, Duck Town, Duck Nation, 11111');
insert into storefronts(storefront_name, address) values ('Duckie Dynasty', '63111 Ducktail Way, Duck Town, Duck Nation, 11111');

--ADD STOREFRONT ITEMS
insert into storefront_items(storefront_id, product_id, quantity) values (1, 2, 500);
insert into storefront_items(storefront_id, product_id, quantity) values (1, 6, 35);
insert into storefront_items(storefront_id, product_id, quantity) values (1, 9, 100);
insert into storefront_items(storefront_id, product_id, quantity) values (1, 11, 200);
insert into storefront_items(storefront_id, product_id, quantity) values (1, 12, 100);

insert into storefront_items(storefront_id, product_id, quantity) values (2, 1, 200);
insert into storefront_items(storefront_id, product_id, quantity) values (2, 4, 70);
insert into storefront_items(storefront_id, product_id, quantity) values (2, 5, 100);
insert into storefront_items(storefront_id, product_id, quantity) values (2, 7, 65);
insert into storefront_items(storefront_id, product_id, quantity) values (2, 8, 100);


insert into storefront_items(storefront_id, product_id, quantity) values (3, 3, 1000);
insert into storefront_items(storefront_id, product_id, quantity) values (3, 13, 100);
insert into storefront_items(storefront_id, product_id, quantity) values (3, 14, 100);
insert into storefront_items(storefront_id, product_id, quantity) values (3, 15, 60);
insert into storefront_items(storefront_id, product_id, quantity) values (3, 10, 100);
insert into storefront_items(storefront_id, product_id, quantity) values (3, 16, 20);

insert into customers (first_name, last_name, username, pw, email) values ('Brandon', 'Learned', 'blearned92', 'password', 'blearrned@gmail.com');
insert into customers (first_name, last_name, username, pw, email) values ('Mario', 'Mario', 'goombastomper', 'password', 'email@yahaaa.com');
insert into customers (first_name, last_name, username, pw, email) values ('Luigi', 'Mario', 'plumberpants', 'password', 'email@woohoo.com');
insert into customers (first_name, last_name, username, pw, email) values ('David', 'Bowie', 'ziggystardust1', 'password', 'email@email.com');
insert into customers (first_name, last_name, username, pw, email) values ('John', 'Davis', 'kornstock', 'password', 'email@email.com');
insert into customers (first_name, last_name, username, pw, email) values ('Cloud', 'Strife', 'bustersword', 'password', 'email@email.com');
insert into customers (first_name, last_name, username, pw, email) values ('Tifa', 'Lockhart', 'purplepain20', 'password', 'email@email.net');
insert into customers (first_name, last_name, username, pw, email) values ('Barret', 'Wallace', 'wreckingball', 'password', 'email@email.com');
insert into customers (first_name, last_name, username, pw, email) values ('Squall', 'Leonhart', 'renzuken', 'password', 'email@email.com');
insert into customers (first_name, last_name, username, pw, email) values ('Red', 'Thirteen', 'ratdogthing', 'password', 'email@email.com');
insert into customers (first_name, last_name, username, pw, email) values ('Aerith', 'Gainsborough', 'holymateria', 'password', 'email@email.net');
insert into customers (first_name, last_name, username, pw, email) values ('Vincent', 'Valentine', 'lucrecia', 'password', 'email@email.com');
insert into customers (first_name, last_name, username, pw, email) values ('Tom', 'Nook', 'yesyes', 'password', 'email@email.com');
insert into customers (first_name, last_name, username, pw, email) values ('Princess', 'Zelda', 'triforce', 'password', 'email@email.com');
insert into customers (first_name, last_name, username, pw, email) values ('Cid', 'Highwind', 'highwind11', 'password', 'email@email.net');
insert into customers (first_name, last_name, username, pw, email) values ('Kurt', 'Cobain', 'nevermind', 'password', 'email@email.com');
insert into customers (first_name, last_name, username, pw, email) values ('Sephiroth', 'Unknown', 'masamune', 'password', 'email@email.com');
insert into customers (first_name, last_name, username, pw, email) values ('Yuffie', 'Kisaragi', 'materiahunter', 'password', 'email@email.net');
insert into customers (first_name, last_name, username, pw, email) values ('Cait', 'Sith', 'reeve', 'password', 'email@email.com');
insert into customers (first_name, last_name, username, pw, email) values ('Sora', 'Sora', 'keyblade', 'password', 'email@email.net');
insert into customers (first_name, last_name, username, pw, email) values ('Randy', 'Savage', 'bonesaw', 'password', 'email@email.com');
