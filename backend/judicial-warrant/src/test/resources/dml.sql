INSERT INTO REQUEST_TYPE VALUES(0, 'built-in', SYSTIMESTAMP, NULL, NULL, 'JUDICIAL_WARRANT_CAPACITY_DELEGATION', 'Judicial warrant capacity delegation request', 1, 1, 'طلب تخويل صفة الضبطية القضائئية' , 1, 'JWCD');
INSERT INTO REQUEST_TYPE VALUES('built-in', SYSTIMESTAMP, 'NEW_REGISTRATION', 'New registration request', 1, 2, n'طلب تسجيل اشخاص' , 2, 
INSERT INTO REQUEST_TYPE VALUES(0, 'built-in', SYSTIMESTAMP, NULL, NULL, 'UNRGISTRATION', 'Unregistration request', 1, 3, 'طلب شطب تسجيل اشخاص' , 3, 'UR');
INSERT INTO REQUEST_TYPE VALUES(0, 'built-in', SYSTIMESTAMP, NULL, NULL, 'REGISTRATION_RENEWAL', 'Registration renewal request', 1, 4, 'طلب تجديد تسجيل اشخاص' , 4, 'RR');


Insert into APP_ROLE (ID,CODE,ARABIC_NAME,ENGLISH_NAME,CREATE_BY,CREATE_DATE,IS_ACTIVE,LIST_ORDER) values (1,'ROLE_ADMIN','Ø¶ÙŠØ§Ø¡','diaa alden','diaa',to_timestamp('21-MAR-18 05.28.10.815000000 PM','DD-MON-RR HH.MI.SS.FF AM'),1,1);

Insert into ORGANIZATION_UNIT (ID,ARABIC_NAME,ENGLISH_NAME,VERSION,CREATE_BY,CREATE_DATE,UPDATE_BY,UPDATE_DATE,IS_ACTIVE,LIST_ORDER) values (1,'??????????','informatique',1,'test',to_timestamp('18-MAR-18 05.25.33.959000000 PM','DD-MON-RR HH.MI.SS.FF AM'),null,null,1,null);


Insert into APP_USER (ID,LOGIN_NAME,ARABIC_NAME,ENGLISH_NAME,ROLE_ID,MOBILE_NUMBER1,MOBILE_NUMBER2,EMAIL_ADDRESS,IS_ACTIVE,CREATE_DATE,CREATE_BY,UPDATE_BY,UPDATE_DATE,VERSION,ORGANIZATION_UNIT_ID,CIVIL_ID) values (1,'administrator','Ø¶ÙŠØ§Ø¡','diaa alden',1,'01020879079','01220155193','diahammad@informatique-edu.com',1,to_timestamp('21-MAR-18 04.53.18.171000000 PM','DD-MON-RR HH.MI.SS.FF AM'),'diaa','diaa',to_timestamp('21-MAR-18 04.53.36.939000000 PM','DD-MON-RR HH.MI.SS.FF AM'),1,1,1);



INSERT INTO "JUDICIALWARRANT"."REQUEST_TYPE" (CREATE_BY, CREATE_DATE, CODE, ENGLISH_NAME, IS_ACTIVE, LIST_ORDER, ARABIC_NAME, ID, REQUEST_SERIAL_PREFIX) VALUES ('system', TO_TIMESTAMP('30-APR-18 01.12.46.301000000 PM', 'DD-MON-RR HH.MI.SS.FF AM'), 'JT', 'Job Title', '1', '1', 'مسمى وظيفى', '1', 'JWCD');


INSERT INTO "JUDICIALWARRANT"."REQUEST_STATUS" (CODE, ENGLISH_NAME, ARABIC_NAME, ID) VALUES ('DRAFT', 'create status', 'create stutus', '1');

INSERT INTO "JUDICIALWARRANT"."REQUEST_STATUS" (CODE, ENGLISH_NAME, ARABIC_NAME, ID) VALUES ('SUBMITED', 'SUBMITED status', 'SUBMITED stutus', '2');

INSERT INTO "JUDICIALWARRANT"."REQUEST_STATUS" (CODE, ENGLISH_NAME, ARABIC_NAME, ID) VALUES ('INCOMPLETE', 'INCOMPLETE status', 'INCOMPLETE stutus', '3');

INSERT INTO "JUDICIALWARRANT"."REQUEST_STATUS" (CODE, ENGLISH_NAME, ARABIC_NAME, ID) VALUES ('INPROGRESS', 'INPROGRESS status', 'INPROGRESS stutus', '4');

INSERT INTO "JUDICIALWARRANT"."REQUEST_STATUS" (CODE, ENGLISH_NAME, ARABIC_NAME, ID) VALUES ('REJECTED', 'REJECTED status', 'REJECTED stutus', '5');

INSERT INTO "JUDICIALWARRANT"."REQUEST_STATUS" (CODE, ENGLISH_NAME, ARABIC_NAME, ID) VALUES ('ISSUED', 'ISSUED status', 'ISSUED stutus', '6');


INSERT INTO "JUDICIALWARRANT"."REQUEST_INTERNAL_STATUS" (ID, CODE, ARABIC_NAME, ENGLISH_NAME) VALUES ('1', 'RECIEVED', 'RECIEVED', 'RECIEVED');
INSERT INTO "JUDICIALWARRANT"."REQUEST_INTERNAL_STATUS" (ID, CODE, ARABIC_NAME, ENGLISH_NAME) VALUES ('2', 'INCOMPLETE', 'INCOMPLETE', 'INCOMPLETE');
INSERT INTO "JUDICIALWARRANT"."REQUEST_INTERNAL_STATUS" (ID, CODE, ARABIC_NAME, ENGLISH_NAME) VALUES ('3', 'INPROGRESS', 'INPROGRESS', 'INPROGRESS')
INSERT INTO "JUDICIALWARRANT"."REQUEST_INTERNAL_STATUS" (ID, CODE, ARABIC_NAME, ENGLISH_NAME) VALUES ('4', 'JWCD_LAW_AFFAIRS_REVIEW', 'LAW_AFFAIRS_REVIEW', 'LAW_AFFAIRS_REVIEW');
INSERT INTO "JUDICIALWARRANT"."REQUEST_INTERNAL_STATUS" (ID, CODE, ARABIC_NAME, ENGLISH_NAME) VALUES ('5', 'JWCD_LAW_AFFAIRS_REJECTED', 'LAW_AFFAIRS_REJECTED', 'LAW_AFFAIRS_REJECTED');
INSERT INTO "JUDICIALWARRANT"."REQUEST_INTERNAL_STATUS" (ID, CODE, ARABIC_NAME, ENGLISH_NAME) VALUES ('6', 'JWCD_LAW_AFFAIRS_ACCEPTED', 'LAW_AFFAIRS_ACCEPTED', 'LAW_AFFAIRS_ACCEPTED');
INSERT INTO "JUDICIALWARRANT"."REQUEST_INTERNAL_STATUS" (ID, CODE, ARABIC_NAME, ENGLISH_NAME) VALUES ('7', 'REJECTED', 'REJECTED', 'REJECTED');
INSERT INTO "JUDICIALWARRANT"."REQUEST_INTERNAL_STATUS" (ID, CODE, ARABIC_NAME, ENGLISH_NAME) VALUES ('8', 'ISSUED', 'ISSUED', 'ISSUED');



