package com.octo.elab.pojo.reflection;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.octo.elab.pojo.db.Exam;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExamNotes {

	Exam exam;
	List<IANotes> initialAssessments;

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public List<IANotes> getInitialAssessments() {
		return initialAssessments;
	}

	public void setInitialAssessments(List<IANotes> initialAssessments) {
		this.initialAssessments = initialAssessments;
	}

}