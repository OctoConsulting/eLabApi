package com.octo.elab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.octo.elab.pojo.db.NoteDropDown;

public interface NoteDropDownRepository extends JpaRepository<NoteDropDown, Integer> {

	@Query(value = "select * from elab.note_dropdown n", nativeQuery = true)
	public List<NoteDropDown> getAllNoteDropDown();
}
