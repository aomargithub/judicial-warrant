ALTER TABLE REQUEST_TYPE RENAME COLUMN REQUEST_NUMBER_PREFIX TO REQUEST_SERIAL_PREFIX;

CREATE TABLE REQUEST_INTERNAL_STATUS 
(
  ID NUMBER(2, 0) NOT NULL 
, CODE VARCHAR2(50 BYTE) NOT NULL 
, ARABIC_NAME VARCHAR2(100 BYTE) NOT NULL 
, ENGLISH_NAME VARCHAR2(100 BYTE) NOT NULL 
, CONSTRAINT REQUEST_INTERNAL_STATUS_PK PRIMARY KEY 
  (
    ID 
  )
  ENABLE 
);

ALTER TABLE REQUEST_INTERNAL_STATUS
ADD CONSTRAINT REQUEST_INTERNAL_STATUS_UK1 UNIQUE 
(
  CODE 
)
ENABLE;

ALTER TABLE REQUEST 
ADD (CURRENT_INTERNAL_STATUS_ID NUMBER );

alter table REQUEST
  add constraint REQUEST_REQUEST_STATUS_FK foreign key (CURRENT_STATUS_ID)
  references REQUEST_STATUS (ID);
  
  ALTER TABLE REQUEST
ADD CONSTRAINT REQUEST_INTERNAL_STATUS_FK1 FOREIGN KEY
(
  CURRENT_INTERNAL_STATUS_ID 
)
REFERENCES REQUEST_INTERNAL_STATUS
(
  ID 
)
ENABLE;


ALTER TABLE REQUEST_HISTORY_LOG 
ADD (INTERNAL_STATUS_ID NUMBER );

ALTER TABLE REQUEST_HISTORY_LOG  
MODIFY (INTERNAL_STATUS_ID NULL);


alter table REQUEST_HISTORY_LOG
  add constraint RQST_HSTRY_RQST_STS_FK foreign key (REQUEST_STATUS_ID)
  references REQUEST_STATUS (ID);

  ALTER TABLE REQUEST_HISTORY_LOG
ADD CONSTRAINT RQST_HSTRY_RQST_INT_STS_FK FOREIGN KEY
(
  INTERNAL_STATUS_ID 
)
REFERENCES REQUEST_INTERNAL_STATUS
(
  ID 
)
ENABLE;


CREATE TABLE NATIONALITY 
(
  ID NUMBER(4) NOT NULL 
, ARABIC_NAME VARCHAR2(20) NOT NULL 
, ENGLISH_NAME VARCHAR2(20) NOT NULL 
, CODE VARCHAR2(3) NOT NULL 
, ISO NUMBER(3, 0) NOT NULL
, CONSTRAINT NATIONALITY_PK PRIMARY KEY 
  (
    ID 
  )
  ENABLE 
);

alter table NATIONALITY
  add constraint NATIONALITY_CODE unique (CODE);
alter table NATIONALITY
  add constraint NATIONALITY_ISO unique (ISO);

  
  CREATE TABLE REQUEST_SERIAL 
(
  ID NUMBER NOT NULL 
, LAST_NUMBER NUMBER(5) NOT NULL 
, YEAR NUMBER NOT NULL 
, VERSION NUMBER NOT NULL 
, REQUEST_TYPE_ID NUMBER(2, 0) NOT NULL
, CONSTRAINT REQUEST_SERIAL_PK PRIMARY KEY 
  (
    ID 
  )
  ENABLE 
);

ALTER TABLE REQUEST_SERIAL
ADD CONSTRAINT REQUEST_TYPE_FK1 FOREIGN KEY
(
  REQUEST_TYPE_ID 
)
REFERENCES REQUEST_TYPE
(
  ID 
)
ENABLE;


CREATE TABLE JWCD_REQUEST 
(
  ID NUMBER NOT NULL 
, JOB_TITLE VARCHAR2(300) NOT NULL 
);

ALTER TABLE JWCD_REQUEST
ADD CONSTRAINT JWCD_REQUEST_PK PRIMARY KEY 
(
  ID 
)
ENABLE;

ALTER TABLE JWCD_REQUEST
ADD CONSTRAINT JWCD_REQUEST_REQUEST_FK1 FOREIGN KEY
(
  ID 
)
REFERENCES REQUEST
(
  ID 
)
ENABLE;


CREATE SEQUENCE REQUEST_SERIAL_SEQ INCREMENT BY 1 start with 1 MAXVALUE 99999999999999999999 MINVALUE 1 CACHE 20;

CREATE TABLE ER_REQUEST 
(
  ID NUMBER NOT NULL 
, JWCD_REQUEST_ID NUMBER NOT NULL 
, CONSTRAINT ER_REQUEST_PK PRIMARY KEY 
  (
    ID 
  )
  ENABLE 
);

ALTER TABLE ER_REQUEST
ADD CONSTRAINT ER_REQUEST_JWCD_REQUEST_FK1 FOREIGN KEY
(
  JWCD_REQUEST_ID 
)
REFERENCES JWCD_REQUEST
(
  ID 
)
ENABLE;

