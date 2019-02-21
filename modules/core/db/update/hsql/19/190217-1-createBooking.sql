create table OMEGA_BOOKING (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    ORGANISATION_ID varchar(36),
    SHOW_ID varchar(36),
    BOOKING_STATUS integer,
    TICKET_STATUS integer,
    TOTAL_QUANTITY integer,
    TOTAL_PRICE double precision,
    PAYMENT_METHOD integer,
    BOOKING_CONFIRMATION_NUMBER varchar(10),
    SRT_CONTACT_ID varchar(36),
    ORG_CONTACT_ID varchar(36),
    NOTES varchar(255),
    --
    primary key (ID)
);
