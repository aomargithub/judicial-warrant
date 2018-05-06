
create table APP_ROLE
(
  id           NUMBER(2) not null,
  code         VARCHAR2(50) not null,
  arabic_name  VARCHAR2(100) not null,
  english_name VARCHAR2(100) not null,
  create_by    VARCHAR2(200) not null,
  create_date  TIMESTAMP(6) not null,
  is_active    NUMBER(1) not null,
  list_order   NUMBER(2) not null
);
alter table APP_ROLE
  add constraint ROLE_PK primary key (ID);


create table ORGANIZATION_UNIT
(
  id           NUMBER(4) not null,
  arabic_name  VARCHAR2(200) not null,
  english_name VARCHAR2(200) not null,
  version      NUMBER(3),
  create_by    VARCHAR2(200) not null,
  create_date  TIMESTAMP(6) not null,
  update_by    VARCHAR2(200),
  update_date  TIMESTAMP(6),
  is_active    NUMBER(1) not null,
  list_order   NUMBER(4)
);
alter table ORGANIZATION_UNIT
  add constraint ORGANIZATION_UNIT_PK primary key (ID);


create table APP_USER
(
  id                   NUMBER not null,
  login_name           VARCHAR2(50) not null,
  arabic_name          VARCHAR2(200) not null,
  english_name         VARCHAR2(200) not null,
  role_id              NUMBER(2) not null,
  mobile_number1       VARCHAR2(15) not null,
  mobile_number2       VARCHAR2(15) not null,
  email_address        VARCHAR2(100) not null,
  is_active            NUMBER(1) not null,
  create_date          TIMESTAMP(6) not null,
  create_by            VARCHAR2(200) not null,
  update_by            VARCHAR2(200),
  update_date          TIMESTAMP(6),
  version              NUMBER(3) not null,
  organization_unit_id NUMBER(4) not null,
  civil_id             NUMBER not null
);
alter table APP_USER
  add constraint USER_PK primary key (ID);
alter table APP_USER
  add constraint USER_CIVIL_ID_UN unique (CIVIL_ID);
alter table APP_USER
  add constraint USER_EMAIL_ADDRESS_UN unique (EMAIL_ADDRESS);
alter table APP_USER
  add constraint USER_LOGIN_NAME_UN unique (LOGIN_NAME);
alter table APP_USER
  add constraint USER_MOBILE_NUMBER1_UN unique (MOBILE_NUMBER1);
alter table APP_USER
  add constraint OU_USER_FK foreign key (ORGANIZATION_UNIT_ID)
  references ORGANIZATION_UNIT (ID);
alter table APP_USER
  add constraint ROLE_USER_FK foreign key (ROLE_ID)
  references APP_ROLE (ID);

create table ATTACHMENT_TYPE
(
  id                      NUMBER not null,
  version                 NUMBER(3) not null,
  create_by               VARCHAR2(200) not null,
  create_date             TIMESTAMP(6) not null,
  update_by               VARCHAR2(200),
  update_date             TIMESTAMP(6),
  arabic_name             VARCHAR2(200) not null,
  english_name            VARCHAR2(200) not null,
  is_active               NUMBER(1) not null,
  is_candidate_attachment NUMBER(1) not null,
  list_order              NUMBER(2),
  is_mandatory            NUMBER(1) not null
);
alter table ATTACHMENT_TYPE
  add constraint ATTACHMENT_TYPE_PK primary key (ID);

create table CANDIDATE_STATUS
(
  id           NUMBER(1) not null,
  code         VARCHAR2(50) not null,
  create_by    VARCHAR2(200) not null,
  create_date  TIMESTAMP(6) not null,
  english_name VARCHAR2(100) not null,
  arabic_name  VARCHAR2(100) not null
);
alter table CANDIDATE_STATUS
  add constraint CNDT_ENTITLEMENT_STATUS_PK primary key (ID);
alter table CANDIDATE_STATUS
  add constraint CNDT_ENTITLEMENT_STS_CODE_UN unique (CODE);


create table REQUEST_STATUS
(
  code         VARCHAR2(50) not null,
  english_name VARCHAR2(100) not null,
  arabic_name  VARCHAR2(100) not null,
  id           NUMBER(2) not null
);
alter table REQUEST_STATUS
  add constraint REQUEST_STATUS_PK primary key (ID);
alter table REQUEST_STATUS
  add constraint REQUEST_STATUS_CODE_UN unique (CODE);


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

  
  
