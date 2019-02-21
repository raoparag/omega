create table OMEGA_CONTACT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    FIRST_NAME varchar(100),
    LAST_NAME varchar(100),
    EMAIL varchar(150),
    PHONE varchar(50),
    JOB_TITLE varchar(150),
    BIRTHDATE date,
    PDPA integer,
    NOTES varchar(255),
    ORGANISATION_ID varchar(36),
    --
    primary key (ID)
);
