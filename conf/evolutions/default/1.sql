# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table desk (
  id                        bigint not null,
  created                   timestamp,
  desk_number               bigint not null,
  desk_code                 bigint not null,
  desk_state                varchar(10),
  constraint ck_desk_desk_state check (desk_state in ('NEW','BOOKED','BEING_USED')),
  constraint pk_desk primary key (id))
;

create table drinks (
  id                        bigint not null,
  name                      varchar(255),
  description               varchar(255),
  price                     decimal(15,4),
  order_id                  bigint,
  constraint pk_drinks primary key (id))
;

create table invoice (
  id                        bigint not null,
  desk_id                   bigint,
  invoice_date              timestamp,
  name                      varchar(255),
  order_id                  bigint,
  constraint pk_invoice primary key (id))
;

create table orders (
  id                        bigint not null,
  order_status              varchar(3),
  constraint ck_orders_order_status check (order_status in ('NEW')),
  constraint pk_orders primary key (id))
;

create table deskCode (
  order_id                  bigint,
  code                      bigint,
  constraint uq_deskCode_code unique (code))
;

create table pizza (
  id                        bigint not null,
  name                      varchar(255),
  size                      bigint,
  visible                   boolean,
  price                     decimal(15,4),
  order_id                  bigint,
  constraint pk_pizza primary key (id))
;

create table toppings (
  id                        bigint not null,
  name                      varchar(255),
  price                     decimal(15,4),
  constraint pk_toppings primary key (id))
;

create table users (
  id                        bigint not null,
  email                     varchar,
  password                  varchar(255),
  name                      varchar,
  first_name                varchar,
  last_name                 varchar,
  role                      varchar(5),
  constraint ck_users_role check (role in ('ADMIN')),
  constraint uq_users_email unique (email),
  constraint pk_users primary key (id))
;


create table desk_deskCode (
  desk_id                        bigint not null,
  constraint pk_desk_deskCode primary key (desk_id))
;

create table pizza_toppings (
  pizza_id                       bigint not null,
  toppings_id                    bigint not null,
  constraint pk_pizza_toppings primary key (pizza_id, toppings_id))
;
create sequence desk_seq;

create sequence drinks_seq;

create sequence invoice_seq;

create sequence orders_seq;

create sequence pizza_seq;

create sequence toppings_seq;

create sequence users_seq;

alter table drinks add constraint fk_drinks_order_1 foreign key (order_id) references orders (id);
create index ix_drinks_order_1 on drinks (order_id);
alter table invoice add constraint fk_invoice_desk_2 foreign key (desk_id) references desk (id);
create index ix_invoice_desk_2 on invoice (desk_id);
alter table invoice add constraint fk_invoice_order_3 foreign key (order_id) references orders (id);
create index ix_invoice_order_3 on invoice (order_id);
alter table deskCode add constraint fk_deskCode_order_4 foreign key (order_id) references orders (id);
create index ix_deskCode_order_4 on deskCode (order_id);
alter table pizza add constraint fk_pizza_order_5 foreign key (order_id) references orders (id);
create index ix_pizza_order_5 on pizza (order_id);



alter table desk_deskCode add constraint fk_desk_deskCode_desk_01 foreign key (desk_id) references desk (id);

alter table desk_deskCode add constraint fk_desk_deskCode_deskCode_02 foreign key () references deskCode ();

alter table pizza_toppings add constraint fk_pizza_toppings_pizza_01 foreign key (pizza_id) references pizza (id);

alter table pizza_toppings add constraint fk_pizza_toppings_toppings_02 foreign key (toppings_id) references toppings (id);

# --- !Downs

drop table if exists desk cascade;

drop table if exists desk_deskCode cascade;

drop table if exists drinks cascade;

drop table if exists invoice cascade;

drop table if exists orders cascade;

drop table if exists deskCode cascade;

drop table if exists pizza cascade;

drop table if exists pizza_toppings cascade;

drop table if exists toppings cascade;

drop table if exists users cascade;

drop sequence if exists desk_seq;

drop sequence if exists drinks_seq;

drop sequence if exists invoice_seq;

drop sequence if exists orders_seq;

drop sequence if exists pizza_seq;

drop sequence if exists toppings_seq;

drop sequence if exists users_seq;

