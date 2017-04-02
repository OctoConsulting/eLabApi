package com.octo.elab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.octo.elab.pojo.db.NoteDetailType;

public interface NoteDetailTypeRepository extends JpaRepository<NoteDetailType, Integer> {

	@Query(value = "select nd.* from elab.note_detail_type nd where (nd._id = :id)", nativeQuery = true)
	public NoteDetailType getNoteDetailTypeByID(@Param("id") Integer id);

	@Query(value = "select nd.* from elab.note_detail_type nd order by nd._id", nativeQuery = true)
	public List<NoteDetailType> getAllNoteDetailTypes();

	@Query(value = "select nd._id from elab.note_detail_type nd order by nd.id", nativeQuery = true)
	public Integer[] getAllNoteDetailTypeIDs();

	@Query(value = "select max(nd._id) from elab.note_detail_type nd", nativeQuery = true)
	public Integer getMaxNoteDetailTypeID();

}
