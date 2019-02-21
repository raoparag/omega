create table OMEGA_TICKET_CATEGORY (
    ID varchar(32),
    VERSION integer not null,
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    DELETE_TS datetime(3),
    DELETED_BY varchar(50),
    --
    CATEGORY_NAME varchar(50),
    CAPACITY integer,
    PRICE double precision,
    SHOW_ID varchar(32),
    --
    primary key (ID)
);
