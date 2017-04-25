package com.octo.elab.pojo.reflection;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.octo.elab.utilities.CustomDateTimeDeserializer;
import com.octo.elab.utilities.CustomDateTimeSerializer;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExaminationNew {

	private List<AccessPair> examType = null;
	private List<AccessPair> examiners = null;
	private List<AccessPair> evidences = null;
	private String name;
	private Timestamp assignedDate;
	private Timestamp startDate;
	private Timestamp endDate;
	private Integer _id;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonSerialize(using = CustomDateTimeSerializer.class)
	@JsonDeserialize(using = CustomDateTimeDeserializer.class)
	public Timestamp getAssignedDate() {
		return assignedDate;
	}

	@JsonSerialize(using = CustomDateTimeSerializer.class)
	@JsonDeserialize(using = CustomDateTimeDeserializer.class)
	public void setAssignedDate(Timestamp assignedDate) {
		this.assignedDate = assignedDate;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

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

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}
	
	
	
	
}