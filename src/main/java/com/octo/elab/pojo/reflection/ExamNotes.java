package com.octo.elab.pojo.reflection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.octo.elab.pojo.db.Exam;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExamNotes {

	Exam exam;
	IKQNotes shoeNotes;
	IKQNotes tireNotes;

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public IKQNotes getShoeNotes() {
		return shoeNotes;
	}

	public void setShoeNotes(IKQNotes shoeNotes) {
		this.shoeNotes = shoeNotes;
	}

	public IKQNotes getTireNotes() {
		return tireNotes;
	}

	public void setTireNotes(IKQNotes tireNotes) {
		this.tireNotes = tireNotes;
	}

	

}