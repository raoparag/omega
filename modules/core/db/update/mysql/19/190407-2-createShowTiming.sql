alter table OMEGA_SHOW_TIMING add constraint FK_OMEGA_SHOW_TIMING_SHOW foreign key (SHOW_ID) references OMEGA_SHOW(ID);
create index IDX_OMEGA_SHOW_TIMING_SHOW on OMEGA_SHOW_TIMING (SHOW_ID);
