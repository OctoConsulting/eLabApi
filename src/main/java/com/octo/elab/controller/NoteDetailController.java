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

import com.octo.elab.pojo.db.NoteDetail;
import com.octo.elab.repository.NoteDetailRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = ElabController.BasePath + "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "NoteDetail", description = "Endpoint pertaining to NoteDetails")
public class NoteDetailController {

	private static final Logger log = LoggerFactory.getLogger(NoteDetailController.class);

	@Resource
	Environment environment;

	@Autowired
	private NoteDetailRepository noteDetailRepo;

	/**
	 * This method is used to fetch all noteDetails from database
	 *
	 * @return ResponseEntity<List<NoteDetail>>
	 */
	@RequestMapping(value = "/noteDetails", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch all NoteDetails")
	public ResponseEntity<List<NoteDetail>> getNoteDetails() throws Exception {
		log.info("GET /noteDetails API to fetch all noteDetails");
		List<NoteDetail> noteDetails = noteDetailRepo.getAllNoteDetails();
		return new ResponseEntity<List<NoteDetail>>(noteDetails, HttpStatus.OK);
	}

	/**
	 * This method is used to supply an endpoint that returns a specified
	 * noteDetail information
	 *
	 * @param noteDetailID
	 *            The id of the noteDetail to be retrieved
	 * @return ResponseEntity<NoteDetail>
	 */
	@RequestMapping(value = "/noteDetails/{noteDetailID}/", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch a noteDetail by ID")
	public ResponseEntity<NoteDetail> getNoteDetailByID(
			@ApiParam(value = "noteDetailID value", required = true) @PathVariable Integer noteDetailID) throws Exception {
		log.info("GET /noteDetails/" + noteDetailID);
		NoteDetail noteDetail = noteDetailRepo.getNoteDetailByID(noteDetailID);
		return new ResponseEntity<NoteDetail>(noteDetail, HttpStatus.OK);
	}
}
