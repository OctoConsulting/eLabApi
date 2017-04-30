package com.octo.elab.pojo.reflection;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.octo.elab.pojo.db.Exam;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExamNotes {

	Exam exam;
	List<IKQNotes> shoeNotes;
	List<IKQNotes> tireNotes;

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public List<IKQNotes> getShoeNotes() {
		return shoeNotes;
	}

	public void setShoeNotes(List<IKQNotes> shoeNotes) {
		this.shoeNotes = shoeNotes;
	}

	public List<IKQNotes> getTireNotes() {
		return tireNotes;
	}

	public void setTireNotes(List<IKQNotes> tireNotes) {
		this.tireNotes = tireNotes;
	}

	
	

}