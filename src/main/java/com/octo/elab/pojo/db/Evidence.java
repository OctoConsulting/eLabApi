package com.octo.elab.pojo.db;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "evidence")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Evidence {
	
	@Id
	@Column(name = "_id")
	@JsonProperty("id")
	private Integer ID;

	@Column(name = "case_id")
	private Integer caseId;

	@Column(name = "evidence_name")
	private String evidenceName;

	@Column(name = "evidence_number")
	private Integer evidenceNumber;

	@Column(name = "evidence_type")
	private Integer evidenceType;

	@Column(name = "is_foranalysis")
	private Boolean isForAnalysis;

	@Column(name = "parent_id")
	private Integer parentId;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "updated_date")
	private Timestamp updatedDate;


	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Integer getCaseId() {
		return caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}

	public String getEvidenceName() {
		return evidenceName;
	}

	public void setEvidenceName(String evidenceName) {
		this.evidenceName = evidenceName;
	}

	public Integer getEvidenceNumber() {
		return evidenceNumber;
	}

	public void setEvidenceNumber(Integer evidenceNumber) {
		this.evidenceNumber = evidenceNumber;
	}

	public Integer getEvidenceType() {
		return evidenceType;
	}

	public void setEvidenceType(Integer evidenceType) {
		this.evidenceType = evidenceType;
	}

	public Boolean getIsForAnalysis() {
		return isForAnalysis;
	}

	public void setIsForAnalysis(Boolean isForAnalysis) {
		this.isForAnalysis = isForAnalysis;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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