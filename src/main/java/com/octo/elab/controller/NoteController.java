package com.octo.elab.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.octo.elab.pojo.db.Note;
import com.octo.elab.pojo.db.Note;
import com.octo.elab.repository.NoteRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = ElabController.BasePath + "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Note", description = "Endpoint pertaining to Notes")
public class NoteController {

	private static final Logger log = LoggerFactory.getLogger(NoteController.class);

	@Resource
	Environment environment;

	@Autowired
	private NoteRepository noteRepo;

	/**
	 * This method is used to fetch all notes from database
	 *
	 * @return ResponseEntity<List<Note>>
	 */
	@RequestMapping(value = "/notes", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch all Notes")
	public ResponseEntity<List<Note>> getNotes() throws Exception {
		log.info("GET /notes API to fetch all notes");
		List<Note> notes = noteRepo.getAllNotes();
		return new ResponseEntity<List<Note>>(notes, HttpStatus.OK);
	}

	/**
	 * This method is used to supply an endpoint that returns a specified
	 * note information
	 *
	 * @param noteID
	 *            The id of the note to be retrieved
	 * @return ResponseEntity<Note>
	 */
	@RequestMapping(value = "/notes/{noteID}/", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch a note by ID")
	public ResponseEntity<Note> getNoteByID(
			@ApiParam(value = "noteID value", required = true) @PathVariable Integer noteID) throws Exception {
		log.info("GET /notes/" + noteID);
		Note note = noteRepo.getNoteByID(noteID);
		return new ResponseEntity<Note>(note, HttpStatus.OK);
	}
	
	/**
	 * This method is used to add note
	 * 
	 * @return ResponseEntity<String>
	 */
	@RequestMapping(value = "/notes/", method = RequestMethod.POST)
	@ApiOperation(value = "Add-Update a note")
	public ResponseEntity<String> updateNote(@RequestBody Note note) throws Exception {
		log.info("POST /notes/");
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		if (note.getId() == null) {
			Integer maxID = noteRepo.getMaxNoteID();
			note.setId((maxID != null ? maxID : 0) + 1);
			note.setCreatedBy("elab");
			note.setUpdatedBy("elab");
			note.setUpdatedDate(timeStamp);
			note.setCreatedDate(timeStamp);
			noteRepo.saveAndFlush(note);
		}
		return new ResponseEntity<String>("Success!!", HttpStatus.CREATED);
	}
}
