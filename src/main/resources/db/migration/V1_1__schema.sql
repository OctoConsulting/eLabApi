
-- Schema creation
CREATE schema elab authorization elab;

--Lookup Tables Start--
CREATE TABLE elab.evidence_type (
  id INTEGER NOT NULL UNIQUE,
  description VARCHAR(80) UNIQUE NOT NULL,
  is_active BOOLEAN DEFAULT true NOT NULL,
  created_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT evidence_type_PK PRIMARY KEY (id)
);

CREATE TABLE elab.exam_type (
  id INTEGER NOT NULL UNIQUE,
  description VARCHAR(80) UNIQUE NOT NULL,
  is_active BOOLEAN DEFAULT true NOT NULL,
  created_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT exam_type_PK PRIMARY KEY (id)
);

CREATE TABLE elab.examiner (
  id INTEGER NOT NULL UNIQUE,
  examiner_name VARCHAR(500) UNIQUE NOT NULL,
  is_active BOOLEAN DEFAULT true NOT NULL,
  created_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT examiner_PK PRIMARY KEY (id)
);

CREATE TABLE elab.note_type (
  id INTEGER NOT NULL UNIQUE,
  description VARCHAR(80) UNIQUE NOT NULL,
  is_active BOOLEAN DEFAULT true NOT NULL,
  created_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_type_PK PRIMARY KEY (id)
);

CREATE TABLE elab.note_detail_type (
  id INTEGER NOT NULL UNIQUE,
  description VARCHAR(80) UNIQUE NOT NULL,
  is_active BOOLEAN DEFAULT true NOT NULL,
  created_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'eLab' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_detail_type_PK PRIMARY KEY (id)
);
--Lookup Tables End--

--Transactional Tables Start--

CREATE SEQUENCE elab.case_id_seq;
CREATE TABLE elab.case (
  id INTEGER NOT NULL DEFAULT nextval('elab.case_id_seq'),
  case_data json NOT NULL,
  is_active BOOLEAN DEFAULT true NOT NULL,
  created_by VARCHAR(100) DEFAULT 'RMS' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'RMS' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT case_PK PRIMARY KEY (id)
);
ALTER SEQUENCE elab.case_id_seq OWNED BY elab.case.id;

CREATE SEQUENCE elab.evidence_id_seq;
CREATE TABLE elab.evidence (
  id INTEGER NOT NULL DEFAULT nextval('elab.evidence_id_seq'),
  case_id INTEGER references elab.case(id) NOT NULL,
  evidence_name VARCHAR(80) NOT NULL,
  evidence_desc VARCHAR(4000) NOT NULL,
  evidence_type INTEGER references elab.evidence_type(id) NOT NULL,
  is_forAnalysis BOOLEAN DEFAULT true NOT NULL,
  is_verified BOOLEAN DEFAULT true NOT NULL,
  created_by VARCHAR(100) DEFAULT 'RMS' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'RMS' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT evidence_PK PRIMARY KEY (id)
);
ALTER SEQUENCE elab.evidence_id_seq OWNED BY elab.evidence.id;

CREATE SEQUENCE elab.exam_id_seq;
CREATE TABLE elab.exam (
  id INTEGER NOT NULL DEFAULT nextval('elab.exam_id_seq'),
  case_id INTEGER references elab.case(id) NOT NULL,
  exam_name VARCHAR(80) NOT NULL,
  exam_type INTEGER references elab.exam_type(id) NOT NULL,
  examiner_id INTEGER references elab.examiner(id) NOT NULL,
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
ALTER SEQUENCE elab.exam_id_seq OWNED BY elab.exam.id;

CREATE SEQUENCE elab.note_id_seq;
CREATE TABLE elab.note (
  id INTEGER NOT NULL DEFAULT nextval('elab.note_id_seq'),
  exam_id INTEGER references elab.exam(id) NOT NULL,
  exam_sub_type VARCHAR(80) NOT NULL,
  note_type INTEGER references elab.note_type(id) NOT NULL,
  note_data json NOT NULL,
  created_by VARCHAR(100) DEFAULT 'RMS' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'RMS' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_PK PRIMARY KEY (id)
);
ALTER SEQUENCE elab.note_id_seq OWNED BY elab.note.id;

CREATE SEQUENCE elab.note_detail_id_seq;
CREATE TABLE elab.note_detail (
  id INTEGER NOT NULL DEFAULT nextval('elab.note_detail_id_seq'),
  note_id INTEGER NOT NULL references elab.note(id),
  note_detail_type INTEGER references elab.note_detail_type(id) NOT NULL,
  note_detail_data json NOT NULL,
  created_by VARCHAR(100) DEFAULT 'RMS' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'RMS' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_detail_PK PRIMARY KEY (id)
);
ALTER SEQUENCE elab.note_detail_id_seq OWNED BY elab.note_detail.id;

CREATE SEQUENCE elab.note_evidence_id_seq;
CREATE TABLE elab.note_evidence (
  id INTEGER NOT NULL DEFAULT nextval('elab.note_evidence_id_seq'),
  note_id INTEGER references elab.note(id) NOT NULL,
  evidence_id INTEGER references elab.evidence(id) NOT NULL,
  created_by VARCHAR(100) DEFAULT 'RMS' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'RMS' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT note_evidence_PK PRIMARY KEY (id)
);
ALTER SEQUENCE elab.note_evidence_id_seq OWNED BY elab.note_evidence.id;

CREATE SEQUENCE elab.image_id_seq;
CREATE TABLE elab.image (
  id INTEGER NOT NULL DEFAULT nextval('elab.image_id_seq'),
  note_id INTEGER references elab.note(id) NOT NULL,
  image bytea,
  created_by VARCHAR(100) DEFAULT 'RMS' NOT NULL,
  created_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  updated_by VARCHAR(100) DEFAULT 'RMS' NOT NULL,
  updated_date TIMESTAMP WITHOUT TIME ZONE DEFAULT clock_timestamp() NOT NULL,
  CONSTRAINT image_PK PRIMARY KEY (id)
);
ALTER SEQUENCE elab.image_id_seq OWNED BY elab.image.id;

--Transactional Tables End--

-- Index Creation
Create unique index IX_case on elab.case (id);
Create unique index IX_evidence on elab.case (id);
Create unique index IX_exam on elab.case (id);
Create unique index IX_note on elab.case (id);
Create unique index IX_note_detail on elab.case (id);
Create unique index IX_note_evidence on elab.note_evidence (id);
Create unique index IX_image on elab.image (id);

-- Owner assignment for tables
ALTER TABLE elab.evidence_type OWNER to elab;
ALTER TABLE elab.exam_type OWNER to elab;
ALTER TABLE elab.examiner OWNER to elab;
ALTER TABLE elab.note_type OWNER to elab;
ALTER TABLE elab.note_detail_type OWNER to elab;
ALTER TABLE elab.case OWNER to elab;
ALTER TABLE elab.evidence OWNER to elab;
ALTER TABLE elab.exam OWNER to elab;
ALTER TABLE elab.note OWNER to elab;
ALTER TABLE elab.note_detail OWNER to elab;
ALTER TABLE elab.note_evidence OWNER to elab;
ALTER TABLE elab.image OWNER to elab;