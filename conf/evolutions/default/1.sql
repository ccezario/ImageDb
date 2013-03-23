# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table category (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_category primary key (id))
;

create table child_image (
  id                        bigint not null,
  image_id                  bigint,
  resolution                varchar(255),
  link                      varchar(255),
  constraint pk_child_image primary key (id))
;

create table image (
  id                        bigint not null,
  name                      varchar(255),
  orientation               integer,
  image_type                integer,
  is_editorial              boolean,
  is_populated              boolean,
  use_term                  varchar(255),
  val_date                  timestamp,
  tag                       varchar(255),
  constraint ck_image_orientation check (orientation in (0,1)),
  constraint ck_image_image_type check (image_type in (0,1,2)),
  constraint pk_image primary key (id))
;

create table segment (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_segment primary key (id))
;

create table user (
  login                     varchar(255) not null,
  password                  varchar(255),
  email                     varchar(255),
  is_active                 boolean,
  is_approved               boolean,
  role                      integer,
  constraint ck_user_role check (role in (0,1,2)),
  constraint pk_user primary key (login))
;


create table image_category (
  image_id                       bigint not null,
  category_id                    bigint not null,
  constraint pk_image_category primary key (image_id, category_id))
;

create table image_segment (
  image_id                       bigint not null,
  segment_id                     bigint not null,
  constraint pk_image_segment primary key (image_id, segment_id))
;
create sequence category_seq;

create sequence child_image_seq;

create sequence image_seq;

create sequence segment_seq;

create sequence user_seq;

alter table child_image add constraint fk_child_image_image_1 foreign key (image_id) references image (id) on delete restrict on update restrict;
create index ix_child_image_image_1 on child_image (image_id);



alter table image_category add constraint fk_image_category_image_01 foreign key (image_id) references image (id) on delete restrict on update restrict;

alter table image_category add constraint fk_image_category_category_02 foreign key (category_id) references category (id) on delete restrict on update restrict;

alter table image_segment add constraint fk_image_segment_image_01 foreign key (image_id) references image (id) on delete restrict on update restrict;

alter table image_segment add constraint fk_image_segment_segment_02 foreign key (segment_id) references segment (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists category;

drop table if exists child_image;

drop table if exists image;

drop table if exists image_category;

drop table if exists image_segment;

drop table if exists segment;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists category_seq;

drop sequence if exists child_image_seq;

drop sequence if exists image_seq;

drop sequence if exists segment_seq;

drop sequence if exists user_seq;

