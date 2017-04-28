package com.octo.elab.pojo.db;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.octo.elab.pojo.jsonconverter.NoteDataConverter;
import com.octo.elab.pojo.reflection.NoteData;
import com.octo.elab.utilities.CustomDateTimeDeserializer;
import com.octo.elab.utilities.CustomDateTimeSerializer;

@Entity
@Table(name = "note")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Note {

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "case_id")
	private Integer caseId;

	@Column(name = "exam_id")
	private Integer examId;

	@Column(name = "marked_complete")
	private Boolean markedComplete;

	@Column(name = "note_type")
	private Integer noteType;

	@Column(name = "item_type")
	private String itemType;

	@Column(name = "evidences")
	private String evidences;

	@Column(name = "parent_id")
	private Integer parentId;

	@Column(name = "note_message")
	private String noteMessage;

	@Column(name = "label_info")
	private String labelInfo;

	@Convert(converter = NoteDataConverter.class)
	@Column(name = "note_data")
	private NoteData noteData;

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

	public Integer getCaseId() {
		return caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}

	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	public Boolean getMarkedComplete() {
		return markedComplete;
	}

	public void setMarkedComplete(Boolean markedComplete) {
		this.markedComplete = markedComplete;
	}

	public Integer getNoteType() {
		return noteType;
	}

	public void setNoteType(Integer noteType) {
		this.noteType = noteType;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getEvidences() {
		return evidences;
	}

	public void setEvidences(String evidences) {
		this.evidences = evidences;
	}

	public String getNoteMessage() {
		return noteMessage;
	}

	public void setNoteMessage(String noteMessage) {
		this.noteMessage = noteMessage;
	}
	
	public String getLabelInfo() {
		return labelInfo;
	}

	public void setLabelInfo(String labelInfo) {
		this.labelInfo = labelInfo;
	}

	public NoteData getNoteData() {
		return noteData;
	}

	public void setNoteData(NoteData noteData) {
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