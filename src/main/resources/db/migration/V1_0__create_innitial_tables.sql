create table author
(
    id          bigint       not null
        primary key,
    about       varchar(255) null,
    created_at  datetime(6)  null,
    creator_id  bigint       null,
    full_name   varchar(255) null,
    modified_at datetime(6)  null,
    modifier_id bigint       null,
    nationality varchar(255) null
);

create table book
(
    id           bigint auto_increment
        primary key,
    about        varchar(255) null,
    created_at   datetime(6)  null,
    creator_id   bigint       null,
    hardcover    int          not null,
    isbn10       varchar(255) null,
    isbn13       varchar(255) null,
    language     varchar(255) null,
    modified_at  datetime(6)  null,
    modifier_id  bigint       null,
    publish_date date         null,
    publisher    varchar(255) null,
    title        varchar(255) null
);

create table role
(
    role varchar(255) not null
        primary key
);

create table user
(
    id          bigint auto_increment
        primary key,
    created_at  datetime(6)  null,
    email       varchar(255) null,
    enabled     bit          not null,
    full_name   varchar(255) null,
    modified_at datetime(6)  null,
    password    varchar(255) null,
    username    varchar(255) null,
    constraint UK_ob8kqyqqgmefl0aco34akdtpe
        unique (email)
);

create table users_roles
(
    user_id bigint       not null,
    role_id varchar(255) not null,
    primary key (user_id, role_id),
    constraint FKgd3iendaoyh04b95ykqise6qh
        foreign key (user_id) references user (id),
    constraint FKt4v0rrweyk393bdgt107vdx0x
        foreign key (role_id) references role (role)
);

