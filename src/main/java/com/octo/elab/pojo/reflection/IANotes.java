package com.octo.elab.pojo.reflection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.octo.elab.pojo.db.Note;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IANotes {

	Note initialAssessmentNote;
	KQNotes shoeNote;
	KQNotes tireNote;

	public Note getInitialAssessmentNote() {
		return initialAssessmentNote;
	}

	public void setInitialAssessmentNote(Note initialAssessmentNote) {
		this.initialAssessmentNote = initialAssessmentNote;
	}

	public KQNotes getShoeNote() {
		return shoeNote;
	}

	public void setShoeNote(KQNotes shoeNote) {
		this.shoeNote = shoeNote;
	}

	public KQNotes getTireNote() {
		return tireNote;
	}

	public void setTireNote(KQNotes tireNote) {
		this.tireNote = tireNote;
	}

}