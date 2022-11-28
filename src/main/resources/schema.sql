
create table pokemon
(
   id integer not null auto_increment,
   name varchar(255) not null,
   url varchar(255) not null,
   description varchar(255) not null,

   primary key(id)
);