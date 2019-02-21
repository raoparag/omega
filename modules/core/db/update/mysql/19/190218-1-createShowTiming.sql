create table OMEGA_SHOW_TIMING (
    ID varchar(32),
    VERSION integer not null,
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    DELETE_TS datetime(3),
    DELETED_BY varchar(50),
    --
    SHOW_TIME datetime(3),
    NOTES varchar(255),
    SHOW_ID varchar(32),
    --
    primary key (ID)
);
