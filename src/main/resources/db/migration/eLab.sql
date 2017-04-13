
-- Schema creation
CREATE schema  "eLab" authorization  "eLab";

CREATE SEQUENCE  "eLab".initial_assessment_note_type_id_seq;
CREATE TABLE  "eLab".initial_assessment_note_type (
  id INTEGER NOT NULL DEFAULT nextval('"eLab".initial_assessment_note_type_id_seq'),
  name VARCHAR(100) NOT NULL,
  created_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT initial_assessment_note_type_PK PRIMARY KEY (id)
);
ALTER SEQUENCE  "eLab".initial_assessment_note_type_id_seq OWNED BY  "eLab".initial_assessment_note_type.id;

CREATE SEQUENCE  "eLab".note_request_type_id_seq;
CREATE TABLE  "eLab".note_request_type (
  id INTEGER NOT NULL DEFAULT nextval('"eLab".note_request_type_id_seq'),
  name VARCHAR(100) NOT NULL,
  created_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_request_type_PK PRIMARY KEY (id)
);
ALTER SEQUENCE  "eLab".note_request_type_id_seq OWNED BY  "eLab".note_request_type.id;

CREATE SEQUENCE  "eLab".note_method_id_seq;
CREATE TABLE  "eLab".note_method (
  id INTEGER NOT NULL DEFAULT nextval('"eLab".note_method_id_seq'),
  name VARCHAR(100) NOT NULL,
  created_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_method_PK PRIMARY KEY (id)
);
ALTER SEQUENCE  "eLab".note_method_id_seq OWNED BY  "eLab".note_method.id;



CREATE SEQUENCE  "eLab".note_detail_item_style_id_seq;
CREATE TABLE  "eLab".note_detail_item_style (
  id INTEGER NOT NULL DEFAULT nextval('"eLab".note_detail_item_style_id_seq'),
  name VARCHAR(100) NOT NULL,
  created_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_detail_item_style_PK PRIMARY KEY (id)
);
ALTER SEQUENCE  "eLab".note_detail_item_style_id_seq OWNED BY  "eLab".note_detail_item_style.id;

CREATE SEQUENCE  "eLab".note_detail_vehicle_position_id_seq;
CREATE TABLE  "eLab".note_detail_vehicle_position (
  id INTEGER NOT NULL DEFAULT nextval('"eLab".note_detail_vehicle_position_id_seq'),
  name VARCHAR(100) NOT NULL,
  created_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_detail_vehicle_position_PK PRIMARY KEY (id)
);
ALTER SEQUENCE  "eLab".note_detail_vehicle_position_id_seq OWNED BY  "eLab".note_detail_vehicle_position.id;

CREATE SEQUENCE  "eLab".note_detail_tire_type_id_seq;
CREATE TABLE  "eLab".note_detail_tire_type (
  id INTEGER NOT NULL DEFAULT nextval('"eLab".note_detail_tire_type_id_seq'),
  name VARCHAR(100) NOT NULL,
  created_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_detail_tire_type_PK PRIMARY KEY (id)
);
ALTER SEQUENCE  "eLab".note_detail_tire_type_id_seq OWNED BY  "eLab".note_detail_tire_type.id;

CREATE SEQUENCE  "eLab".note_detail_q_item_type_id_seq;
CREATE TABLE  "eLab".note_detail_q_item_type (
  id INTEGER NOT NULL DEFAULT nextval('"eLab".note_detail_q_item_type_id_seq'),
  name VARCHAR(100) NOT NULL,
  created_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_detail_q_item_type_PK PRIMARY KEY (id)
);
ALTER SEQUENCE  "eLab".note_detail_q_item_type_id_seq OWNED BY  "eLab".note_detail_q_item_type.id;


CREATE SEQUENCE  "eLab".note_detail_k_item_type_id_seq;
CREATE TABLE  "eLab".note_detail_k_item_type (
  id INTEGER NOT NULL DEFAULT nextval('"eLab".note_detail_k_item_type_id_seq'),
  name VARCHAR(100) NOT NULL,
  created_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_detail_k_item_type_PK PRIMARY KEY (id)
);
ALTER SEQUENCE  "eLab".note_detail_k_item_type_id_seq OWNED BY  "eLab".note_detail_k_item_type.id;

