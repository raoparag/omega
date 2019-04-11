create table OMEGA_SHOW (
    ID varchar(32),
    VERSION integer not null,
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    DELETE_TS datetime(3),
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    CODE varchar(3),
    TYPE_ integer,
    START_DATE date,
    END_DATE date,
    SHOW_VENUE_ID varchar(32),
    STATUS integer,
    NOTES varchar(255),
    --
    primary key (ID)
);