ALTER TABLE ER_REQUEST
ADD CONSTRAINT ER_REQUEST_REQUEST_FK1 FOREIGN KEY
(
  ID 
)
REFERENCES REQUEST
(
  ID 
)
ENABLE;

ALTER TABLE CANDIDATE  
MODIFY (CURRENT_STATUS_ID NULL);

ALTER TABLE CANDIDATE_HISTORY_LOG  
MODIFY (CANDIDATE_STATUS_ID NULL);


ALTER TABLE APP_ROLE
  ADD LDAP_SECURITY_GROUP varchar2(50);
  
  
   CREATE TABLE APP_USER_CREDENTIALS 
   (	PASSWORD VARCHAR2(50 BYTE), 
	ID NUMBER(20,0) NOT NULL ENABLE, 
	 CONSTRAINT APP_USER_CREDENTIALS_PK PRIMARY KEY (ID)
   );
   
  alter table APP_USER_CREDENTIALS
  add constraint USER_credientials_FK foreign key (ID)
  references APP_USER (ID);
  
  
  
  create table APP_USER_Type
(
  ID           NUMBER(2) not null,
  USER_TYPE         VARCHAR2(50) not null
 
);

alter table APP_USER_Type
  add constraint APP_USER_Type_PK primary key (ID);
  
create sequence APP_USER_CREDS_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

ALTER TABLE APP_USER 
ADD (USER_TYPE_ID NUMBER(2) );

ALTER TABLE APP_ROLE
  ADD LDAP_SECURITY_GROUP varchar2(50);
  
ALTER TABLE APP_USER_Type
rename USER_TYPE  to CODE ;


ALTER TABLE JWCD_REQUEST
  RENAME TO CAPACITY_DELEGATION;
  
  -- Rename indexes 
alter index JWCD_REQUEST_PK rename to CAPACITY_DELEGATION_PK;
-- Rename primary, unique and foreign key constraints 
alter table CAPACITY_DELEGATION
 rename constraint JWCD_REQUEST_PK to CAPACITY_DELEGATION_PK;
alter table CAPACITY_DELEGATION
 rename constraint JWCD_REQUEST_REQUEST_FK to CAPACITY_DELEGATION_REQUEST_FK;
 
 
DROP SEQUENCE candidate_attachment_seq;
DROP SEQUENCE candidate_history_log_seq;
DROP SEQUENCE candidate_seq;

create sequence ENTITLED_HISTORY_LOG_SEQ;
create sequence ENTITLED_ATTACHMENT_SEQ;
create sequence ENTITLED_SEQ;

alter table candidate rename to entitled;
alter table CANDIDATE_ATTACHMENT rename to entitled_ATTACHMENT;
alter table CANDIDATE_HISTORY_LOG rename to entitled_HISTORY_LOG;
alter table CANDIDATE_STATUS rename to entitled_STATUS;
alter table ER_REQUEST rename to entitled_registration;


-- Add/modify columns 
alter table ENTITLED_ATTACHMENT rename column candidate_id to ENTITLED_ID;
-- Create/Recreate primary, unique and foreign key constraints 
alter table ENTITLED_ATTACHMENT
  drop constraint CAND_CANDIDATE_ATTACHMENT_FK;
alter table ENTITLED_ATTACHMENT
  add constraint ENT_ENT_ATTACh_FK foreign key (ENTITLED_ID)
  references ENTITLED (ID);
  
-- Add/modify columns 
alter table ENTITLED_HISTORY_LOG rename column candidate_id to ENTITLED_ID;
alter table ENTITLED_HISTORY_LOG rename column candidate_status_id to ENTITLED_STATUS_ID;
-- Create/Recreate primary, unique and foreign key constraints 
alter table ENTITLED_HISTORY_LOG
  drop constraint CAN_CANDIDATE_HISTORY_FK;
alter table ENTITLED_HISTORY_LOG
  add constraint ENT_ENT_HISTORY_FK foreign key (ENTITLED_ID)
  references ENTITLED (ID);
alter table ENTITLED_HISTORY_LOG
  drop constraint CAN_STS_CANDIDATE_HISTORY_FK;
alter table ENTITLED_HISTORY_LOG
  add constraint ENT_STS_ENT_HISTORY_FK foreign key (ENTITLED_STATUS_ID)
  references ENTITLED_STATUS (ID);

  
  -- Drop columns 
alter table ENTITLED drop column request_id;
-- Create/Recreate primary, unique and foreign key constraints 
alter table ENTITLED
  drop constraint RQST_CAN_FK;
alter table ENTITLED
  add constraint RQST_CAN_FK foreign key ()
  references REQUEST (ID);

  
alter table ENTITLED add entitled_registration_id number not null;
alter table ENTITLED
  add constraint ENT_REG_ENT foreign key (ENTITLED_REGISTRATION_ID)
  references entitled_registration (ID);

alter table APP_USER rename column user_type_id to TYPE_ID;

alter table CAPACITY_DELEGATION add version NUMBER(3) not null;
alter table APP_USER modify type_id not null;


alter table ATTACHMENT_TYPE rename column is_candidate_attachment to IS_ENTITLED_ATTACHMENT;

  