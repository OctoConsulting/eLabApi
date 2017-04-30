-- Schema creation
CREATE schema  elab authorization  elab;

CREATE SEQUENCE  elab.note_dropdown_id_seq;
CREATE TABLE  elab.note_dropdown (
  id INTEGER DEFAULT nextval('elab.note_dropdown_id_seq'),
  item_type character varying(20) ,
  identifier character varying(20) ,
  label character varying(20),
  value VARCHAR(100), 
  CONSTRAINT note_dropdown_PK PRIMARY KEY (id)
);
ALTER SEQUENCE  elab.note_dropdown_id_seq OWNED BY  elab.note_dropdown.id;

CREATE SEQUENCE  elab.case_id_seq;
CREATE TABLE  elab.case (
  id INTEGER DEFAULT nextval('elab.case_id_seq'),
  lab_no INTEGER,
  status VARCHAR(100),
  opened_datetime TIMESTAMP WITH TIME ZONE,
  violation VARCHAR(100),
  violation_datetime TIMESTAMP WITH TIME ZONE,
  is_active BOOLEAN DEFAULT true,
  created_by VARCHAR(100) DEFAULT 'elab',
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp(),
  updated_by VARCHAR(100) DEFAULT 'elab',
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp(),
  CONSTRAINT case_PK PRIMARY KEY (id)
);
ALTER SEQUENCE  elab.case_id_seq OWNED BY  elab.case.id;

CREATE TABLE  elab.evidence_type (
  id INTEGER,
  description VARCHAR(80),
  is_active BOOLEAN DEFAULT true,
  created_by VARCHAR(100) DEFAULT 'elab',
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp(),
  updated_by VARCHAR(100) DEFAULT 'elab',
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp(),
  CONSTRAINT evidence_type_PK PRIMARY KEY (id)
);

CREATE SEQUENCE  elab.evidence_id_seq;
CREATE TABLE  elab.evidence (
  id INTEGER DEFAULT nextval('elab.evidence_id_seq'),
  case_id INTEGER references  elab.case(id),
  evidence_name VARCHAR(80),
  evidence_type INTEGER references  elab.evidence_type(id),
  is_forAnalysis BOOLEAN DEFAULT true,
  parent_id INTEGER,
  _id INTEGER ,
  created_by VARCHAR(100) DEFAULT 'elab',
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp(),
  updated_by VARCHAR(100) DEFAULT 'elab',
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp(),
  CONSTRAINT evidence_PK PRIMARY KEY (id)
);
ALTER SEQUENCE  elab.evidence_id_seq OWNED BY  elab.evidence.id;

CREATE TABLE  elab.exam_type (
  id INTEGER,
  description VARCHAR(80),
  is_active BOOLEAN DEFAULT true,
  created_by VARCHAR(100) DEFAULT 'elab',
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp(),
  updated_by VARCHAR(100) DEFAULT 'elab',
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp(),
  CONSTRAINT exam_type_PK PRIMARY KEY (id)
);

CREATE TABLE  elab.examiner (
  id INTEGER,
  examiner_name VARCHAR(500),
  is_active BOOLEAN DEFAULT true,
  created_by VARCHAR(100) DEFAULT 'elab',
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp(),
  updated_by VARCHAR(100) DEFAULT 'elab',
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp(),
  CONSTRAINT examiner_PK PRIMARY KEY (id)
);

CREATE SEQUENCE  elab.exam_id_seq;
CREATE TABLE  elab.exam (
  id INTEGER DEFAULT nextval('elab.exam_id_seq'),
  case_id INTEGER references  elab.case(id),
  _id INTEGER,
  evidence_id INTEGER,
  exam_name VARCHAR(80),
  exam_type INTEGER references  elab.exam_type(id),
  examiner_id INTEGER references  elab.examiner(id),
  assigned_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp(),
  start_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp(),
  end_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp(),
  created_by VARCHAR(100) DEFAULT 'elab',
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp(),
  updated_by VARCHAR(100) DEFAULT 'elab',
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp(),
  CONSTRAINT exam_PK PRIMARY KEY (id)
);
ALTER SEQUENCE  elab.exam_id_seq OWNED BY  elab.exam.id;

CREATE TABLE  elab.note_type (
  id INTEGER,
  description VARCHAR(80),
  is_active BOOLEAN DEFAULT true,
  created_by VARCHAR(100) DEFAULT 'elab',
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp(),
  updated_by VARCHAR(100) DEFAULT 'elab',
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp(),
  CONSTRAINT note_type_PK PRIMARY KEY (id)
);