create table REQUEST_TYPE
(
  create_by             VARCHAR2(200) not null,
  create_date           TIMESTAMP(6) not null,
  code                  VARCHAR2(50) not null,
  english_name          VARCHAR2(200) not null,
  is_active             NUMBER(1) not null,
  list_order            NUMBER(2) not null,
  arabic_name           VARCHAR2(200) not null,
  id                    NUMBER(2) not null,
  request_serial_prefix VARCHAR2(100) not null
);
alter table REQUEST_TYPE
  add constraint REQUEST_TYPE_PK primary key (ID);
alter table REQUEST_TYPE
  add constraint REQUEST_TYPE_CODE_UN unique (CODE);
alter table REQUEST_TYPE
  add constraint REQUEST_TYPE_DSPLY_ORDR_UN unique (LIST_ORDER);
  




create table REQUEST
(
  create_by            VARCHAR2(200) not null,
  create_date          TIMESTAMP(6) not null,
  update_by            VARCHAR2(200),
  update_date          TIMESTAMP(6),
  version              NUMBER(3) not null,
  id                   NUMBER not null,
  current_status_id    NUMBER(2) not null,
  CURRENT_INTERNAL_STATUS_ID NUMBER(2),
  type_id              NUMBER(2) not null,
  organization_unit_id NUMBER(4) not null,
  serial               VARCHAR2(200),
  JWCD_REQUEST_ID NUMBER
);
alter table REQUEST
  add constraint REQUEST_PK primary key (ID);
alter table REQUEST
  add constraint REQUEST_SERIAL_UN unique (SERIAL);
alter table REQUEST
  add constraint OU_REQ_FK foreign key (ORGANIZATION_UNIT_ID)
  references ORGANIZATION_UNIT (ID);
alter table REQUEST
  add constraint REQUEST_REQUEST_STATUS_FK foreign key (CURRENT_STATUS_ID)
  references REQUEST_STATUS (ID);
alter table REQUEST
  add constraint REQUEST_REQUEST_TYPE_FK foreign key (TYPE_ID)
  references REQUEST_TYPE (ID);
  
  ALTER TABLE REQUEST
ADD CONSTRAINT JWCD_REQUEST_FK1 FOREIGN KEY
(
  JWCD_REQUEST_ID 
)
REFERENCES JWCD_REQUEST
(
  ID 
)
ENABLE;

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



create table CANDIDATE
(
  id                   NUMBER not null,
  create_by            VARCHAR2(200) not null,
  create_date          TIMESTAMP(6) not null,
  update_by            VARCHAR2(200),
  update_date          TIMESTAMP(6),
  version              NUMBER(3) not null,
  organization_unit_id NUMBER(4) not null,
  english_name         VARCHAR2(200) not null,
  arabic_name          VARCHAR2(200) not null,
  civil_id             NUMBER not null,
  mobile_number1       VARCHAR2(15) not null,
  mobile_number2       VARCHAR2(15) not null,
  email_address        VARCHAR2(100) not null,
  request_id           NUMBER not null,
  current_status_id    NUMBER(1) not null
);
alter table CANDIDATE
  add constraint CANDIDATE_PK primary key (ID);
alter table CANDIDATE
  add constraint CAN_STS_CAN_FK foreign key (CURRENT_STATUS_ID)
  references CANDIDATE_STATUS (ID);
alter table CANDIDATE
  add constraint OU_CAN_FK foreign key (ORGANIZATION_UNIT_ID)
  references ORGANIZATION_UNIT (ID);
alter table CANDIDATE
  add constraint RQST_CAN_FK foreign key (REQUEST_ID)
  references REQUEST (ID);

create table CANDIDATE_ATTACHMENT
(
  id                 NUMBER not null,
  candidate_id       NUMBER not null,
  attachment_type_id NUMBER(2) not null,
  ucm_document_id    VARCHAR2(50) not null,
  create_by          VARCHAR2(200) not null,
  create_date        TIMESTAMP(6) not null,
  update_by          VARCHAR2(200),
  update_date        TIMESTAMP(6),
  version            NUMBER(3) not null
);
alter table CANDIDATE_ATTACHMENT
  add constraint CANDIDATE_ATTACHMENT_PK primary key (ID);
alter table CANDIDATE_ATTACHMENT
  add constraint ATT_TYP_CAND_ATTACHMENT_FK foreign key (ATTACHMENT_TYPE_ID)
  references ATTACHMENT_TYPE (ID);