CREATE SEQUENCE  "eLab".case_id_seq;
CREATE TABLE  "eLab".case (
  id INTEGER NOT NULL DEFAULT nextval('"eLab".case_id_seq'),
  lab_no INTEGER NOT NULL,
  status VARCHAR(100) NOT NULL,
  opened_datetime TIMESTAMP WITHOUT TIME ZONE,
  violation VARCHAR(100) NOT NULL,
  violation_datetime TIMESTAMP WITHOUT TIME ZONE,
  is_active BOOLEAN DEFAULT true NOT NULL,
  created_by VARCHAR(100) DEFAULT 'RMS' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'RMS' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT case_PK PRIMARY KEY (id)
);
ALTER SEQUENCE  "eLab".case_id_seq OWNED BY  "eLab".case.id;

CREATE TABLE  "eLab".evidence_type (
  id INTEGER NOT NULL UNIQUE,
  description VARCHAR(80) UNIQUE NOT NULL,
  is_active BOOLEAN DEFAULT true NOT NULL,
  created_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT evidence_type_PK PRIMARY KEY (id)
);

CREATE SEQUENCE  "eLab".evidence_id_seq;
CREATE TABLE  "eLab".evidence (
  id INTEGER NOT NULL DEFAULT nextval('"eLab".evidence_id_seq'),
  case_id INTEGER references  "eLab".case(id) NOT NULL,
  evidence_name VARCHAR(80) NOT NULL,
  evidence_desc VARCHAR(4000) NOT NULL,
  evidence_type INTEGER references  "eLab".evidence_type(id) NOT NULL,
  is_forAnalysis BOOLEAN DEFAULT true NOT NULL,
  is_verified BOOLEAN DEFAULT true NOT NULL,
  item_type character varying(20) ,
  identifier character varying(20) ,
  container_id INTEGER,
  package_id INTEGER,
  created_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT evidence_PK PRIMARY KEY (id)
);
ALTER SEQUENCE  "eLab".evidence_id_seq OWNED BY  "eLab".evidence.id;

CREATE TABLE  "eLab".exam_type (
  id INTEGER NOT NULL UNIQUE,
  description VARCHAR(80) UNIQUE NOT NULL,
  is_active BOOLEAN DEFAULT true NOT NULL,
  created_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT exam_type_PK PRIMARY KEY (id)
);

CREATE TABLE  "eLab".examiner (
  id INTEGER NOT NULL UNIQUE,
  examiner_name VARCHAR(500) UNIQUE NOT NULL,
  is_active BOOLEAN DEFAULT true NOT NULL,
  created_by VARCHAR(100) DEFAULT 'eLab"' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT examiner_PK PRIMARY KEY (id)
);

CREATE SEQUENCE  "eLab".exam_id_seq;
CREATE TABLE  "eLab".exam (
  id INTEGER NOT NULL DEFAULT nextval('"eLab".exam_id_seq'),
  case_id INTEGER references  "eLab".case(id) NOT NULL,
  evidence_id INTEGER references  "eLab".evidence(id) NOT NULL,
  exam_name VARCHAR(80) NOT NULL,
  exam_type INTEGER references  "eLab".exam_type(id) NOT NULL,
  examiner_id INTEGER references  "eLab".examiner(id) NOT NULL,
  assigned_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  start_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  end_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  created_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT exam_PK PRIMARY KEY (id)
);
ALTER SEQUENCE  "eLab".exam_id_seq OWNED BY  "eLab".exam.id;

CREATE TABLE  "eLab".note_type (
  id INTEGER NOT NULL UNIQUE,
  description VARCHAR(80) UNIQUE NOT NULL,
  is_active BOOLEAN DEFAULT true NOT NULL,
  created_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_type_PK PRIMARY KEY (id)
);


