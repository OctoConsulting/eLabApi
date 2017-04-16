
-- Schema creation
CREATE schema  elab authorization  elab;

CREATE SEQUENCE  elab.initial_assessment_note_type_id_seq;
CREATE TABLE  elab.initial_assessment_note_type (
  id INTEGER NOT NULL DEFAULT nextval('elab.initial_assessment_note_type_id_seq'),
  name VARCHAR(100) NOT NULL,
  created_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT initial_assessment_note_type_PK PRIMARY KEY (id)
);
ALTER SEQUENCE  elab.initial_assessment_note_type_id_seq OWNED BY  elab.initial_assessment_note_type.id;

CREATE SEQUENCE  elab.note_request_type_id_seq;
CREATE TABLE  elab.note_request_type (
  id INTEGER NOT NULL DEFAULT nextval('elab.note_request_type_id_seq'),
  name VARCHAR(100) NOT NULL,
  created_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_request_type_PK PRIMARY KEY (id)
);
ALTER SEQUENCE  elab.note_request_type_id_seq OWNED BY  elab.note_request_type.id;

CREATE SEQUENCE  elab.note_method_id_seq;
CREATE TABLE  elab.note_method (
  id INTEGER NOT NULL DEFAULT nextval('elab.note_method_id_seq'),
  name VARCHAR(100) NOT NULL,
  created_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_method_PK PRIMARY KEY (id)
);
ALTER SEQUENCE  elab.note_method_id_seq OWNED BY  elab.note_method.id;



CREATE SEQUENCE  elab.note_detail_item_style_id_seq;
CREATE TABLE  elab.note_detail_item_style (
  id INTEGER NOT NULL DEFAULT nextval('elab.note_detail_item_style_id_seq'),
  name VARCHAR(100) NOT NULL,
  created_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_detail_item_style_PK PRIMARY KEY (id)
);
ALTER SEQUENCE  elab.note_detail_item_style_id_seq OWNED BY  elab.note_detail_item_style.id;

CREATE SEQUENCE  elab.note_detail_vehicle_position_id_seq;
CREATE TABLE  elab.note_detail_vehicle_position (
  id INTEGER NOT NULL DEFAULT nextval('elab.note_detail_vehicle_position_id_seq'),
  name VARCHAR(100) NOT NULL,
  created_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_detail_vehicle_position_PK PRIMARY KEY (id)
);
ALTER SEQUENCE  elab.note_detail_vehicle_position_id_seq OWNED BY  elab.note_detail_vehicle_position.id;

CREATE SEQUENCE  elab.note_detail_tire_type_id_seq;
CREATE TABLE  elab.note_detail_tire_type (
  id INTEGER NOT NULL DEFAULT nextval('elab.note_detail_tire_type_id_seq'),
  name VARCHAR(100) NOT NULL,
  created_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_detail_tire_type_PK PRIMARY KEY (id)
);
ALTER SEQUENCE  elab.note_detail_tire_type_id_seq OWNED BY  elab.note_detail_tire_type.id;

CREATE SEQUENCE  elab.note_detail_q_item_type_id_seq;
CREATE TABLE  elab.note_detail_q_item_type (
  id INTEGER NOT NULL DEFAULT nextval('elab.note_detail_q_item_type_id_seq'),
  name VARCHAR(100) NOT NULL,
  created_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_detail_q_item_type_PK PRIMARY KEY (id)
);
ALTER SEQUENCE  elab.note_detail_q_item_type_id_seq OWNED BY  elab.note_detail_q_item_type.id;


CREATE SEQUENCE  elab.note_detail_k_item_type_id_seq;
CREATE TABLE  elab.note_detail_k_item_type (
  id INTEGER NOT NULL DEFAULT nextval('elab.note_detail_k_item_type_id_seq'),
  name VARCHAR(100) NOT NULL,
  created_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_detail_k_item_type_PK PRIMARY KEY (id)
);
ALTER SEQUENCE  elab.note_detail_k_item_type_id_seq OWNED BY  elab.note_detail_k_item_type.id;

CREATE SEQUENCE  elab.case_id_seq;
CREATE TABLE  elab.case (
  id INTEGER NOT NULL DEFAULT nextval('elab.case_id_seq'),
  lab_no INTEGER NOT NULL,
  status VARCHAR(100) NOT NULL,
  opened_datetime TIMESTAMP WITH TIME ZONE,
  violation VARCHAR(100) NOT NULL,
  violation_datetime TIMESTAMP WITH TIME ZONE,
  is_active BOOLEAN DEFAULT true NOT NULL,
  created_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT case_PK PRIMARY KEY (id)
);
ALTER SEQUENCE  elab.case_id_seq OWNED BY  elab.case.id;

