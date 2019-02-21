create table OMEGA_BOOKING_ITEM (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    SHOW_TIMING_ID varchar(36),
    TICKET_CATEGORY_ID varchar(36),
    QUANTITY integer,
    PAYMENT_CATEGORY_ID varchar(36),
    DISCOUNT double precision,
    SISTIC_FEE double precision,
    SEAT_DETAILS varchar(255),
    BOOKING_ID varchar(36),
    --
    primary key (ID)
);
