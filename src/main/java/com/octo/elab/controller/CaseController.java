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

import com.octo.elab.pojo.db.Case;
import com.octo.elab.repository.CaseRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = ElabController.BasePath + "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Case", description = "Endpoint pertaining to Cases")
public class CaseController {

	private static final Logger log = LoggerFactory.getLogger(CaseController.class);

	@Resource
	Environment environment;

	@Autowired
	private CaseRepository caseRepo;

	/**
	 * This method is used to fetch all cases from database
	 *
	 * @return ResponseEntity<List<Case>>
	 */
	@RequestMapping(value = "/cases", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch all Cases")
	public ResponseEntity<List<Case>> getCases() throws Exception {
		log.info("GET /cases API to fetch all cases");
		List<Case> cases = caseRepo.getAllCases();
		return new ResponseEntity<List<Case>>(cases, HttpStatus.OK);
	}

	/**
	 * This method is used to supply an endpoint that returns a specified Case
	 * information
	 *
	 * @param caseID
	 *            The id of the Case to be retrieved
	 * @return ResponseEntity<Case>
	 */
	@RequestMapping(value = "/cases/{caseID}/", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch a Case by ID")
	public ResponseEntity<Case> getCaseByID(
			@ApiParam(value = "caseID value", required = true) @PathVariable Integer caseID) throws Exception {
		log.info("GET /cases/" + caseID);
		Case cases = caseRepo.getCaseByID(caseID);
		return new ResponseEntity<Case>(cases, HttpStatus.OK);
	}
}
