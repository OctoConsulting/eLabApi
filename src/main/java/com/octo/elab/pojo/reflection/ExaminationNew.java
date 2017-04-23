package com.octo.elab.pojo.reflection;

import java.util.List;

public class ExaminationNew {

	private List<AccessPair> examType = null;
	private List<AccessPair> examiners = null;
	private List<AccessPair> evidences = null;
	
	public List<AccessPair> getExamType() {
		return examType;
	}
	
	
	public void setExamType(List<AccessPair> examType) {
		this.examType = examType;
	}
	public List<AccessPair> getExaminers() {
		return examiners;
	}
	public void setExaminers(List<AccessPair> examiners) {
		this.examiners = examiners;
	}
	
	public List<AccessPair> getEvidences() {
		return evidences;
	}
	public void setEvidences(List<AccessPair> evidences) {
		this.evidences = evidences;
	}
	
	
	
	
}