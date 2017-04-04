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

import com.octo.elab.pojo.db.Evidence;
import com.octo.elab.repository.EvidenceRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = ElabController.BasePath + "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Evidence", description = "Endpoint pertaining to Evidences")
public class EvidenceController {

	private static final Logger log = LoggerFactory.getLogger(EvidenceController.class);

	@Resource
	Environment environment;

	@Autowired
	private EvidenceRepository evidenceRepo;

	/**
	 * This method is used to fetch all evidences from database
	 *
	 * @return ResponseEntity<List<Evidence>>
	 */
	@RequestMapping(value = "/evidences", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch all Evidences")
	public ResponseEntity<List<Evidence>> getEvidences() throws Exception {
		log.info("GET /evidences API to fetch all evidences");
		List<Evidence> evidences = evidenceRepo.getAllEvidences();
		return new ResponseEntity<List<Evidence>>(evidences, HttpStatus.OK);
	}

	/**
	 * This method is used to supply an endpoint that returns a specified Evidence
	 * information
	 *
	 * @param evidenceID
	 *            The id of the Evidence to be retrieved
	 * @return ResponseEntity<Evidence>
	 */
	@RequestMapping(value = "/evidences/{evidenceID}/", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch a Evidence by ID")
	public ResponseEntity<Evidence> getEvidenceByID(
			@ApiParam(value = "evidenceID value", required = true) @PathVariable Integer evidenceID) throws Exception {
		log.info("GET /evidences/" + evidenceID);
		Evidence evidence = evidenceRepo.getEvidenceByID(evidenceID);
		return new ResponseEntity<Evidence>(evidence, HttpStatus.OK);
	}
}