CREATE SEQUENCE elab.note_id_seq;
CREATE TABLE elab.note (
  id INTEGER DEFAULT nextval('elab.note_id_seq'),
  case_id INTEGER references elab.case(id),
  exam_id INTEGER references elab.exam(id),
  marked_complete boolean DEFAULT false,
  note_type INTEGER references elab.note_type(id),
  item_type VARCHAR(20),
  evidences VARCHAR(80),
  parent_id INTEGER,
  note_message VARCHAR(5000),
  label_info VARCHAR(5000),
  note_data json,
  created_by VARCHAR(100) DEFAULT 'elab',
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp(),
  updated_by VARCHAR(100) DEFAULT 'elab',
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp(),
  CONSTRAINT note_PK PRIMARY KEY (id)
);
ALTER SEQUENCE elab.note_id_seq OWNED BY elab.note.id;

ALTER TABLE elab.note
  OWNER to elab;

INSERT INTO elab.case(
  id, lab_no, status, opened_datetime, violation, violation_datetime)
  VALUES (1,1, 'In Progress', '2017-04-15', 'Violation', '2017-04-15');

 INSERT INTO elab.evidence_type(id, description) VALUES (0, 'Select Option');
 INSERT INTO elab.evidence_type(
  id, description)
  VALUES (1, 'Container');
INSERT INTO elab.evidence_type(
  id, description)
  VALUES (2, 'Package');
INSERT INTO elab.evidence_type(
  id, description)
  VALUES (3, 'Item');

 INSERT INTO elab.exam_type(id, description) VALUES (0, 'Select Option');	
 INSERT INTO elab.exam_type(
  id, description)
  VALUES (1, 'Shoe Prints/Tire Thread');
 INSERT INTO elab.exam_type(
  id, description)
  VALUES (2, 'Chemistry - Toxicology');
 INSERT INTO elab.exam_type(
  id, description)
  VALUES (3, 'Firearms');
 INSERT INTO elab.exam_type(
  id, description)
  VALUES (4, 'Question Documents');
  
 INSERT INTO elab.examiner(
  id, examiner_name)
  VALUES (0, 'Select Option');	
 INSERT INTO elab.examiner(
  id, examiner_name)
  VALUES (1, 'Nithin Emanuel');
INSERT INTO elab.examiner(
  id, examiner_name)
  VALUES (2, 'Akanksha Chauhan');
INSERT INTO elab.examiner(
  id, examiner_name)
  VALUES (3, 'Sumit Dang');
 INSERT INTO elab.examiner(
  id, examiner_name)
  VALUES (4, 'Juliette Fitzsimmons');
 INSERT INTO elab.examiner(
  id, examiner_name)
  VALUES (5, 'Erik Hosler');
 INSERT INTO elab.examiner(
  id, examiner_name)
  VALUES (6, 'Marcus Stanton');

 INSERT INTO elab.note_type(
  id, description)
  VALUES (1, 'Initial Assessment');

 INSERT INTO elab.note_type(
  id, description)
  VALUES (2, 'K Item Detail');
  
 INSERT INTO elab.note_type(
  id, description)
  VALUES (3, 'Q Item Detail');

INSERT INTO elab.note_dropdown(
 item_type, identifier, label, value)
