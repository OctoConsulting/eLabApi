package com.octo.elab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.octo.elab.pojo.db.NoteDropDown;

public interface NoteDropDownRepository extends JpaRepository<NoteDropDown, Integer> {

	@Query(value = "select * from elab.note_dropdown n", nativeQuery = true)
	public List<NoteDropDown> getAllNoteDropDown();
	
	@Query(value = "select n.* from elab.note_dropdown n where n.label = :label",nativeQuery =true)
	public List<NoteDropDown> getNoteDropDownByLabel(@Param("label") String label);
	
	@Query(value = "select n.* from elab.note_dropdown n where n.label = :label and item_type = :type",nativeQuery =true)
	public List<NoteDropDown> getNoteDropDownByLabelAndType(@Param("label") String label,@Param("type") String type);
}
