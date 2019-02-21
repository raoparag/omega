create table OMEGA_TICKET_CATEGORY (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CATEGORY_NAME varchar(50),
    CAPACITY integer,
    PRICE double precision,
    SHOW_ID varchar(36),
    --
    primary key (ID)
);
