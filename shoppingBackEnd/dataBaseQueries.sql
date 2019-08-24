create table category(
	id IDENTITY,
	name VARCHAR(50),
	description varchar(255),
	image_uri varchar(50),
	is_active BOOLEAN ,
	
	constraint pk_category_id primary key (id)
);