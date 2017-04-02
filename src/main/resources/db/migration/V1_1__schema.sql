
-- Schema creation
CREATE schema elab authorization elab;

--Lookup Tables Start--
CREATE TABLE elab.evidence_type (
  _id INTEGER NOT NULL UNIQUE,
  description VARCHAR(80) UNIQUE NOT NULL,
  is_active BOOLEAN DEFAULT true NOT NULL,
  created_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT evidence_type_PK PRIMARY KEY (_id)
);

CREATE TABLE elab.exam_type (
  _id INTEGER NOT NULL UNIQUE,
  description VARCHAR(80) UNIQUE NOT NULL,
  is_active BOOLEAN DEFAULT true NOT NULL,
  created_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT exam_type_PK PRIMARY KEY (_id)
);

CREATE TABLE elab.examiner (
  _id INTEGER NOT NULL UNIQUE,
  examiner_name VARCHAR(500) UNIQUE NOT NULL,
  is_active BOOLEAN DEFAULT true NOT NULL,
  created_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT examiner_PK PRIMARY KEY (_id)
);

CREATE TABLE elab.note_type (
  _id INTEGER NOT NULL UNIQUE,
  description VARCHAR(80) UNIQUE NOT NULL,
  is_active BOOLEAN DEFAULT true NOT NULL,
  created_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_type_PK PRIMARY KEY (_id)
);

CREATE TABLE elab.note_detail_type (
  _id INTEGER NOT NULL UNIQUE,
  description VARCHAR(80) UNIQUE NOT NULL,
  is_active BOOLEAN DEFAULT true NOT NULL,
  created_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_detail_type_PK PRIMARY KEY (_id)
);
--Lookup Tables End--

--Transactional Tables Start--

CREATE SEQUENCE elab.case_id_seq;
CREATE TABLE elab.case (
  _id INTEGER NOT NULL DEFAULT nextval('elab.case_id_seq'),
  case_data json NOT NULL,
  is_active BOOLEAN DEFAULT true NOT NULL,
  created_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT case_PK PRIMARY KEY (_id)
);
ALTER SEQUENCE elab.case_id_seq OWNED BY elab.case._id;

CREATE SEQUENCE elab.evidence_id_seq;
CREATE TABLE elab.evidence (
  _id INTEGER NOT NULL DEFAULT nextval('elab.evidence_id_seq'),
  case_id INTEGER references elab.case(_id) NOT NULL,
  evidence_name VARCHAR(4000) NOT NULL,
  evidence_number INTEGER NOT NULL,
  evidence_type INTEGER references elab.evidence_type(_id) NOT NULL,
  is_forAnalysis BOOLEAN,
  parent_id INTEGER, 
  created_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT evidence_PK PRIMARY KEY (_id)
);
ALTER SEQUENCE elab.evidence_id_seq OWNED BY elab.evidence._id;

CREATE SEQUENCE elab.exam_id_seq;
CREATE TABLE elab.exam (
  _id INTEGER NOT NULL DEFAULT nextval('elab.exam_id_seq'),
  case_id INTEGER references elab.case(_id) NOT NULL,
  exam_name VARCHAR(80),
  exam_type INTEGER references elab.exam_type(_id) NOT NULL,
  examiner_id INTEGER references elab.examiner(_id),
  assigned_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp(),
  start_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp(),
  end_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp(),
  created_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT exam_PK PRIMARY KEY (_id)
);
ALTER SEQUENCE elab.exam_id_seq OWNED BY elab.exam._id;

CREATE SEQUENCE elab.note_id_seq;
CREATE TABLE elab.note (
  _id INTEGER NOT NULL DEFAULT nextval('elab.note_id_seq'),
  exam_id INTEGER references elab.exam(_id) NOT NULL,
  exam_sub_type VARCHAR(80),
  note_type INTEGER references elab.note_type(_id) NOT NULL,
  note_data json NOT NULL,
  created_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_PK PRIMARY KEY (_id)
);
ALTER SEQUENCE elab.note_id_seq OWNED BY elab.note._id;

CREATE SEQUENCE elab.note_detail_id_seq;
CREATE TABLE elab.note_detail (
  _id INTEGER NOT NULL DEFAULT nextval('elab.note_detail_id_seq'),
  note_id INTEGER NOT NULL references elab.note(_id),
  note_detail_type INTEGER references elab.note_detail_type(_id) NOT NULL,
  note_detail_data json NOT NULL,
  created_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_detail_PK PRIMARY KEY (_id)
);
ALTER SEQUENCE elab.note_detail_id_seq OWNED BY elab.note_detail._id;

