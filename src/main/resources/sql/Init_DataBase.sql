CREATE SCHEMA `teaching_assistance` ;

drop table if exists user_login;
CREATE TABLE user_login(
  id bigint auto_increment,
	login_name varchar(64) not null unique,
	name varchar(64) not null,
	password varchar(255) not null,
	register_date timestamp not null default 0,
	primary key (id)
)