package com.octo.elab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.octo.elab.pojo.db.NoteDetailEvidence;

public interface NoteDetailEvidenceRepository extends JpaRepository<NoteDetailEvidence, Integer> {

	@Query(value = "select nd.* from elab.note_detail_evidence nd where (nd._id = :id)", nativeQuery = true)
	public NoteDetailEvidence getNoteDetailEvidenceByID(@Param("id") Integer id);

	@Query(value = "select nd.* from elab.note_detail_evidence nd order by nd._id", nativeQuery = true)
	public List<NoteDetailEvidence> getAllNoteDetailEvidences();

	@Query(value = "select nd._id from elab.note_detail_evidence nd order by nd.id", nativeQuery = true)
	public Integer[] getAllNoteDetailEvidenceIDs();

	@Query(value = "select max(nd._id) from elab.note_detail_evidence nd", nativeQuery = true)
	public Integer getMaxNoteDetailEvidenceID();

}