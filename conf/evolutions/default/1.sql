# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table cart (
  id                        bigint not null,
  constraint pk_cart primary key (id))
;

create table cart_item (
  id                        bigint not null,
  cart_id                   bigint,
  drink_id                  bigint,
  pizza_id                  bigint,
  quantity                  integer,
  constraint pk_cart_item primary key (id))
;

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

create table order_item (
  id                        bigint not null,
  pizza_id                  bigint,
  drink_id                  bigint,
  order_id                  bigint,
  constraint pk_order_item primary key (id))
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
create sequence cart_seq;

create sequence cart_item_seq;

create sequence desk_seq;

create sequence drinks_seq;

create sequence invoice_seq;

create sequence orders_seq;

create sequence order_item_seq;

create sequence pizza_seq;

create sequence toppings_seq;

create sequence users_seq;

alter table cart_item add constraint fk_cart_item_cart_1 foreign key (cart_id) references cart (id);
create index ix_cart_item_cart_1 on cart_item (cart_id);
alter table cart_item add constraint fk_cart_item_drink_2 foreign key (drink_id) references drinks (id);
create index ix_cart_item_drink_2 on cart_item (drink_id);
alter table cart_item add constraint fk_cart_item_pizza_3 foreign key (pizza_id) references pizza (id);
create index ix_cart_item_pizza_3 on cart_item (pizza_id);
alter table drinks add constraint fk_drinks_order_4 foreign key (order_id) references orders (id);
create index ix_drinks_order_4 on drinks (order_id);
alter table invoice add constraint fk_invoice_desk_5 foreign key (desk_id) references desk (id);
create index ix_invoice_desk_5 on invoice (desk_id);
alter table invoice add constraint fk_invoice_order_6 foreign key (order_id) references orders (id);
create index ix_invoice_order_6 on invoice (order_id);
alter table order_item add constraint fk_order_item_pizza_7 foreign key (pizza_id) references pizza (id);
create index ix_order_item_pizza_7 on order_item (pizza_id);
alter table order_item add constraint fk_order_item_drink_8 foreign key (drink_id) references drinks (id);
create index ix_order_item_drink_8 on order_item (drink_id);
alter table order_item add constraint fk_order_item_order_9 foreign key (order_id) references orders (id);
create index ix_order_item_order_9 on order_item (order_id);
alter table deskCode add constraint fk_deskCode_order_10 foreign key (order_id) references orders (id);
create index ix_deskCode_order_10 on deskCode (order_id);
alter table pizza add constraint fk_pizza_order_11 foreign key (order_id) references orders (id);
create index ix_pizza_order_11 on pizza (order_id);



alter table desk_deskCode add constraint fk_desk_deskCode_desk_01 foreign key (desk_id) references desk (id);

alter table desk_deskCode add constraint fk_desk_deskCode_deskCode_02 foreign key () references deskCode ();

alter table pizza_toppings add constraint fk_pizza_toppings_pizza_01 foreign key (pizza_id) references pizza (id);

alter table pizza_toppings add constraint fk_pizza_toppings_toppings_02 foreign key (toppings_id) references toppings (id);

# --- !Downs

drop table if exists cart cascade;

drop table if exists cart_item cascade;

drop table if exists desk cascade;

drop table if exists desk_deskCode cascade;

drop table if exists drinks cascade;

drop table if exists invoice cascade;

drop table if exists orders cascade;

drop table if exists order_item cascade;

drop table if exists deskCode cascade;

drop table if exists pizza cascade;

drop table if exists pizza_toppings cascade;

drop table if exists toppings cascade;

drop table if exists users cascade;

drop sequence if exists cart_seq;

drop sequence if exists cart_item_seq;

drop sequence if exists desk_seq;

drop sequence if exists drinks_seq;

drop sequence if exists invoice_seq;

drop sequence if exists orders_seq;

drop sequence if exists order_item_seq;

drop sequence if exists pizza_seq;

drop sequence if exists toppings_seq;

drop sequence if exists users_seq;