CREATE SEQUENCE "eLab".note_id_seq;
CREATE TABLE "eLab".note (
  id INTEGER NOT NULL DEFAULT nextval('"eLab".note_id_seq'),
  exam_id INTEGER references "eLab".exam(id) NOT NULL,
  exam_sub_type VARCHAR(80) NOT NULL,
  note_type INTEGER references "eLab".note_type(id) NOT NULL,
  note_category VARCHAR(80) NOT NULL,
  initial_assessment_note_type_id INTEGER references "eLab".initial_assessment_note_type(id) NOT NULL,
  conducted_by VARCHAR(80) NOT NULL,
  request_type INTEGER references "eLab".note_request_type(id) NOT NULL,
  note_method  INTEGER references "eLab".note_method(id) NOT NULL,
  note_data VARCHAR(4000) NOT NULL,
  created_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_PK PRIMARY KEY (id)
);
ALTER SEQUENCE "eLab".note_id_seq OWNED BY "eLab".note.id;


CREATE TABLE "eLab".note_detail_type (
  id INTEGER NOT NULL UNIQUE,
  description VARCHAR(80) UNIQUE NOT NULL,
  is_active BOOLEAN DEFAULT true NOT NULL,
  created_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_detail_type_PK PRIMARY KEY (id)
);

CREATE SEQUENCE "eLab".note_detail_id_seq;
CREATE TABLE "eLab".note_detail (
  id INTEGER NOT NULL DEFAULT nextval('"eLab".note_detail_id_seq'),
 note_id INTEGER NOT NULL references "eLab".note(id),
  note_detail_type INTEGER references "eLab".note_detail_type(id) NOT NULL,
  note_detail_k_item_type_id INTEGER references "eLab".note_detail_k_item_type(id) NOT NULL,
  note_detail_q_item_type_id INTEGER references "eLab".note_detail_q_item_type(id) NOT NULL,
  note_detail_item_style_id INTEGER references "eLab".note_detail_item_style(id) NOT NULL,
  brand_name VARCHAR(100) NOT NULL,
  note_detail_size VARCHAR(100) NOT NULL,
  model VARCHAR(100) NOT NULL,
  dot_number VARCHAR(100) NOT NULL,
  note_detail_vehicle_position_id INTEGER references "eLab".note_detail_vehicle_position(id) NOT NULL,
  note_detail_tire_type_id INTEGER references "eLab".note_detail_tire_type(id) NOT NULL,
  note_detail_data VARCHAR(4000) NOT NULL,
  note_detail_lable_info VARCHAR(2000) NOT NULL,
  created_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_detail_PK PRIMARY KEY (id)
);
ALTER SEQUENCE "eLab".note_detail_id_seq OWNED BY "eLab".note_detail.id;

ALTER TABLE "eLab".note
    OWNER to "eLab";



INSERT INTO "eLab"."case"(
  id, lab_no, status, opened_datetime, violation, violation_datetime, is_active)
  VALUES (1,1, 'In Progress', '2017-04-11', 'Violation', '2017-04-11', true);

  INSERT INTO "eLab".evidence_type(
  id, description, is_active)
  VALUES (1, 'Container', true);
INSERT INTO "eLab".evidence_type(
  id, description, is_active)
  VALUES (2, 'Package', true);
INSERT INTO "eLab".evidence_type(
  id, description, is_active)
  VALUES (3, 'Item', true);


  INSERT INTO "eLab".exam_type(
  id, description, is_active)
  VALUES (1, 'Shoe Prints/Tire Thread',true);

  INSERT INTO "eLab".examiner(
  id, examiner_name, is_active)
  VALUES (1, 'Nithin', true);
INSERT INTO "eLab".examiner(
  id, examiner_name, is_active)
  VALUES (2, 'Akanksha', true);
