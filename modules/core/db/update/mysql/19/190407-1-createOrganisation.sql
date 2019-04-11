create table OMEGA_ORGANISATION (
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
    ADDRESS1 varchar(255),
    ADDRESS2 varchar(255),
    CITY varchar(150),
    COUNTRY_ID varchar(32),
    POSTAL_CODE varchar(10),
    EMAIL varchar(100),
    PHONE varchar(50),
    NOTES varchar(255),
    INDUSTRY_ID varchar(32),
    --
    primary key (ID)
);