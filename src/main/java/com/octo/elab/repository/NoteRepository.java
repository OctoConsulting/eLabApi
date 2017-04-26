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
}
