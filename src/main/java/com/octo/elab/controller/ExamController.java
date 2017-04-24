package com.octo.elab.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
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

import edu.emory.mathcs.backport.java.util.Arrays;
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

	@RequestMapping(value = "/ui/exams/", method = RequestMethod.GET)
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
				else{
					examinationNew.setAssignedDate(examToBeEdited.getAssignedDate());
					examinationNew.setStartDate(examToBeEdited.getStartDate());
					examinationNew.setEndDate(examToBeEdited.getEndDate());
					examinationNew.setName(examToBeEdited.getExamName());
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
	
	/**
	 * This method is used to add/update exam 
	 * 
	 * @return ResponseEntity<String>
	 */
	@RequestMapping(value = "/exams/", method = RequestMethod.POST)
	@ApiOperation(value = "Add new exam or edit new exam")
	public ResponseEntity<String> updateExam(
			@RequestBody Exam exam) throws Exception {
		log.info("POST /exams/");
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		Integer[] existingEvidencesArray = examRepo.getExamEvidencesByCaseID(exam.getCaseId());
		List<Integer> existingEvidencesList = Arrays.asList(existingEvidencesArray);
		List<Integer> removeEvidencesList = new ArrayList<Integer>();
		Integer caseID = exam.getCaseId();
		Integer maxID = examRepo.getMaxExamID();
		
			for(Integer existingEvidences : exam.getEvidenceIds())
			{
				if(existingEvidencesList != null && existingEvidencesList.contains(existingEvidences)){
					//Update
					Exam existingExam = examRepo.getExamIDByCaseIDAndEvidenceID(caseID,existingEvidences);
					existingExam.setStartDate(exam.getStartDate());
					existingExam.setEndDate(exam.getEndDate());
					existingExam.setExaminerId(exam.getExaminerId());
					existingExam.setExamName(exam.getExamName());
					existingExam.setExamType(exam.getExamType());
					existingExam.setEvidenceId(existingEvidences);
					existingExam.setAssignedDate(exam.getAssignedDate());
					existingExam.setUpdatedDate(timeStamp);
					examRepo.saveAndFlush(existingExam);
					removeEvidencesList.add(existingEvidences);
					
				}
				else{
				//Insert new
					exam.setID((maxID != null ? maxID : 0)+1);
					exam.setCreatedBy("elab");
					exam.setUpdatedBy("elab");
					exam.setCreatedDate(timeStamp);
					exam.setEvidenceId(existingEvidences);
					exam.setUpdatedDate(timeStamp);
					examRepo.saveAndFlush(exam);
				}	
		}
			// Delete
			for(Integer existingEvidenceID : existingEvidencesList)
			{
				if(removeEvidencesList.contains(existingEvidenceID)){
					System.out.println("Hello"+existingEvidenceID);
					
				}
				else{
					Exam examToBeDeleted = examRepo.getExamIDByCaseIDAndEvidenceID(caseID,existingEvidenceID);
					examRepo.delete(examToBeDeleted);
					System.out.println("World"+existingEvidenceID);
				}
				
			}
			
		return new ResponseEntity<String>("Success!!", HttpStatus.CREATED);
	}
}