alter table CANDIDATE_ATTACHMENT
  add constraint CAND_CANDIDATE_ATTACHMENT_FK foreign key (CANDIDATE_ID)
  references CANDIDATE (ID);

create table CANDIDATE_HISTORY_LOG
(
  id                  NUMBER not null,
  candidate_id        NUMBER not null,
  candidate_status_id NUMBER(1) not null,
  create_by           NUMBER not null,
  create_date         TIMESTAMP(6) not null,
  note                VARCHAR2(500)
);
alter table CANDIDATE_HISTORY_LOG
  add constraint CANDIDATE_HISTORY_PK primary key (ID);
alter table CANDIDATE_HISTORY_LOG
  add constraint APUSER_CAN_FK foreign key (CREATE_BY)
  references APP_USER (ID);
alter table CANDIDATE_HISTORY_LOG
  add constraint CAN_CANDIDATE_HISTORY_FK foreign key (CANDIDATE_ID)
  references CANDIDATE (ID);
alter table CANDIDATE_HISTORY_LOG
  add constraint CAN_STS_CANDIDATE_HISTORY_FK foreign key (CANDIDATE_STATUS_ID)
  references CANDIDATE_STATUS (ID);

  create table ERROR_LOG
(
  id          NUMBER,
  stack_trace CLOB,
  request_id  NUMBER,
  user_name   NVARCHAR2(500),
  create_date TIMESTAMP(6)
);


create table LOCALE
(
  id          NUMBER(1) not null,
  create_by   VARCHAR2(200) not null,
  create_date TIMESTAMP(6) not null,
  name        VARCHAR2(50) not null,
  code        VARCHAR2(50) not null,
  is_active   NUMBER(1) not null,
  list_order  NUMBER(1) not null
);
alter table LOCALE
  add constraint LOCALE_PK primary key (ID);
alter table LOCALE
  add constraint LOCALE_CODE_UN unique (CODE);

create table NOTIFICATION_CHANNEL
(
  id           NUMBER(1) not null,
  code         VARCHAR2(50) not null,
  create_by    VARCHAR2(200) not null,
  create_date  TIMESTAMP(6) not null,
  arabic_name  VARCHAR2(50) not null,
  is_active    NUMBER not null,
  english_name VARCHAR2(50) not null,
  list_order   NUMBER(1)
);
alter table NOTIFICATION_CHANNEL
  add constraint NOTIFICATION_CHANNEL_PK primary key (ID);
alter table NOTIFICATION_CHANNEL
  add constraint NOTIFICATION_CHANNEL_CODE_UN unique (CODE);

create table REQUEST_ATTACHMENT
(
  id                 NUMBER not null,
  request_id         NUMBER not null,
  attachment_type_id NUMBER(2) not null,
  ucm_document_id    VARCHAR2(50) not null,
  create_by          VARCHAR2(200) not null,
  create_date        TIMESTAMP(6) not null,
  update_by          VARCHAR2(200),
  update_date        TIMESTAMP(6),
  version            NUMBER(3) not null
);
alter table REQUEST_ATTACHMENT
  add constraint REQUEST_ATTACHMENT_PK primary key (ID);
alter table REQUEST_ATTACHMENT
  add constraint ATTCH_TYP_RQST_ATCH_FK foreign key (ATTACHMENT_TYPE_ID)
  references ATTACHMENT_TYPE (ID);
alter table REQUEST_ATTACHMENT
  add constraint RQST_REQUEST_ATTACHMENT_FK foreign key (REQUEST_ID)
  references REQUEST (ID);

create table REQUEST_HISTORY_LOG
(
  id                NUMBER not null,
  request_id        NUMBER not null,
  request_status_id NUMBER(2) not null,
  INTERNAL_STATUS_ID NUMBER(2) NOT NULL,
  create_by         NUMBER not null,
  create_date       TIMESTAMP(6) not null,
  note              VARCHAR2(500)
);
alter table REQUEST_HISTORY_LOG
  add constraint REQUEST_HISTORY_PK primary key (ID);
alter table REQUEST_HISTORY_LOG
  add constraint APUSER_REQ_FK foreign key (CREATE_BY)
  references APP_USER (ID);
alter table REQUEST_HISTORY_LOG
  add constraint REQUEST_HISTORY_REQUEST_FK foreign key (REQUEST_ID)
  references REQUEST (ID);
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
  
