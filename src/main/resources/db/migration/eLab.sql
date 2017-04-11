
-- Schema creation
CREATE schema  "eLab" authorization  "eLab";
//--
CREATE SEQUENCE  "eLab".case_id_seq;
CREATE TABLE  "eLab".case (
  id INTEGER NOT NULL DEFAULT nextval(' "eLab".case_id_seq'),
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
  created_by VARCHAR(100) DEFAULT ' "eLab"' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT ' "eLab"' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT evidence_type_PK PRIMARY KEY (id)
);

CREATE SEQUENCE  "eLab".evidence_id_seq;
CREATE TABLE  "eLab".evidence (
  id INTEGER NOT NULL DEFAULT nextval(' "eLab".evidence_id_seq'),
  case_id INTEGER references  "eLab".case(id) NOT NULL,
  evidence_name VARCHAR(80) NOT NULL,
  evidence_desc VARCHAR(4000) NOT NULL,
  evidence_type INTEGER references  "eLab".evidence_type(id) NOT NULL,
  is_forAnalysis BOOLEAN DEFAULT true NOT NULL,
  is_verified BOOLEAN DEFAULT true NOT NULL,
  created_by VARCHAR(100) DEFAULT 'RMS' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'RMS' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT evidence_PK PRIMARY KEY (id)
);
ALTER SEQUENCE  "eLab".evidence_id_seq OWNED BY  "eLab".evidence.id;

CREATE TABLE  "eLab".exam_type (
  id INTEGER NOT NULL UNIQUE,
  description VARCHAR(80) UNIQUE NOT NULL,
  is_active BOOLEAN DEFAULT true NOT NULL,
  created_by VARCHAR(100) DEFAULT ' "eLab"' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT ' "eLab"' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT exam_type_PK PRIMARY KEY (id)
);

CREATE TABLE  "eLab".examiner (
  id INTEGER NOT NULL UNIQUE,
  examiner_name VARCHAR(500) UNIQUE NOT NULL,
  is_active BOOLEAN DEFAULT true NOT NULL,
  created_by VARCHAR(100) DEFAULT ' "eLab"' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT ' "eLab"' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT examiner_PK PRIMARY KEY (id)
);

CREATE SEQUENCE  "eLab".exam_id_seq;
CREATE TABLE  "eLab".exam (
  id INTEGER NOT NULL DEFAULT nextval(' "eLab".exam_id_seq'),
  case_id INTEGER references  "eLab".case(id) NOT NULL,
  evidence_id INTEGER references  "eLab".evidence(id) NOT NULL,
  exam_name VARCHAR(80) NOT NULL,
  exam_type INTEGER references  "eLab".exam_type(id) NOT NULL,
  examiner_id INTEGER references  "eLab".examiner(id) NOT NULL,
  note_id INTEGER NOT NULL,
  assigned_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  start_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  end_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  created_by VARCHAR(100) DEFAULT 'RMS' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'RMS' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT exam_PK PRIMARY KEY (id)
);
ALTER SEQUENCE  "eLab".exam_id_seq OWNED BY  "eLab".exam.id;

INSERT INTO ""eLab""."case"(
  id, lab_no, status, opened_datetime, violation, violation_datetime, is_active)
  VALUES (1,1, 'In Progress', '2017-04-11', 'Violation', '2017-04-11', true);

  INSERT INTO ""eLab"".evidence_type(
  id, description, is_active)
  VALUES (1, 'Container', true);
INSERT INTO ""eLab"".evidence_type(
  id, description, is_active)
  VALUES (2, 'Package', true);
INSERT INTO ""eLab"".evidence_type(
  id, description, is_active)
  VALUES (3, 'Item', true);


  INSERT INTO ""eLab"".evidence(
  id, case_id, evidence_name, evidence_desc, evidence_type, is_foranalysis, is_verified)
  VALUES (1, 1, 'Test Evidence I', 'Test Evidence Description I', 1, true, true);
INSERT INTO ""eLab"".evidence(
  id, case_id, evidence_name, evidence_desc, evidence_type, is_foranalysis, is_verified)
  VALUES (2, 1, 'Test Evidence II', 'Test Evidence Description II', 1, true, true);
INSERT INTO ""eLab"".evidence(
  id, case_id, evidence_name, evidence_desc, evidence_type, is_foranalysis, is_verified)
  VALUES (3, 1, 'Test Evidence III', 'Test Evidence Description III', 1, true, true);

  INSERT INTO ""eLab"".exam_type(
  id, description, is_active)
  VALUES (1, 'Shoe Prints/Tire Thread',true);

  INSERT INTO ""eLab"".examiner(
  id, examiner_name, is_active)
  VALUES (1, 'Nithin', true);
INSERT INTO ""eLab"".examiner(
  id, examiner_name, is_active)
  VALUES (2, 'Akanksha', true);
INSERT INTO ""eLab"".examiner(
  id, examiner_name, is_active)
  VALUES (3, 'Sumit', true);

  INSERT INTO ""eLab"".exam(
  id, case_id, evidence_id, exam_name, exam_type, examiner_id,note_id)
  VALUES (1, 1, 1, 'Test Exam', 1, 1,1);



-- Index Creation
Create unique index IX_case on  "eLab".case (id);
Create unique index IX_evidence on  "eLab".case (id);
Create unique index IX_exam on  "eLab".case (id);

-- Owner assignment for tables
ALTER TABLE  "eLab".evidence_type OWNER to  "eLab";
ALTER TABLE  "eLab".exam_type OWNER to  "eLab";
ALTER TABLE  "eLab".examiner OWNER to  "eLab";
ALTER TABLE  "eLab".case OWNER to  "eLab";
ALTER TABLE  "eLab".evidence OWNER to  "eLab";
ALTER TABLE  "eLab".exam OWNER to  "eLab";