CREATE TABLE  elab.evidence_type (
  id INTEGER NOT NULL UNIQUE,
  description VARCHAR(80) UNIQUE NOT NULL,
  is_active BOOLEAN DEFAULT true NOT NULL,
  created_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT evidence_type_PK PRIMARY KEY (id)
);

CREATE SEQUENCE  elab.evidence_id_seq;
CREATE TABLE  elab.evidence (
  id INTEGER NOT NULL DEFAULT nextval('elab.evidence_id_seq'),
  case_id INTEGER references  elab.case(id) NOT NULL,
  evidence_name VARCHAR(80) NOT NULL,
  evidence_type INTEGER references  elab.evidence_type(id) NOT NULL,
  is_forAnalysis BOOLEAN DEFAULT true NOT NULL,
  parent_id INTEGER,
  item_type character varying(20) ,
  identifier character varying(20) ,
  created_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT evidence_PK PRIMARY KEY (id)
);
ALTER SEQUENCE  elab.evidence_id_seq OWNED BY  elab.evidence.id;

CREATE TABLE  elab.exam_type (
  id INTEGER NOT NULL UNIQUE,
  description VARCHAR(80) UNIQUE NOT NULL,
  is_active BOOLEAN DEFAULT true NOT NULL,
  created_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT exam_type_PK PRIMARY KEY (id)
);

CREATE TABLE  elab.examiner (
  id INTEGER NOT NULL UNIQUE,
  examiner_name VARCHAR(500) UNIQUE NOT NULL,
  is_active BOOLEAN DEFAULT true NOT NULL,
  created_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT examiner_PK PRIMARY KEY (id)
);

CREATE SEQUENCE  elab.exam_id_seq;
CREATE TABLE  elab.exam (
  id INTEGER NOT NULL DEFAULT nextval('elab.exam_id_seq'),
  case_id INTEGER references  elab.case(id) NOT NULL,
  evidence_id INTEGER references  elab.evidence(id) NOT NULL,
  exam_name VARCHAR(80) NOT NULL,
  exam_type INTEGER references  elab.exam_type(id) NOT NULL,
  examiner_id INTEGER references  elab.examiner(id) NOT NULL,
  assigned_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  start_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  end_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  created_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT exam_PK PRIMARY KEY (id)
);
ALTER SEQUENCE  elab.exam_id_seq OWNED BY  elab.exam.id;

CREATE TABLE  elab.note_type (
  id INTEGER NOT NULL UNIQUE,
  description VARCHAR(80) UNIQUE NOT NULL,
  is_active BOOLEAN DEFAULT true NOT NULL,
  created_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_type_PK PRIMARY KEY (id)
);


CREATE SEQUENCE elab.note_id_seq;
CREATE TABLE elab.note (
  id INTEGER NOT NULL DEFAULT nextval('elab.note_id_seq'),
  exam_id INTEGER references elab.exam(id) NOT NULL,
  exam_sub_type VARCHAR(80) NOT NULL,
  note_type INTEGER references elab.note_type(id) NOT NULL,
  note_category VARCHAR(80) NOT NULL,
  initial_assessment_note_type_id INTEGER references elab.initial_assessment_note_type(id) NOT NULL,
  conducted_by VARCHAR(80) NOT NULL,
  request_type INTEGER references elab.note_request_type(id) NOT NULL,
  note_method  INTEGER references elab.note_method(id) NOT NULL,
  note_data VARCHAR(4000) NOT NULL,
  created_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_PK PRIMARY KEY (id)
);
ALTER SEQUENCE elab.note_id_seq OWNED BY elab.note.id;


CREATE TABLE elab.note_detail_type (
  id INTEGER NOT NULL UNIQUE,
  description VARCHAR(80) UNIQUE NOT NULL,
  is_active BOOLEAN DEFAULT true NOT NULL,
  created_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_detail_type_PK PRIMARY KEY (id)
);

CREATE SEQUENCE elab.note_detail_id_seq;
CREATE TABLE elab.note_detail (
  id INTEGER NOT NULL DEFAULT nextval('elab.note_detail_id_seq'),
 note_id INTEGER NOT NULL references elab.note(id),
  note_detail_type INTEGER references elab.note_detail_type(id) NOT NULL,
  note_detail_k_item_type_id INTEGER references elab.note_detail_k_item_type(id) NOT NULL,
  note_detail_q_item_type_id INTEGER references elab.note_detail_q_item_type(id) NOT NULL,
  note_detail_item_style_id INTEGER references elab.note_detail_item_style(id) NOT NULL,
  brand_name VARCHAR(100) NOT NULL,
  note_detail_size VARCHAR(100) NOT NULL,
  model VARCHAR(100) NOT NULL,
  dot_number VARCHAR(100) NOT NULL,
  note_detail_vehicle_position_id INTEGER references elab.note_detail_vehicle_position(id) NOT NULL,
  note_detail_tire_type_id INTEGER references elab.note_detail_tire_type(id) NOT NULL,
  note_detail_data VARCHAR(4000) NOT NULL,
  note_detail_lable_info VARCHAR(2000) NOT NULL,
  created_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  created_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'elab' NOT NULL,
  updated_date TIMESTAMP WITH TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_detail_PK PRIMARY KEY (id)
);
ALTER SEQUENCE elab.note_detail_id_seq OWNED BY elab.note_detail.id;