create table REQUEST_TYPE_ATTACHMENT_TYPE
(
  id                 NUMBER(4) not null,
  request_type_id    NUMBER(2) not null,
  attachment_type_id NUMBER(2) not null,
  create_by          VARCHAR2(200) not null,
  create_date        TIMESTAMP(6) not null,
  is_active          NUMBER(1) not null,
  list_order         NUMBER(4) not null,
  is_required        NUMBER(1) not null,
  update_by          VARCHAR2(200),
  update_date        TIMESTAMP(6),
  version            NUMBER(3) not null
);
alter table REQUEST_TYPE_ATTACHMENT_TYPE
  add constraint REQUEST_TYPE_ATTACHMENT_PK primary key (ID);
alter table REQUEST_TYPE_ATTACHMENT_TYPE
  add constraint REQUEST_TYPE_ATTACHMENT_UN unique (REQUEST_TYPE_ID, ATTACHMENT_TYPE_ID);
alter table REQUEST_TYPE_ATTACHMENT_TYPE
  add constraint ATCHMNT_TYP_RQST_TYP_ATMNT_FK foreign key (ATTACHMENT_TYPE_ID)
  references ATTACHMENT_TYPE (ID);
alter table REQUEST_TYPE_ATTACHMENT_TYPE
  add constraint RQST_TYP_RQST_TYP_ATCHMNT_FK foreign key (REQUEST_TYPE_ID)
  references REQUEST_TYPE (ID);

create table SPRING_SESSION
(
  session_id            CHAR(36) not null,
  creation_time         NUMBER(19) not null,
  last_access_time      NUMBER(19) not null,
  max_inactive_interval NUMBER(10) not null,
  principal_name        VARCHAR2(100 CHAR)
);
create index SPRING_SESSION_IX1 on SPRING_SESSION (LAST_ACCESS_TIME);
alter table SPRING_SESSION
  add constraint SPRING_SESSION_PK primary key (SESSION_ID);

create table SPRING_SESSION_ATTRIBUTES
(
  session_id      CHAR(36) not null,
  attribute_name  VARCHAR2(200 CHAR) not null,
  attribute_bytes BLOB not null
);
create index SPRING_SESSION_ATTRIBUTES_IX1 on SPRING_SESSION_ATTRIBUTES (SESSION_ID);
alter table SPRING_SESSION_ATTRIBUTES
  add constraint SPRING_SESSION_ATTRIBUTES_PK primary key (SESSION_ID, ATTRIBUTE_NAME);
alter table SPRING_SESSION_ATTRIBUTES
  add constraint SPRING_SESSION_ATTRIBUTES_FK foreign key (SESSION_ID)
  references SPRING_SESSION (SESSION_ID) on delete cascade;

  
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

  
CREATE TABLE JWCD_REQUEST 
(
  ID NUMBER NOT NULL 
, JOB_TITLE VARCHAR2(20) NOT NULL 
, REQUEST_ID NUMBER
, CONSTRAINT JOB_TITLE_REQUEST_PK PRIMARY KEY 
  (
    ID 
  )
  ENABLE 
);

ALTER TABLE JWCD_REQUEST
ADD CONSTRAINT JWCD_REQUEST_REQUEST_FK1 FOREIGN KEY
(
  REQUEST_ID 
)
REFERENCES REQUEST
(
  ID 
)
ENABLE;

  

CREATE TABLE REQUEST_SERIAL 
(
  ID NUMBER NOT NULL 
, LAST_NUMBER NUMBER NOT NULL 
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

  
create sequence APP_USER_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

create sequence ATTACHMENT_TYPE_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;


create sequence CANDIDATE_ATTACHMENT_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

create sequence CANDIDATE_HISTORY_LOG_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

create sequence CANDIDATE_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

create sequence ERROR_LOG_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 101
increment by 1
cache 20;

create sequence ORGANIZATION_UNIT_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 121
increment by 1
cache 20;

create sequence REQUEST_ATTACHMENT_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

create sequence REQUEST_HISTORY_LOG_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

create sequence REQUEST_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

create sequence RQUST_TYPE_ATTACHMENT_TYPE_SEQ
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;

CREATE SEQUENCE REQUEST_SERIAL_SEQ INCREMENT BY 1 start with 1 MAXVALUE 99999999999999999999 MINVALUE 1 CACHE 20;

CREATE SEQUENCE JWCD_REQUEST_SEQ INCREMENT BY 1 START WITH 1 MAXVALUE 9999999999999999999 MINVALUE 1 CACHE 20;




