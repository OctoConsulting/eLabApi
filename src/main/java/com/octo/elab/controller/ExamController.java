package com.octo.elab.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.octo.elab.pojo.db.Note;
import com.octo.elab.pojo.reflection.AccessPair;
import com.octo.elab.pojo.reflection.ExamNotes;
import com.octo.elab.pojo.reflection.ExaminationNew;
import com.octo.elab.pojo.reflection.IKQNotes;
import com.octo.elab.repository.EvidenceRepository;
import com.octo.elab.repository.ExamRepository;
import com.octo.elab.repository.ExamTypeRepository;
import com.octo.elab.repository.ExaminerRepository;
import com.octo.elab.repository.NoteRepository;

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
	private NoteRepository noteRepo;

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
		List<Exam> examsWithNames = new ArrayList<Exam>();
		List<ExamType> examTypeNameList = examTypeRepo.getAllExamTypes();
		List<Examiner> examinerNameList = examinerRepo.getAllExaminers();

		HashMap<Integer, String> examTypeHashMap = new HashMap<>();
		HashMap<Integer, String> examinerHashMap = new HashMap<>();

		for (ExamType examType : examTypeNameList) {
			examTypeHashMap.put(examType.getId(), examType.getDescription());
		}

		for (Examiner examiner : examinerNameList) {
			examinerHashMap.put(examiner.getId(), examiner.getExaminerName());
		}
		if (caseId == null) {
			exams = examRepo.getAllExams();
		} else {
			exams = examRepo.getExamsByCaseID(caseId);
		}
		for(Exam exam : exams){
			exam.setExamTypeName(examTypeHashMap.get(exam.getExamType()));
			exam.setExaminerName(examinerHashMap.get(exam.getExaminerId()));
			examsWithNames.add(exam);
		}
		return new ResponseEntity<List<Exam>>(examsWithNames, HttpStatus.OK);
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
			@RequestParam(value = "examID", required = false) Integer examID,
			@RequestParam(value = "caseID", required = false) Integer caseID) throws Exception {
		log.info("GET /exams API to fetch all exams");
		ExaminationNew examinationNew = new ExaminationNew();
		List<ExamType> examTypeList = examTypeRepo.getAllExamTypes();
		List<Examiner> examinerList = examinerRepo.getAllExaminers();
		List<Evidence> evidenceList = evidenceRepo.getAllEvidencesForExam();
		List<AccessPair> examTypeAccessPairList = new ArrayList<AccessPair>();
		List<AccessPair> examinerAccessPairList = new ArrayList<AccessPair>();
		List<AccessPair> evidenceAccessPairList = new ArrayList<AccessPair>();
		Exam examToBeEdited = null;
		List<Exam> examToBeEditedMultiple = null;
		List<Integer> evidenceIDs = new ArrayList<>();

		if (mode.equalsIgnoreCase("edit")) {
			if (examID != null) {
				// examToBeEdited = examRepo.getExamByID(examID);
				examToBeEditedMultiple = examRepo.getExamsByCaseID(caseID);
				examToBeEdited = examToBeEditedMultiple.get(0);
				if (examToBeEdited == null) {
					return new ResponseEntity<ExaminationNew>(examinationNew, HttpStatus.BAD_REQUEST);
				} else {
					for (Exam readEvidenceForExam : examToBeEditedMultiple) {
						evidenceIDs.add(readEvidenceForExam.getEvidenceId());
					}
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
			evidenceAccessPair.setId(evidence.getId());
			evidenceAccessPair.setVal(evidence.getEvidenceName());
			evidenceAccessPair.set_id(evidence.get_id());
			if (evidenceIDs.contains(evidence.getId())) {
				evidenceAccessPair.setIsSelected(true);
			}

			evidenceAccessPairList.add(evidenceAccessPair);
		}

		examinationNew.setEvidences(evidenceAccessPairList);
		examinationNew.setExaminers(examinerAccessPairList);
		examinationNew.setExamType(examTypeAccessPairList);
		if (examToBeEdited != null) {
			examinationNew.set_id(examToBeEdited.get_id());
		}

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
	public ResponseEntity<Exam> updateExam(@RequestBody Exam exam) throws Exception {
		log.info("POST /exams/");
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		Integer caseID = exam.getCaseId();
		Integer _id = exam.get_id();
		
		Exam savedExam = null;
		/*Integer[] evidenceIDs = exam.getEvidenceIds();
		if (evidenceIDs == null) {
			evidenceIDs = new Integer[1];
			evidenceIDs[0] = 0;
		}*/
		
		if (caseID == null) {
			Exam exams = new Exam();
			return new ResponseEntity<Exam>(exams, HttpStatus.BAD_REQUEST);
		}
		if (_id != null) {
			//edit existing
			exam.setID(examRepo.getExamIDByCaseIDAnd_id(caseID, _id));
			exam.setUpdatedBy("elab");
			exam.setUpdatedDate(timeStamp);
			savedExam = examRepo.saveAndFlush(exam);
		}
		else {
			// Insert new
			Integer maxID = examRepo.getMaxExamID();
			exam.setID((maxID != null ? maxID : 0) + 1);
			exam.setCreatedBy("elab");
			exam.setUpdatedBy("elab");
			exam.setCreatedDate(timeStamp);
			//exam.setEvidenceId(evidenceIDs[0]);
			exam.setUpdatedDate(timeStamp);
			savedExam = examRepo.saveAndFlush(exam);
		}
	
		return new ResponseEntity<Exam>(savedExam, HttpStatus.CREATED);
	}

	/**
	 * This method is used to fetch all exams notes from database using caseId
	 *
	 * @return ResponseEntityList<ExamNotes>
	 */
	@RequestMapping(value = "/examnotes/{examId}", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch all Exam notes")
	public ResponseEntity<List<ExamNotes>> getExamNotes(
			@PathVariable Integer examId,
			@RequestParam(value = "caseId", required = false) Integer caseId,
			@RequestParam(value = "type", required = false) String type) throws Exception {
		
		List<ExamNotes> examNotesList = new ArrayList<ExamNotes>();
		List<IKQNotes> shoeNoteList = new ArrayList<IKQNotes>();
		List<IKQNotes> tireNoteList = new ArrayList<IKQNotes>();
		
		List<Integer> examIDs = noteRepo.getAllExamIDsByCaseID(caseId);
		
		if(!examIDs.contains(examId)){
			return new ResponseEntity<List<ExamNotes>>(examNotesList, HttpStatus.OK);
		}
		else{
						
			List<Note> initialAssessmentShoe = noteRepo.getShoeIANoteForExamID(examId);
			
			for(Note shoeNote : initialAssessmentShoe){
				IKQNotes shoeNotes = new IKQNotes();
				List<Note> shoeKNotes = new ArrayList<Note>();
				List<Note> shoeQNotes = new ArrayList<Note>();
				List<Note> shoeNoteDetails = new ArrayList<>();
				if(shoeNote != null){
					shoeNoteDetails = noteRepo.getNoteDetailsByParentID(shoeNote.getId());
				}
				
				if(type == null || ("shoe").equalsIgnoreCase(type)){
					if(shoeNoteDetails != null){
						
						for (Note note : shoeNoteDetails) {
							Integer noteType = note.getNoteType();
		
							// Knowns
							if (noteType == 2) {
								shoeKNotes.add(note);
							}
							// Questions
							else if (noteType == 3) {
								shoeQNotes.add(note);
							}
						}
		
					}
					shoeNotes.setKnowns(shoeKNotes);
					shoeNotes.setQuestions(shoeQNotes);
					shoeNotes.setInitialAssessmentNote(shoeNote);
					shoeNoteList.add(shoeNotes);
				}
			}	
			List<Note> initialAssessmentTire = noteRepo.getTireIANoteForExamID(examId);	
			for(Note tireNote : initialAssessmentTire){
				IKQNotes tireNotes = new IKQNotes();
				List<Note> tireKNotes = new ArrayList<Note>();
				List<Note> tireQNotes = new ArrayList<Note>();
				
				List<Note> tireNoteDetails = new ArrayList<>();
				if(tireNote != null){
					tireNoteDetails = noteRepo.getNoteDetailsByParentID(tireNote.getId());
				}
					
				if(type == null || ("tire").equalsIgnoreCase(type)){
					if(tireNoteDetails != null){
						for (Note note : tireNoteDetails) {
							Integer noteType = note.getNoteType();
		
							// Knowns
							if (noteType == 2) {
								tireKNotes.add(note);
							}
							// Questions
							else if (noteType == 3) {
								tireQNotes.add(note);
							}
						}
		
		
					}
					
					tireNotes.setInitialAssessmentNote(tireNote);
					tireNotes.setKnowns(tireKNotes);
					tireNotes.setQuestions(tireQNotes);
					tireNoteList.add(tireNotes);
				}
			}	

			// Get Exam Details for exam ID
			Exam exam = examRepo.getExamByID(examId);

			// Set Data for Exam Notes object
			ExamNotes examnotes = new ExamNotes();
			examnotes.setExam(exam);
			
			if(type == null || ("shoe").equalsIgnoreCase(type))
				examnotes.setShoeNotes(shoeNoteList);
			
			if(type == null || ("tire").equalsIgnoreCase(type))
				examnotes.setTireNotes(tireNoteList);

			// Add exam notes to List
			examNotesList.add(examnotes);
		}
		
		return new ResponseEntity<List<ExamNotes>>(examNotesList, HttpStatus.OK);
	}
}
