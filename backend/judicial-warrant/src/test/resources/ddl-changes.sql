ALTER TABLE REQUEST_HISTORY_LOG  
MODIFY (INTERNAL_STATUS_ID NULL);

ALTER TABLE APP_ROLE
  ADD LDAP_SECURITY_GROUP varchar2(50);
  
  alter table APP_USER
  add constraint USER_credientials_FK foreign key (ID)
  references APP_USER_CREDENTIALS (ID);