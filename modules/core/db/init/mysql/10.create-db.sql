-- begin OMEGA_SHOW_VENUE
create table OMEGA_SHOW_VENUE (
    ID varchar(32),
    VERSION integer not null,
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    DELETE_TS datetime(3),
    DELETED_BY varchar(50),
    --
    NAME varchar(150),
    CAPACITY integer,
    --
    primary key (ID)
)^
-- end OMEGA_SHOW_VENUE
-- begin OMEGA_COUNTRY
create table OMEGA_COUNTRY (
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
    --
    primary key (ID)
)^
-- end OMEGA_COUNTRY
-- begin OMEGA_PAYMENT_CATEGORY
create table OMEGA_PAYMENT_CATEGORY (
    ID varchar(32),
    VERSION integer not null,
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    DELETE_TS datetime(3),
    DELETED_BY varchar(50),
    --
    NAME varchar(100),
    NOTES varchar(255),
    --
    primary key (ID)
)^
-- end OMEGA_PAYMENT_CATEGORY
-- begin OMEGA_CONTACT
create table OMEGA_CONTACT (
    ID varchar(32),
    VERSION integer not null,
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    DELETE_TS datetime(3),
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
    ORGANISATION_ID varchar(32),
    --
    primary key (ID)
)^
-- end OMEGA_CONTACT
-- begin OMEGA_ORGANISATION
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
)^
-- end OMEGA_ORGANISATION
-- begin OMEGA_INDUSTRY
create table OMEGA_INDUSTRY (
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
    --
    primary key (ID)
)^
-- end OMEGA_INDUSTRY
-- begin OMEGA_SHOW_TIMING
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
)^
-- end OMEGA_SHOW_TIMING
-- begin OMEGA_SHOW
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
)^
-- end OMEGA_SHOW
-- begin OMEGA_TICKET_CATEGORY
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
)^
-- end OMEGA_TICKET_CATEGORY
-- begin OMEGA_BOOKING_ITEM
create table OMEGA_BOOKING_ITEM (
    ID varchar(32),
    VERSION integer not null,
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    DELETE_TS datetime(3),
    DELETED_BY varchar(50),
    --
    SHOW_TIMING_ID varchar(32),
    TICKET_CATEGORY_ID varchar(32),
    QUANTITY integer,
    COMPS integer,
    PAYMENT_CATEGORY_ID varchar(32),
    DISCOUNT double precision,
    SISTIC_FEE double precision,
    SEAT_DETAILS varchar(255),
    BOOKING_ID varchar(32),
    --
    primary key (ID)
)^
-- end OMEGA_BOOKING_ITEM
-- begin OMEGA_BOOKING
create table OMEGA_BOOKING (
    ID varchar(32),
    VERSION integer not null,
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    DELETE_TS datetime(3),
    DELETED_BY varchar(50),
    --
    ORGANISATION_ID varchar(32),
    SHOW_ID varchar(32),
    BOOKING_STATUS integer,
    TICKET_STATUS integer,
    TOTAL_QUANTITY integer,
    TOTAL_PRICE double precision,
    PAYMENT_METHOD integer,
    BOOKING_CONFIRMATION_NUMBER varchar(10),
    SRT_CONTACT_ID varchar(32),
    ORG_CONTACT_ID varchar(32),
    NOTES varchar(255),
    --
    primary key (ID)
)^
-- end OMEGA_BOOKING
