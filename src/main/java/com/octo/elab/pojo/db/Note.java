package com.octo.elab.pojo.db;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.octo.elab.utilities.CustomDateTimeDeserializer;
import com.octo.elab.utilities.CustomDateTimeSerializer;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Note {
	
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "exam_id")
	private Integer examId;
	
	@Column(name = "exam_sub_type")
	private String examSubType;
	
	@Column(name = "note_type")
	private Integer noteType;
	
	@Column(name = "note_category")
	private String noteCategory;
	
	@Column(name = "initial_assessment_note_type_id")
	private String initialAssessmentNoteTypeId;
	
	@Column(name = "conducted_by")
	private String conductedBy;
	
	@Column(name = "request_type")
	private String requestType;
	
	@Column(name = "note_method")
	private Integer note_method;
	
	@Column(name = "note_data")
	private String noteData;
	
	public Boolean getMarkedComplete() {
		return markedComplete;
	}

	public void setMarkedComplete(Boolean markedComplete) {
		this.markedComplete = markedComplete;
	}

	@Column(name = "marked_complete")
	private Boolean markedComplete;
	
	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	@JsonDeserialize(using = CustomDateTimeDeserializer.class)
	private Timestamp createdDate;

	@Column(name = "updated_by")
	private String updatedBy;
	
	@Column(name = "updated_date")
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	@JsonDeserialize(using = CustomDateTimeDeserializer.class)
	private Timestamp updatedDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	public String getExamSubType() {
		return examSubType;
	}

	public void setExamSubType(String examSubType) {
		this.examSubType = examSubType;
	}

	public Integer getNoteType() {
		return noteType;
	}

	public void setNoteType(Integer noteType) {
		this.noteType = noteType;
	}

	public String getNoteCategory() {
		return noteCategory;
	}

	public void setNoteCategory(String noteCategory) {
		this.noteCategory = noteCategory;
	}

	public String getInitialAssessmentNoteTypeId() {
		return initialAssessmentNoteTypeId;
	}

	public void setInitialAssessmentNoteTypeId(String initialAssessmentNoteTypeId) {
		this.initialAssessmentNoteTypeId = initialAssessmentNoteTypeId;
	}

	public String getConductedBy() {
		return conductedBy;
	}

	public void setConductedBy(String conductedBy) {
		this.conductedBy = conductedBy;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public Integer getNote_method() {
		return note_method;
	}

	public void setNote_method(Integer note_method) {
		this.note_method = note_method;
	}

	public String getNoteData() {
		return noteData;
	}

	public void setNoteData(String noteData) {
		this.noteData = noteData;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	
}