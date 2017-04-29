package com.octo.elab.pojo.reflection;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.octo.elab.pojo.db.Note;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IKQNotes {

	Note initialAssessmentNote;
	List<Note> knowns;
	List<Note> questions;

	public Note getInitialAssessmentNote() {
		return initialAssessmentNote;
	}

	public void setInitialAssessmentNote(Note initialAssessmentNote) {
		this.initialAssessmentNote = initialAssessmentNote;
	}

	public List<Note> getKnowns() {
		return knowns;
	}

	public void setKnowns(List<Note> knowns) {
		this.knowns = knowns;
	}

	public List<Note> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Note> questions) {
		this.questions = questions;
	}

}