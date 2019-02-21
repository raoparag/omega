create table OMEGA_SHOW (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    CODE varchar(3),
    TYPE_ integer,
    START_DATE timestamp,
    END_DATE timestamp,
    SHOW_VENUE_ID varchar(36),
    STATUS integer,
    NOTES varchar(255),
    --
    primary key (ID)
);
