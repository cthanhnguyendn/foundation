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
    authority varchar(255) not null
        primary key
);

create table user
(
    id          bigint auto_increment
        primary key,
    created_at  datetime(6)  null,
    enabled     bit          not null,
    full_name   varchar(255) null,
    modified_at datetime(6)  null,
    password    varchar(255) null,
    username    varchar(255) null,
    email varchar(255) null unique
);

create table role_users
(
    role_authority varchar(255) not null,
    users_id       bigint       not null,
    primary key (role_authority, users_id),
    constraint FKafxikhmb9s897d7rqamkb3vtv
        foreign key (role_authority) references role (authority),
    constraint FKipeyaf3dve9njdrl1t23ndidv
        foreign key (users_id) references user (id)
);

create table user_authorities
(
    user_id               bigint       not null,
    authorities_authority varchar(255) not null,
    constraint FK3yqhiip97xf8rrlk3g57m1yl
        foreign key (authorities_authority) references role (authority),
    constraint FKmj13d0mnuj4cd8b6htotbf9mm
        foreign key (user_id) references user (id)
);

