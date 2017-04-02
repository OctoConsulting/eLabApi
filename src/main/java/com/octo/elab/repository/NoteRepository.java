package com.octo.elab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.octo.elab.pojo.db.Note;

public interface NoteRepository extends JpaRepository<Note, Integer> {

	@Query(value = "select n.* from elab.note n where (n._id = :id)", nativeQuery = true)
	public Note getNoteByID(@Param("id") Integer id);

	@Query(value = "select n.* from elab.note n order by n._id", nativeQuery = true)
	public List<Note> getAllNotes();

	@Query(value = "select n._id from elab.note n order by n.id", nativeQuery = true)
	public Integer[] getAllNoteIDs();

	@Query(value = "select max(n._id) from elab.note n", nativeQuery = true)
	public Integer getMaxNoteID();

}