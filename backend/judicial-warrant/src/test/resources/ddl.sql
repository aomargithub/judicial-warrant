CREATE TABLE "JUDICIALWARRANT"."REQUEST_TYPE" 
   (	
    "ORM_VERSION" NUMBER(*,0) NOT NULL ENABLE, 
	"CREATE_BY" VARCHAR2(500) NOT NULL ENABLE, 
	"CREATE_DATE" TIMESTAMP (6) NOT NULL ENABLE, 
	"UPDATE_BY" VARCHAR2(500), 
	"UPDATE_DATE" TIMESTAMP (6), 
	"CODE" VARCHAR2(100) NOT NULL ENABLE, 
	"ENGLISH_NAME" VARCHAR2(500) NOT NULL ENABLE, 
	"IS_ACTIVE" NUMBER(1,0) NOT NULL ENABLE, 
	"DISPLAY_ORDER" NUMBER(22,0) NOT NULL ENABLE, 
	"ARABIC_NAME" NVARCHAR2(100) NOT NULL ENABLE, 
	"ID" NUMBER NOT NULL ENABLE, 
	"REQUEST_NUMBER_PREFIX" VARCHAR2(100), 
	
	 CONSTRAINT "REQUEST_TYPE_PK" PRIMARY KEY ("ID"), 
	 CONSTRAINT "REQUEST_TYPE_CODE_UN" UNIQUE ("CODE"), 
	 CONSTRAINT "REQUEST_TYPE_DSPLY_ORDR_UN" UNIQUE ("DISPLAY_ORDER")
   ) ;