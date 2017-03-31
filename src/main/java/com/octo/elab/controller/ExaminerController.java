package com.octo.elab.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.octo.elab.pojo.db.Examiner;
import com.octo.elab.repository.ExaminerRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = ElabController.BasePath + "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Examiner", description = "Endpoint pertaining to Examiners")
public class ExaminerController {

	private static final Logger log = LoggerFactory.getLogger(ExaminerController.class);

	@Resource
	Environment environment;

	@Autowired
	private ExaminerRepository examinerRepo;

	/**
	 * This method is used to fetch all examiners from database
	 *
	 * @return Resources<Examiner>
	 */
	@RequestMapping(value = "/examiners", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch all Examiners")
	public Resources<Examiner> getExaminers() throws Exception {
		log.info("GET /examiners API to fetch all examiners");
		List<Examiner> examiners = examinerRepo.getExaminers();
		for (Examiner examiner : examiners) {
			examiner.add(linkTo(methodOn(ExaminerController.class).getExaminerByID(examiner.getExaminerId())).withSelfRel());
		}
		Link link = linkTo(ExaminerController.class).slash("/rms").slash("/v1").slash("/examiners").withSelfRel();
		Resources<Examiner> resources = new Resources<Examiner>(examiners, link);
		return resources;
	}

	/**
	 * This method is used to supply an endpoint that returns a specified examiner
	 * information
	 *
	 * @param examinerID
	 *            The id of the examiner to be retrieved
	 * @return Examiner
	 */
	@RequestMapping(value = "/examiners/{examinerID}/", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch a examiner by ID")
	public Examiner getExaminerByID(@ApiParam(value = "examinerID value", required = true) @PathVariable Integer examinerID)
			throws Exception {
		log.info("GET /examiners/" + examinerID);
		Examiner examiner = examinerRepo.getExaminerByID(examinerID);
		examiner.add(linkTo(methodOn(ExaminerController.class).getExaminerByID(examinerID)).withSelfRel());
		return examiner;
	}

	/**
	 * End-point for adding a new examiner. It inserts record into Examiner table
	 * 
	 * @param Examiner
	 * 
	 * @return ResponseEntity<Examiner>
	 */

	@RequestMapping(value = "/examiners/", method = RequestMethod.POST)
	@ApiOperation(value = "Add new examiner")
	public ResponseEntity<Examiner> addNewExaminer(@RequestBody Examiner examiner) throws Exception {
		log.info("POST /examiners/");

		if (examiner == null)
			return new ResponseEntity<Examiner>(HttpStatus.BAD_REQUEST);
		//else
			//return examinerDefinitionService.addExaminer(examiner);
		return null;
	}
}
