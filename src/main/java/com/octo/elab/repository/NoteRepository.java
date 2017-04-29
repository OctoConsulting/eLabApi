package com.octo.elab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.octo.elab.pojo.db.Note;

public interface NoteRepository extends JpaRepository<Note, Integer> {

	@Query(value = "select * from elab.note n", nativeQuery = true)
	public List<Note> getAllNotes();
	
	@Query(value = "select * from elab.note n where (n.id = :noteID)", nativeQuery = true)
	public Note getNoteByID(@Param("noteID") Integer noteID);
	
	@Query(value = "select max(id) from elab.note", nativeQuery = true)
	public Integer getMaxNoteID();
	
	@Query(value = "select distinct(n.exam_id) from elab.note n where (n.case_id = :caseID)", nativeQuery = true)
	public List<Integer> getAllExamIDsByCaseID(@Param("caseID") Integer caseID);
	
	@Query(value = "select * from elab.note n where (n.exam_id = :examID) and note_type = 1 and item_type='shoe'", nativeQuery = true)
	public Note getShoeIANoteForExamID(@Param("examID") Integer examID);
	
	@Query(value = "select * from elab.note n where (n.exam_id = :examID) and note_type = 1 and item_type='tire'", nativeQuery = true)
	public Note getTireIANoteForExamID(@Param("examID") Integer examID);
	
	@Query(value = "select * from elab.note n where (n.parent_id = :parentID)", nativeQuery = true)
	public List<Note> getNoteDetailsByParentID(@Param("parentID") Integer parentID);
	
	@Query(value = "select evidences from elab.note n where (n.case_id = :caseID) and (n.exam_id = :examID)", nativeQuery = true)
	public List<String> getEvidenceIDsByCaseIDAndExamID(@Param("caseID") Integer caseID,@Param("examID") Integer examID);
	
	@Query(value = "select evidences from elab.note n where (n.case_id = :caseID) and (n.exam_id = :examID) and(n.note_type = :noteType) and (n.item_type = :itemType) ", nativeQuery = true)
	public List<String> getEvidenceIDsByCaseIDExamIDNoteTypeItemType(@Param("caseID") Integer caseID,@Param("examID") Integer examID,@Param("noteType") Integer noteType,@Param("itemType") String itemType);
	
	@Query(value = "select evidences from elab.note n where (n.case_id = :caseID) and (n.exam_id = :examID) and(n.note_type = :noteType) ", nativeQuery = true)
	public List<String> getEvidenceIDsByCaseIDExamIDNoteType(@Param("caseID") Integer caseID,@Param("examID") Integer examID,@Param("noteType") Integer noteType);
	
	@Query(value = "select evidences from elab.note n where (n.case_id = :caseID) and (n.exam_id = :examID) and (n.item_type = :itemType)", nativeQuery = true)
	public List<String> getEvidenceIDsByCaseIDExamIDItemType(@Param("caseID") Integer caseID,@Param("examID") Integer examID,@Param("itemType") String itemType);
}
