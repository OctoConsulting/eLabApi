package com.octo.elab.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.octo.elab.pojo.db.Evidence;
import com.octo.elab.pojo.db.Exam;
import com.octo.elab.pojo.db.ExamType;
import com.octo.elab.pojo.db.Examiner;
import com.octo.elab.pojo.reflection.AccessPair;
import com.octo.elab.pojo.reflection.ExaminationNew;
import com.octo.elab.repository.EvidenceRepository;
import com.octo.elab.repository.ExamRepository;
import com.octo.elab.repository.ExamTypeRepository;
import com.octo.elab.repository.ExaminerRepository;

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

	@Autowired
	private ExamTypeRepository examTypeRepo;

	@Autowired
	private EvidenceRepository evidenceRepo;

	@Autowired
	private ExaminerRepository examinerRepo;

	/**
	 * This method is used to fetch all exams from database
	 *
	 * @return ResponseEntity<List<Exam>>
	 */
	@RequestMapping(value = "/exams", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch all Exams")
	public ResponseEntity<List<Exam>> getExams(@RequestParam(value = "caseId", required = false) Integer caseId)
			throws Exception {
		log.info("GET /exams API to fetch all exams");
		List<Exam> exams = new ArrayList<Exam>();
		if (caseId == null) {
			exams = examRepo.getAllExams();
			return new ResponseEntity<List<Exam>>(exams, HttpStatus.OK);
		} else {
			List<Exam> examList = examRepo.getExamsByCaseID(caseId);
			Exam exam = examList.get(0);
			Integer[] evidenceIDs = examRepo.getAllEvidencesByCaseID(caseId);
			List<Evidence> evidences = evidenceRepo.getEvidencesByID(evidenceIDs);
			exam.setItems(evidences);
			exams.add(exam);

		}
		return new ResponseEntity<List<Exam>>(exams, HttpStatus.OK);
	}

	/**
	 * This method is used to populate dropdowns for creating/viewing/editing an
	 * exam
	 *
	 * @return ResponseEntity<ExaminationNew>
	 */

	@RequestMapping(value = "/examsM", method = RequestMethod.GET)
	@ApiOperation(value = "Populating info for creating/viewing/editing an exam")
	public ResponseEntity<ExaminationNew> getExamsInfo(@RequestParam(value = "mode", required = true) String mode,
			@RequestParam(value = "examID", required = false) Integer examID) throws Exception {
		log.info("GET /exams API to fetch all exams");
		ExaminationNew examinationNew = new ExaminationNew();
		List<ExamType> examTypeList = examTypeRepo.getAllExamTypes();
		List<Examiner> examinerList = examinerRepo.getAllExaminers();
		List<Evidence> evidenceList = evidenceRepo.getAllEvidencesForExam();
		List<AccessPair> examTypeAccessPairList = new ArrayList<AccessPair>();
		List<AccessPair> examinerAccessPairList = new ArrayList<AccessPair>();
		List<AccessPair> evidenceAccessPairList = new ArrayList<AccessPair>();
		Exam examToBeEdited = null;

		if (mode.equalsIgnoreCase("edit")) {
			if (examID != null) {
				examToBeEdited = examRepo.getExamByID(examID);
				if (examToBeEdited == null) {
					return new ResponseEntity<ExaminationNew>(examinationNew, HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity<ExaminationNew>(examinationNew, HttpStatus.BAD_REQUEST);
			}

		}
		// Set ExamType
		AccessPair examTypeAccessPair;
		for (ExamType examType : examTypeList) {
			examTypeAccessPair = new AccessPair();
			examTypeAccessPair.setId(examType.getId());
			examTypeAccessPair.setVal(examType.getDescription());
			if (examToBeEdited != null && (examToBeEdited.getExamType() == examType.getId())) {
				examTypeAccessPair.setIsSelected(true);
			}
			examTypeAccessPairList.add(examTypeAccessPair);
		}

		// Set Examiners
		AccessPair examinerAccessPair;
		for (Examiner examiner : examinerList) {
			examinerAccessPair = new AccessPair();
			examinerAccessPair.setId(examiner.getId());
			examinerAccessPair.setVal(examiner.getExaminerName());
			if (examToBeEdited != null && (examToBeEdited.getExaminerId() == examiner.getId())) {
				examinerAccessPair.setIsSelected(true);
			}
			examinerAccessPairList.add(examinerAccessPair);
		}

		// Set Evidences
		AccessPair evidenceAccessPair;
		for (Evidence evidence : evidenceList) {
			evidenceAccessPair = new AccessPair();
			evidenceAccessPair.setId(evidence.get_id());
			evidenceAccessPair.setVal(evidence.getEvidenceName());
			// TBD
			/*
			 * if(examToBeEdited != null && (examToBeEdited.g ==
			 * examiner.getId())){ examinerAccessPair.setIsSelected(true); }
			 */
			evidenceAccessPairList.add(evidenceAccessPair);
		}

		examinationNew.setEvidences(evidenceAccessPairList);
		examinationNew.setExaminers(examinerAccessPairList);
		examinationNew.setExamType(examTypeAccessPairList);
		return new ResponseEntity<ExaminationNew>(examinationNew, HttpStatus.OK);
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
