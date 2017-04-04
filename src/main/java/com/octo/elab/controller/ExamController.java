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

import com.octo.elab.pojo.db.Exam;
import com.octo.elab.repository.ExamRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = ElabController.BasePath + "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Exam", description = "Endpoint pertaining to Exams")
public class ExamController {

	private static final Logger log = LoggerFactory.getLogger(ExamController.class);

	@Resource
	Environment environment;

	@Autowired
	private ExamRepository examRepo;

	/**
	 * This method is used to fetch all exams from database
	 *
	 * @return ResponseEntity<List<Exam>>
	 */
	@RequestMapping(value = "/exams", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch all Exams")
	public ResponseEntity<List<Exam>> getExams() throws Exception {
		log.info("GET /exams API to fetch all exams");
		List<Exam> exams = examRepo.getAllExams();
		return new ResponseEntity<List<Exam>>(exams, HttpStatus.OK);
	}

	/**
	 * This method is used to supply an endpoint that returns a specified Exam
	 * information
	 *
	 * @param examID
	 *            The id of the Exam to be retrieved
	 * @return ResponseEntity<Exam>
	 */
	@RequestMapping(value = "/exams/{examID}/", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch a Exam by ID")
	public ResponseEntity<Exam> getExamByID(
			@ApiParam(value = "examID value", required = true) @PathVariable Integer examID) throws Exception {
		log.info("GET /exams/" + examID);
		Exam exam = examRepo.getExamByID(examID);
		return new ResponseEntity<Exam>(exam, HttpStatus.OK);
	}
}
