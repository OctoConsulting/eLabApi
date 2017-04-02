package com.octo.elab.pojo.db;
// default package

// Generated Apr 1, 2017 10:50:57 PM by Hibernate Tools 4.0.0

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * NoteDetail generated by hbm2java
 */
@Entity
@Table(name = "note_detail")
public class NoteDetail implements java.io.Serializable {

	private int id;
	private Note note;
	private NoteDetailType noteDetailType;
	private Serializable noteDetailData;
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private Set<NoteDetailEvidence> noteDetailEvidences = new HashSet<NoteDetailEvidence>(0);

	public NoteDetail() {
	}

	public NoteDetail(int id, Note note, NoteDetailType noteDetailType, Serializable noteDetailData, String createdBy,
			Date createdDate, String updatedBy, Date updatedDate) {
		this.id = id;
		this.note = note;
		this.noteDetailType = noteDetailType;
		this.noteDetailData = noteDetailData;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
	}

	public NoteDetail(int id, Note note, NoteDetailType noteDetailType, Serializable noteDetailData, String createdBy,
			Date createdDate, String updatedBy, Date updatedDate, Set<NoteDetailEvidence> noteDetailEvidences) {
		this.id = id;
		this.note = note;
		this.noteDetailType = noteDetailType;
		this.noteDetailData = noteDetailData;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.noteDetailEvidences = noteDetailEvidences;
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
	@JoinColumn(name = "note_id", nullable = false)
	public Note getNote() {
		return this.note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "note_detail_type", nullable = false)
	public NoteDetailType getNoteDetailType() {
		return this.noteDetailType;
	}

	public void setNoteDetailType(NoteDetailType noteDetailType) {
		this.noteDetailType = noteDetailType;
	}

	@Column(name = "note_detail_data", nullable = false)
	public Serializable getNoteDetailData() {
		return this.noteDetailData;
	}

	public void setNoteDetailData(Serializable noteDetailData) {
		this.noteDetailData = noteDetailData;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "noteDetail")
	public Set<NoteDetailEvidence> getNoteDetailEvidences() {
		return this.noteDetailEvidences;
	}

	public void setNoteDetailEvidences(Set<NoteDetailEvidence> noteDetailEvidences) {
		this.noteDetailEvidences = noteDetailEvidences;
	}

}
