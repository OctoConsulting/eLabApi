package com.octo.elab.pojo.reflection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.octo.elab.pojo.db.Note;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IKQNotes {

	Note initialAssessmentNote;
	KQNotes kqNote;

	public Note getInitialAssessmentNote() {
		return initialAssessmentNote;
	}

	public void setInitialAssessmentNote(Note initialAssessmentNote) {
		this.initialAssessmentNote = initialAssessmentNote;
	}

	public KQNotes getKqNote() {
		return kqNote;
	}

	public void setKqNote(KQNotes kqNote) {
		this.kqNote = kqNote;
	}

}