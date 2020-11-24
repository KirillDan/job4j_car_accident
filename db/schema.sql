CREATE TABLE public.accident (
  id serial primary key,
  name varchar(200),
  text varchar(200),
  address varchar(200),
  accident_type_id serial,
  foreign key (accident_type_id) references accident_type(id)
);

CREATE TABLE public.accident_type (
	id serial primary key,
	name varchar(200)
);

CREATE TABLE public.rule (
	id serial primary key,
	name varchar(200)
);

CREATE TABLE public.accident_rule (
	accident_id serial not null,
	rule_id serial not null,
	primary key (accident_id, rule_id),
	foreign key (accident_id) references accident(id),
	foreign key (rule_id) references rule(id)
);

