package com.octo.elab.pojo.db;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.octo.elab.utilities.CustomDateParser;

@Entity
@Table(name = "examiner")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Examiner extends ResourceSupport {

	@Id
	@Column(name = "id")
	@JsonProperty("id")
	private Integer examinerId;

	@Column(name = "examiner_name")
	private String examinerName;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "created_by")
	@JsonIgnore
	private String createdBy;

	@Column(name = "created_date")
	@JsonIgnore
	private Timestamp createdDate;

	@Column(name = "updated_by")
	@JsonIgnore
	private String updatedBy;

	@Column(name = "updated_date")
	@JsonIgnore
	private Timestamp updatedDate;

	public Integer getExaminerId() {
		return examinerId;
	}

	public void setExaminerId(Integer examinerId) {
		this.examinerId = examinerId;
	}

	public String getExaminerName() {
		return examinerName;
	}

	public void setExaminerName(String examinerName) {
		this.examinerName = examinerName;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return CustomDateParser.parseDate(createdDate.getTime());
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

	public String getUpdatedDate() {
		return CustomDateParser.parseDate(updatedDate.getTime());
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

}