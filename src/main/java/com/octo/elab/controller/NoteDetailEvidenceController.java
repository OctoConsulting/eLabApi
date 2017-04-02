package com.octo.elab.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.octo.elab.pojo.db.NoteDetailEvidence;
import com.octo.elab.repository.NoteDetailEvidenceRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = ElabController.BasePath + "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "NoteDetailEvidence", description = "Endpoint pertaining to NoteDetailEvidences")
public class NoteDetailEvidenceController {

	private static final Logger log = LoggerFactory.getLogger(NoteDetailEvidenceController.class);

	@Resource
	Environment environment;

	@Autowired
	private NoteDetailEvidenceRepository noteDetailEvidenceRepo;

	/**
	 * This method is used to fetch all noteDetailEvidences from database
	 *
	 * @return ResponseEntity<List<NoteDetailEvidence>>
	 */
	@RequestMapping(value = "/noteDetailEvidences", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch all NoteDetailEvidences")
	public ResponseEntity<List<NoteDetailEvidence>> getNoteDetailEvidences() throws Exception {
		log.info("GET /noteDetailEvidences API to fetch all noteDetailEvidences");
		List<NoteDetailEvidence> noteDetailEvidences = noteDetailEvidenceRepo.getAllNoteDetailEvidences();
		return new ResponseEntity<List<NoteDetailEvidence>>(noteDetailEvidences, HttpStatus.OK);
	}

	/**
	 * This method is used to supply an endpoint that returns a specified
	 * noteDetailEvidence information
	 *
	 * @param noteDetailEvidenceID
	 *            The id of the noteDetailEvidence to be retrieved
	 * @return ResponseEntity<NoteDetailEvidence>
	 */
	@RequestMapping(value = "/noteDetailEvidences/{noteDetailEvidenceID}/", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch a noteDetailEvidence by ID")
	public ResponseEntity<NoteDetailEvidence> getNoteDetailEvidenceByID(
			@ApiParam(value = "noteDetailEvidenceID value", required = true) @PathVariable Integer noteDetailEvidenceID) throws Exception {
		log.info("GET /noteDetailEvidences/" + noteDetailEvidenceID);
		NoteDetailEvidence noteDetailEvidence = noteDetailEvidenceRepo.getNoteDetailEvidenceByID(noteDetailEvidenceID);
		return new ResponseEntity<NoteDetailEvidence>(noteDetailEvidence, HttpStatus.OK);
	}
}
