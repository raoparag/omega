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
);
