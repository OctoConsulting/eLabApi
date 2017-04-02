package com.octo.elab.pojo.db;
// default package

// Generated Apr 1, 2017 10:50:57 PM by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * NoteDetailEvidence generated by hbm2java
 */
@Entity
@Table(name = "note_detail_evidence")
public class NoteDetailEvidence implements java.io.Serializable {

	private int id;
	private Evidence evidence;
	private NoteDetail noteDetail;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;

	public NoteDetailEvidence() {
	}

	public NoteDetailEvidence(int id, Evidence evidence, NoteDetail noteDetail, String createdBy, Date createdDate,
			String updatedBy, Date updatedDate) {
		this.id = id;
		this.evidence = evidence;
		this.noteDetail = noteDetail;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
	}

	@Id

	@Column(name = "_id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "evidence_id", nullable = false)
	public Evidence getEvidence() {
		return this.evidence;
	}

	public void setEvidence(Evidence evidence) {
		this.evidence = evidence;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "note_detail_id", nullable = false)
	public NoteDetail getNoteDetail() {
		return this.noteDetail;
	}

	public void setNoteDetail(NoteDetail noteDetail) {
		this.noteDetail = noteDetail;
	}

	@Column(name = "created_by", nullable = false, length = 100)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = false, length = 8)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "updated_by", nullable = false, length = 100)
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date", nullable = false, length = 8)
	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}