VALUES 
('Shoe','K','K Item Type','Original Footwear'),
( 'Shoe','K','K Item Type','Footwear test impression'),
('Shoe','K','K Item Type','Photo/Printouts'),
( 'Shoe','K','K Item Type','Disc'),
( 'Shoe','K','K Item Type','Digital Image'),
( 'Shoe','K','K Item Type','Other'),
( 'Shoe','K','Style','Shoe'),
( 'Shoe','K','Style','Sandal'),
( 'Shoe','K','Style','Boot'),
( 'Tire','','Vehicle Position','Driver Front'),
( 'Tire','','Vehicle Position','Passenger Front'),
( 'Tire','','Vehicle Position','Driver Rear'),
( 'Tire','','Vehicle Position','Passenger Rear'),
( 'Tire','','Vehicle Position','Spare'),
( 'Tire','','Vehicle Position','Unknown'),
( '','Q','Q Item Type','Gel Lift'),
( '','Q','Q Item Type','Original'),
( '','Q','Q Item Type','Static Lift'),
( '','Q','Q Item Type','Adhesive Lift'),
( '','Q','Q Item Type','Case'),
( '','Q','Q Item Type','Photo/printout'),
( '','Q','Q Item Type','Disc'),
( '','Q','Q Item Type','Digital Image'),
( 'Shoe','','Request Type','Footwear Comparison'),
( 'Shoe','','Request Type','Footwear make/model determination'),
( 'Shoe','','Request Type','Footwear size determination'),
( 'Shoe','','Request Type','Other'),
( 'Shoe','','Method','QDU Procedures for Conducting Shoe and Tire Tread Examinations'),
( 'Shoe','','Method','QDU Procedures for Conducting a Footwear Database Search'),
('Tire','','Request Type','Tire Comparison'),
( 'Tire','','Request Type','Tire make/model determination'),
( 'Tire','','Request Type','Other'),
( 'Tire','','Method','QDU Procedures for Conducting Shoe and Tire Tread Examinations'),
( 'Tire','','Method','QDU Procedures for Conducting a Tire Tread Database'),
('Tire','K','K Item Type','Original Tire'),
( 'Tire','K','K Item Type','Tire test impression'),
( 'Tire','K','K Item Type','Photo/Printouts'),
( 'Tire','K','K Item Type','Disc'),
( 'Tire','K','K Item Type','Digital Image'),
( 'Tire','K','K Item Type','Other'),
('','','Conducted by','Juliette Fitzsimmons'),
('','','Conducted by','Marcus Stanton'),
('','','Conducted by','Tim Miller'),
('','','Conducted by','Barb McCullen');

 INSERT INTO elab.evidence(
  id, case_id, evidence_name, evidence_type,_id)
  VALUES (1, 1, 'Box 1', 1,1);
  
 INSERT INTO elab.evidence(
  id, case_id, evidence_name, evidence_type, parent_id,_id)
  VALUES (2, 1, 'Paper Bag 1', 2, 1,1);

 INSERT INTO elab.evidence(
  id, case_id, evidence_name, evidence_type, is_foranalysis, parent_id,_id)
  VALUES (3, 1, 'Piece of Rubber', 3, true, 2,1);
  
 INSERT INTO elab.evidence(
  id, case_id, evidence_name, evidence_type, is_foranalysis, parent_id,_id)
  VALUES (4, 1, 'Shoe Laces', 3, true, 2,2);
  
 INSERT INTO elab.evidence(
  id, case_id, evidence_name, evidence_type,_id)
  VALUES (5, 1, 'Box 2', 1,2);
  
 INSERT INTO elab.evidence(
  id, case_id, evidence_name, evidence_type, parent_id,_id)
  VALUES (6, 1, 'Paper Bag 2', 2, 5,2);

 INSERT INTO elab.evidence(
  id, case_id, evidence_name, evidence_type, is_foranalysis, parent_id,_id)
  VALUES (7, 1, 'Left Nike Sneaker', 3, true, 6,3);
  
 INSERT INTO elab.evidence(
  id, case_id, evidence_name, evidence_type, is_foranalysis, parent_id,_id)
  VALUES (8, 1, 'Shoe Imprint', 3, true, 6,4);
  
 INSERT INTO elab.evidence(
  id, case_id, evidence_name, evidence_type, is_foranalysis, parent_id,_id)
  VALUES (9, 1, 'Tire Impression', 3, true, 6,5);
  
 INSERT INTO elab.evidence(
  id, case_id, evidence_name, evidence_type, is_foranalysis, parent_id,_id)
  VALUES (10, 1, 'Tire Impression 2', 3, true, 6,6);
  
 UPDATE elab.evidence set is_foranalysis = false where evidence.evidence_type < 3;

 INSERT INTO elab.exam(
  id, case_id, evidence_id, exam_name, exam_type, examiner_id,_id)
  VALUES 
  (1, 1, 0, 'Shoe/Tire', 1, 1, 1),
  
-- Owner assignment for tables
ALTER TABLE  elab.case OWNER to  elab; 
ALTER TABLE  elab.evidence OWNER to  elab;
ALTER TABLE  elab.evidence_type OWNER to elab;
ALTER TABLE  elab.exam OWNER to elab;
ALTER TABLE  elab.exam_type OWNER to elab;
ALTER TABLE  elab.examiner OWNER to elab;
ALTER TABLE  elab.note OWNER to elab;
ALTER TABLE  elab.note_dropdown OWNER to elab;
ALTER TABLE  elab.note_type OWNER to elab;
