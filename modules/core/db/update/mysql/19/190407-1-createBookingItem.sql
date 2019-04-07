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
    PAID_TICKETS integer,
    COMPS integer,
    PAYMENT_CATEGORY_ID varchar(32),
    DISCOUNT double precision,
    SISTIC_FEE double precision,
    SEAT_DETAILS varchar(255),
    BOOKING_ID varchar(32),
    --
    primary key (ID)
);