INSERT INTO "eLab".examiner(
  id, examiner_name, is_active)
  VALUES (3, 'Sumit', true);
    INSERT INTO "eLab".examiner(
  id, examiner_name, is_active)
  VALUES (4, 'Juliette Fitzsimmons', true);
      INSERT INTO "eLab".examiner(
  id, examiner_name, is_active)
  VALUES (5, 'Erik', true);
   INSERT INTO "eLab".examiner(
  id, examiner_name, is_active)
  VALUES (6, 'Marcus Stanton', true);

  INSERT INTO "eLab".note_type(
  id, description, is_active)
  VALUES (1, 'Initial Assessment', true);

  INSERT INTO "eLab".note_detail_type(
  id, description, is_active)
  VALUES (1, 'K Item Detail', true);
    
    INSERT INTO "eLab".note_detail_type(
  id, description, is_active)
  VALUES (2, 'Q Item Detail', true);

  INSERT INTO "eLab".note_detail_k_item_type(
  id, name)
  VALUES (1, 'Original Footwear');
      
    INSERT INTO "eLab".note_detail_k_item_type(
  id, name)
  VALUES (2, 'Footwear test impression');
      
    INSERT INTO "eLab".note_detail_k_item_type(
  id, name)
  VALUES (3, 'Photo/Printouts');
      
    INSERT INTO "eLab".note_detail_k_item_type(
  id, name)
  VALUES (4, 'Disc');
    INSERT INTO "eLab".note_detail_k_item_type(
  id, name)
  VALUES (5, 'Digital Image');
    INSERT INTO "eLab".note_detail_k_item_type(
  id, name)
  VALUES (6, 'Other');
    
    INSERT INTO "eLab".note_detail_item_style(
  id, name)
  VALUES (1, 'Shoe');
    INSERT INTO "eLab".note_detail_item_style(
  id, name)
  VALUES (2, 'Sandal');
     INSERT INTO "eLab".note_detail_item_style(
  id, name)
  VALUES (3, 'Boot');

    INSERT INTO "eLab".note_detail_vehicle_position(
  id, name)
  VALUES (1, 'Driver Front');
    INSERT INTO "eLab".note_detail_vehicle_position(
  id, name)
  VALUES (2, 'Passenger Front');
      INSERT INTO "eLab".note_detail_vehicle_position(
  id, name)
  VALUES (3, 'Driver Rear');
    INSERT INTO "eLab".note_detail_vehicle_position(
  id, name)
  VALUES (4, 'Passenger Rear');
     INSERT INTO "eLab".note_detail_vehicle_position(
  id, name)
  VALUES (5, 'Spare');
     INSERT INTO "eLab".note_detail_vehicle_position(
  id, name)
  VALUES (6, 'Unknown');

  

    INSERT INTO "eLab".note_detail_q_item_type(
  id, name)
  VALUES (1, 'Gel Lift');
      INSERT INTO "eLab".note_detail_q_item_type(
  id, name)
  VALUES (2, 'Original');
      INSERT INTO "eLab".note_detail_q_item_type(
  id, name)
  VALUES (3, 'Static Lift');
      INSERT INTO "eLab".note_detail_q_item_type(
  id, name)
  VALUES (4, 'Adhesive Lift');
      INSERT INTO "eLab".note_detail_q_item_type(
  id, name)
  VALUES (5, 'Case');
      INSERT INTO "eLab".note_detail_q_item_type(
  id, name)
  VALUES (6, 'Photo/printout');
       INSERT INTO "eLab".note_detail_q_item_type(
  id, name)
  VALUES (7, 'Disc');
       INSERT INTO "eLab".note_detail_q_item_type(
  id, name)
  VALUES (8, 'Digital Image');

  INSERT INTO "eLab".initial_assessment_note_type(
  id, name)
  VALUES (1, 'Footwear Comparison');
    INSERT INTO "eLab".initial_assessment_note_type(
  id, name)
  VALUES (2, 'Footwear make/model determination');
    INSERT INTO "eLab".initial_assessment_note_type(
  id, name)
  VALUES (3, 'Footwear size determination');
    INSERT INTO "eLab".initial_assessment_note_type(
  id, name)
  VALUES (4, 'Other');

          INSERT INTO "eLab".evidence(
  id, case_id, evidence_name, evidence_desc, evidence_type, is_verified, item_type, identifier)
  VALUES (1, 1, 'Box', 'evidence container desc Box', 1, true, '', '');
