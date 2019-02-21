create table OMEGA_ORGANISATION (
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
    ADDRESS1 varchar(255),
    ADDRESS2 varchar(255),
    CITY varchar(150),
    COUNTRY_ID varchar(36),
    POSTAL_CODE varchar(10),
    EMAIL varchar(100),
    PHONE varchar(50),
    NOTES varchar(255),
    INDUSTRY_ID varchar(36),
    --
    primary key (ID)
);
