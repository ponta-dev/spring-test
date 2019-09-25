create table if not exists employee(
	employee_id int primary key,
	employee_name varchar(50),
	age int
);

create table if not exists m_user(
	user_id varchar(50) primary key,
	password varchar(100),
	user_name varchar(50),
	birthday date,
	age int,
	marriage boolean,
	role varchar(50)
);