ALTER TABLE elab.note
    OWNER to elab;



INSERT INTO elab."case"(
  id, lab_no, status, opened_datetime, violation, violation_datetime, is_active)
  VALUES (1,1, 'In Progress', '2017-04-15', 'Violation', '2017-04-15', true);

  INSERT INTO elab.evidence_type(
  id, description, is_active)
  VALUES (1, 'Container', true);
INSERT INTO elab.evidence_type(
  id, description, is_active)
  VALUES (2, 'Package', true);
INSERT INTO elab.evidence_type(
  id, description, is_active)
  VALUES (3, 'Item', true);


  INSERT INTO elab.exam_type(
  id, description, is_active)
  VALUES (1, 'Shoe Prints/Tire Thread',true);

  INSERT INTO elab.examiner(
  id, examiner_name, is_active)
  VALUES (1, 'Nithin', true);
INSERT INTO elab.examiner(
  id, examiner_name, is_active)
  VALUES (2, 'Akanksha', true);
INSERT INTO elab.examiner(
  id, examiner_name, is_active)
  VALUES (3, 'Sumit', true);
    INSERT INTO elab.examiner(
  id, examiner_name, is_active)
  VALUES (4, 'Juliette Fitzsimmons', true);
      INSERT INTO elab.examiner(
  id, examiner_name, is_active)
  VALUES (5, 'Erik', true);
   INSERT INTO elab.examiner(
  id, examiner_name, is_active)
  VALUES (6, 'Marcus Stanton', true);

  INSERT INTO elab.note_type(
  id, description, is_active)
  VALUES (1, 'Initial Assessment', true);

  INSERT INTO elab.note_detail_type(
  id, description, is_active)
  VALUES (1, 'K Item Detail', true);
    
    INSERT INTO elab.note_detail_type(
  id, description, is_active)
  VALUES (2, 'Q Item Detail', true);

  INSERT INTO elab.note_detail_k_item_type(
  id, name)
  VALUES (1, 'Original Footwear');
      
    INSERT INTO elab.note_detail_k_item_type(
  id, name)
  VALUES (2, 'Footwear test impression');
      
    INSERT INTO elab.note_detail_k_item_type(
  id, name)
  VALUES (3, 'Photo/Printouts');
      
    INSERT INTO elab.note_detail_k_item_type(
  id, name)
  VALUES (4, 'Disc');
    INSERT INTO elab.note_detail_k_item_type(
  id, name)
  VALUES (5, 'Digital Image');
    INSERT INTO elab.note_detail_k_item_type(
  id, name)
  VALUES (6, 'Other');
    
    INSERT INTO elab.note_detail_item_style(
  id, name)
  VALUES (1, 'Shoe');
    INSERT INTO elab.note_detail_item_style(
  id, name)
  VALUES (2, 'Sandal');
     INSERT INTO elab.note_detail_item_style(
  id, name)
  VALUES (3, 'Boot');

    INSERT INTO elab.note_detail_vehicle_position(
  id, name)
  VALUES (1, 'Driver Front');
    INSERT INTO elab.note_detail_vehicle_position(
  id, name)
  VALUES (2, 'Passenger Front');
      INSERT INTO elab.note_detail_vehicle_position(
  id, name)
  VALUES (3, 'Driver Rear');
    INSERT INTO elab.note_detail_vehicle_position(
  id, name)
  VALUES (4, 'Passenger Rear');
     INSERT INTO elab.note_detail_vehicle_position(
  id, name)
  VALUES (5, 'Spare');
     INSERT INTO elab.note_detail_vehicle_position(
  id, name)
  VALUES (6, 'Unknown');

  

    INSERT INTO elab.note_detail_q_item_type(
  id, name)
  VALUES (1, 'Gel Lift');
      INSERT INTO elab.note_detail_q_item_type(
  id, name)
  VALUES (2, 'Original');
      INSERT INTO elab.note_detail_q_item_type(
  id, name)
  VALUES (3, 'Static Lift');
      INSERT INTO elab.note_detail_q_item_type(
  id, name)
  VALUES (4, 'Adhesive Lift');
      INSERT INTO elab.note_detail_q_item_type(
  id, name)
  VALUES (5, 'Case');
      INSERT INTO elab.note_detail_q_item_type(
  id, name)
  VALUES (6, 'Photo/printout');
       INSERT INTO elab.note_detail_q_item_type(
  id, name)
  VALUES (7, 'Disc');
       INSERT INTO elab.note_detail_q_item_type(
  id, name)
  VALUES (8, 'Digital Image');

  INSERT INTO elab.initial_assessment_note_type(
  id, name)
  VALUES (1, 'Footwear Comparison');
    INSERT INTO elab.initial_assessment_note_type(
  id, name)
  VALUES (2, 'Footwear make/model determination');
    INSERT INTO elab.initial_assessment_note_type(
  id, name)
  VALUES (3, 'Footwear size determination');
  
  INSERT INTO elab.initial_assessment_note_type(
  id, name)
  VALUES (4, 'Other');

  INSERT INTO elab.evidence(
  id, case_id, evidence_name, evidence_type)
  VALUES (1, 1, 'Box 1', 1);
  
  INSERT INTO elab.evidence(
  id, case_id, evidence_name, evidence_type, parent_id)
  VALUES (2, 1, 'Paper Bag 1', 2, 1);

  INSERT INTO elab.evidence(
  id, case_id, evidence_name, evidence_type, is_foranalysis, parent_id)
  VALUES (3, 1, 'Piece of Rubber', 3, true, 2);
  
  INSERT INTO elab.evidence(
  id, case_id, evidence_name, evidence_type, is_foranalysis, parent_id)
  VALUES (4, 1, 'Shoe Laces', 3, true, 2);
  
  INSERT INTO elab.evidence(
  id, case_id, evidence_name, evidence_type)
  VALUES (5, 1, 'Box 2', 1);
  
  INSERT INTO elab.evidence(
  id, case_id, evidence_name, evidence_type, parent_id)
  VALUES (6, 1, 'Paper Bag 2', 2, 5);

  INSERT INTO elab.evidence(
  id, case_id, evidence_name, evidence_type, is_foranalysis, parent_id)
  VALUES (7, 1, 'Left Nike Sneaker', 3, true, 6);
  
  INSERT INTO elab.evidence(
  id, case_id, evidence_name, evidence_type, is_foranalysis, parent_id)
  VALUES (8, 1, 'Shoe Imprint', 3, true, 6);
  
  INSERT INTO elab.evidence(
  id, case_id, evidence_name, evidence_type, is_foranalysis, parent_id)
  VALUES (9, 1, 'Tire Impression', 3, true, 6);
  
  INSERT INTO elab.evidence(
  id, case_id, evidence_name, evidence_type, is_foranalysis, parent_id)
  VALUES (10, 1, 'Tire Impression 2', 3, true, 6);

      INSERT INTO elab.exam(
  id, case_id, evidence_id, exam_name, exam_type, examiner_id)
  VALUES 
    (1, 1, 3, 'Shoe/Tire', 1, 1),
    (2, 1, 4, 'Shoe/Tire', 1, 1),
    (3, 1, 7, 'Shoe/Tire', 1, 1),
    (4, 1, 8, 'Shoe/Tire', 1, 1),
    (5, 1, 9, 'Shoe/Tire', 1, 1),
    (6, 1, 10, 'Shoe/Tire', 1, 1);

-- Owner assignment for tables
ALTER TABLE  elab.case OWNER to  elab;   
ALTER TABLE  elab.evidence OWNER to  elab;
ALTER TABLE  elab.evidence_type OWNER to   elab;
ALTER TABLE  elab.exam OWNER to   elab;
ALTER TABLE  elab.exam_type OWNER to   elab;
ALTER TABLE  elab.examiner OWNER to   elab;
ALTER TABLE  elab.initial_assessment_note_type OWNER to   elab;
ALTER TABLE  elab.note OWNER to   elab;
ALTER TABLE  elab.note_detail OWNER to   elab;
ALTER TABLE  elab.note_detail_item_style OWNER to   elab;
ALTER TABLE  elab.note_detail_k_item_type OWNER to   elab;
ALTER TABLE  elab.note_detail_q_item_type OWNER to   elab;
ALTER TABLE  elab.note_detail_tire_type OWNER to   elab;
ALTER TABLE  elab.note_detail_type OWNER to   elab;
ALTER TABLE  elab.note_detail_vehicle_position OWNER to   elab;
ALTER TABLE  elab.note_method OWNER to   elab;
ALTER TABLE  elab.note_request_type OWNER to   elab;
ALTER TABLE  elab.note_type OWNER to   elab;

