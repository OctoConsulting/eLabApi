package com.octo.elab.pojo.db;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.octo.elab.utilities.CustomDateTimeDeserializer;
import com.octo.elab.utilities.CustomDateTimeSerializer;

@Entity
@Table(name = "evidence")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Evidence {

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "case_id")
	private Integer caseId;

	@Column(name = "evidence_name")
	private String evidenceName;

	@Column(name = "evidence_type")
	private Integer evidenceType;

	@Column(name = "is_foranalysis")
	private Boolean isForAnalysis;

	@Column(name = "parent_id")
	private Integer parentId;

	@Column(name = "_id")
	private Integer _id;

	@Column(name = "created_by")
	private String createdBy;

	@Transient
	private String description;

	@Transient
	private Integer parent_id;

	@Transient
	private Integer parentType;
	
	@Transient
	private Boolean selected;
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

	public String getEvidenceName() {
		return evidenceName;
	}

	public void setEvidenceName(String evidenceName) {
		this.evidenceName = evidenceName;
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

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

	public Integer getParentType() {
		return parentType;
	}

	public void setParentType(Integer parentType) {
		this.parentType = parentType;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	
}