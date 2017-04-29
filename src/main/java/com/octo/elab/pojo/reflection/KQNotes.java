package com.octo.elab.pojo.reflection;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.octo.elab.pojo.db.Note;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class KQNotes {

	List<Note> knowns;
	List<Note> questions;

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