INSERT INTO "eLab".evidence(
  id, case_id, evidence_name, evidence_desc, evidence_type, is_verified, item_type, identifier,container_id)
  VALUES (2, 1, 'Paper Bag', 'evidence package desc Paper Bag', 2, true, '', '',1);
    INSERT INTO "eLab".evidence(
  id, case_id, evidence_name, evidence_desc, evidence_type, is_foranalysis, is_verified, item_type, identifier,container_id,package_id)
  VALUES (3, 1, 'Piece of Rubber', 'evidence item desc Piece of Rubber', 3, true, true, 'Q', 'Tire',1,2);
    INSERT INTO "eLab".evidence(
  id, case_id, evidence_name, evidence_desc, evidence_type, is_foranalysis, is_verified, item_type, identifier,container_id,package_id)
  VALUES (4, 1, 'Shoe Laces', 'evidence item desc I2', 3, true, true, '', 'Shoe',1,2);
      INSERT INTO "eLab".evidence(
  id, case_id, evidence_name, evidence_desc, evidence_type, is_foranalysis, is_verified, item_type, identifier,container_id,package_id)
  VALUES (5, 1, 'Left Nike Sneaker', 'evidence item desc I3', 3, true, true, 'K', 'Shoe',1,2);
      INSERT INTO "eLab".evidence(
  id, case_id, evidence_name, evidence_desc, evidence_type, is_foranalysis, is_verified, item_type, identifier,container_id,package_id)
  VALUES (6, 1, 'Tire Impression', 'evidence item desc I4', 3, true, true, '', 'Tire',1,2);
    INSERT INTO "eLab".evidence(
  id, case_id, evidence_name, evidence_desc, evidence_type, is_foranalysis, is_verified, item_type, identifier,container_id,package_id)
  VALUES (7, 1, 'Shoe Imprint', 'evidence item desc I4', 3, true, true, '', 'Shoe',1,2);

  INSERT INTO "eLab".exam(
  id, case_id, evidence_id, exam_name, exam_type, examiner_id)
  VALUES (1, 1, 5, 'Impressions/Imprints', 1, 4);
    INSERT INTO "eLab".exam(
  id, case_id, evidence_id, exam_name, exam_type, examiner_id)
  VALUES (2, 1, 3, 'Impressions/Imprints', 1, 4);




-- Owner assignment for tables
ALTER TABLE  "eLab".case OWNER to  "eLab";   
ALTER TABLE  "eLab".evidence OWNER to  "eLab";
ALTER TABLE  "eLab".evidence_type OWNER to   "eLab";
ALTER TABLE  "eLab".exam OWNER to   "eLab";
ALTER TABLE  "eLab".exam_type OWNER to   "eLab";
ALTER TABLE  "eLab".examiner OWNER to   "eLab";
ALTER TABLE  "eLab".initial_assessment_note_type OWNER to   "eLab";
ALTER TABLE  "eLab".note OWNER to   "eLab";
ALTER TABLE  "eLab".note_detail OWNER to   "eLab";
ALTER TABLE  "eLab".note_detail_item_style OWNER to   "eLab";
ALTER TABLE  "eLab".note_detail_k_item_type OWNER to   "eLab";
ALTER TABLE  "eLab".note_detail_q_item_type OWNER to   "eLab";
ALTER TABLE  "eLab".note_detail_tire_type OWNER to   "eLab";
ALTER TABLE  "eLab".note_detail_type OWNER to   "eLab";
ALTER TABLE  "eLab".note_detail_vehicle_position OWNER to   "eLab";
ALTER TABLE  "eLab".note_method OWNER to   "eLab";
ALTER TABLE  "eLab".note_request_type OWNER to   "eLab";
ALTER TABLE  "eLab".note_type OWNER to   "eLab";

