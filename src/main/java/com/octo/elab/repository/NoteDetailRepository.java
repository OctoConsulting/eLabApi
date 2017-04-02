package com.octo.elab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.octo.elab.pojo.db.NoteDetail;

public interface NoteDetailRepository extends JpaRepository<NoteDetail, Integer> {

	@Query(value = "select nd.* from elab.note_detail nd where (nd._id = :id)", nativeQuery = true)
	public NoteDetail getNoteDetailByID(@Param("id") Integer id);

	@Query(value = "select nd.* from elab.note_detail nd order by nd._id", nativeQuery = true)
	public List<NoteDetail> getAllNoteDetails();

	@Query(value = "select nd._id from elab.note_detail nd order by nd.id", nativeQuery = true)
	public Integer[] getAllNoteDetailIDs();

	@Query(value = "select max(nd._id) from elab.note_detail nd", nativeQuery = true)
	public Integer getMaxNoteDetailID();

}