CREATE SEQUENCE elab.note_detail_evidence_id_seq;
CREATE TABLE elab.note_detail_evidence (
  _id INTEGER NOT NULL DEFAULT nextval('elab.note_detail_evidence_id_seq'),
  note_detail_id INTEGER references elab.note_detail(_id) NOT NULL,
  evidence_id INTEGER references elab.evidence(_id) NOT NULL,
  created_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_detail_evidence_PK PRIMARY KEY (_id)
);
ALTER SEQUENCE elab.note_detail_evidence_id_seq OWNED BY elab.note_detail_evidence._id;

CREATE SEQUENCE elab.image_id_seq;
CREATE TABLE elab.image (
  _id INTEGER NOT NULL DEFAULT nextval('elab.image_id_seq'),
  note_id INTEGER references elab.note(_id) NOT NULL,
  image bytea,
  created_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT image_PK PRIMARY KEY (_id)
);
ALTER SEQUENCE elab.image_id_seq OWNED BY elab.image._id;

--Transactional Tables End--

-- Index Creation
Create unique index IX_case on elab.case (_id);
Create unique index IX_evidence on elab.evidence (_id);
Create unique index IX_evidence_type on elab.evidence_type (_id);
Create unique index IX_exam on elab.exam (_id);
Create unique index IX_exam_type on elab.exam_type (_id);
Create unique index IX_examiner on elab.examiner (_id);
Create unique index IX_image on elab.image (_id);
Create unique index IX_note on elab.note (_id);
Create unique index IX_note_detail on elab.note_detail (_id);
Create unique index IX_note_detail_evidence on elab.note_detail_evidence (_id);
Create unique index IX_note_detail_type on elab.note_detail_type (_id);
Create unique index IX_note_type on elab.note_type (_id);

-- Owner assignment for tables
ALTER TABLE elab.case OWNER to elab;
ALTER TABLE elab.evidence OWNER to elab;
ALTER TABLE elab.evidence_type OWNER to elab;
ALTER TABLE elab.exam OWNER to elab;
ALTER TABLE elab.exam_type OWNER to elab;
ALTER TABLE elab.examiner OWNER to elab;
ALTER TABLE elab.image OWNER to elab;
ALTER TABLE elab.note OWNER to elab;
ALTER TABLE elab.note_detail OWNER to elab;
ALTER TABLE elab.note_detail_evidence OWNER to elab;
ALTER TABLE elab.note_detail_type OWNER to elab;
ALTER TABLE elab.note_type OWNER to elab;

-- Insert data into reference tables
insert into elab.evidence_type(_id,description) values(1,'Container');
insert into elab.evidence_type(_id,description) values(2,'Package');
insert into elab.evidence_type(_id,description) values(3,'Item');

insert into elab.exam_type(_id,description) values(1,'Chemistry - Toxicology');
insert into elab.exam_type(_id,description) values(2,'Firearms');
insert into elab.exam_type(_id,description) values(3,'Question Documents');
insert into elab.exam_type(_id,description) values(4,'Shoe Prints/Tire Tread');

insert into elab.examiner(_id,examiner_name) values(1,'Barb McCullen');
insert into elab.examiner(_id,examiner_name) values(2,'Juliette Fitzsimmons');
insert into elab.examiner(_id,examiner_name) values(3,'Marcus Stanton');  
insert into elab.examiner(_id,examiner_name) values(4,'Tim Miller');

insert into elab.note_type(_id,description) values(1,'Comparison');
insert into elab.note_type(_id,description) values(2,'Imaging Request');
insert into elab.note_type(_id,description) values(3,'Imaging Work Performed');
insert into elab.note_type(_id,description) values(4,'Initial Assessment');
insert into elab.note_type(_id,description) values(5,'Make/Model Determination');
insert into elab.note_type(_id,description) values(6,'Processing');
insert into elab.note_type(_id,description) values(7,'Test Impression');

insert into elab.note_detail_type(_id,description) values(1,'K Item Detail');
insert into elab.note_detail_type(_id,description) values(2,'Q Item Detail');
insert into elab.note_detail_type(_id,description) values(3,'Non Evidentiary Item Detail');