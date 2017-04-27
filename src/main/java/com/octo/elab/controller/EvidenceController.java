package com.octo.elab.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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
import com.octo.elab.pojo.db.EvidenceType;
import com.octo.elab.pojo.reflection.AccessPair;
import com.octo.elab.pojo.reflection.EvidenceNew;
import com.octo.elab.repository.EvidenceRepository;
import com.octo.elab.repository.EvidenceTypeRepository;
import com.octo.elab.utilities.Constants;
import com.octo.elab.utilities.NumberUtils;

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

	@Autowired
	private EvidenceTypeRepository evidenceTypeRepo;

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
	 * This method is used to populate dropdowns for creating/viewing/editing an
	 * evidence
	 *
	 * @return ResponseEntity<List<Evidence>>
	 */
	@RequestMapping(value = "/ui/evidences/", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch all Evidences")
	public ResponseEntity<EvidenceNew> getEvidenceNew(@RequestParam(value = "mode", required = true) String mode,
			@RequestParam(value = "evidenceID", required = false) Integer evidenceID) throws Exception {
		log.info("GET /evidences API to fetch all evidences");

		EvidenceNew evidenceNew = new EvidenceNew();
		Evidence evidenceToBeEdited = null;

		if (mode.equalsIgnoreCase("edit")) {
			if (evidenceID != null) {
				evidenceToBeEdited = evidenceRepo.getEvidenceByID(evidenceID);
				// No record in database for provided ID
				if (evidenceToBeEdited == null) {
					return new ResponseEntity<EvidenceNew>(evidenceNew, HttpStatus.BAD_REQUEST);
				} else {
					evidenceNew.setName(evidenceToBeEdited.getEvidenceName());
					evidenceNew.setForAnalysis(evidenceToBeEdited.getIsForAnalysis());
				}
			} else {
				return new ResponseEntity<EvidenceNew>(evidenceNew, HttpStatus.BAD_REQUEST);
			}
		}

		List<EvidenceType> evidenceTypeList = evidenceTypeRepo.getAllEvidenceTypes();
		List<AccessPair> evidenceTypeAccessPairList = new ArrayList<AccessPair>();
		List<AccessPair> parentTypeAccessPairList = new ArrayList<AccessPair>();
		AccessPair evidenceTypeAccessPair;
		AccessPair evidenceTypeAccessPairForParentType;

		for (EvidenceType evidenceType : evidenceTypeList) {
			// Set EvidenceType
			evidenceTypeAccessPair = new AccessPair();
			evidenceTypeAccessPair.setId(evidenceType.getId());
			evidenceTypeAccessPair.setVal(evidenceType.getDescription());
			if (evidenceToBeEdited != null && (evidenceToBeEdited.getEvidenceType() == evidenceType.getId())) {
				evidenceTypeAccessPair.setIsSelected(true);
			}
			evidenceTypeAccessPairList.add(evidenceTypeAccessPair);
			// Set Parent Type
			evidenceTypeAccessPairForParentType = new AccessPair();
			evidenceTypeAccessPairForParentType.setId(evidenceType.getId());
			evidenceTypeAccessPairForParentType.setVal(evidenceType.getDescription());
			if (evidenceToBeEdited != null && (evidenceToBeEdited.getParentId() == evidenceType.getId())) {
				evidenceTypeAccessPairForParentType.setIsSelected(true);
			}
			parentTypeAccessPairList.add(evidenceTypeAccessPairForParentType);

		}

		// Set Parent Evidence Number
		List<Evidence> evidenceList = evidenceRepo.getAllEvidences();
		List<AccessPair> evidenceAccessPairList = new ArrayList<AccessPair>();
		AccessPair evidenceAccessPair;
		for (Evidence evidence : evidenceList) {

			evidenceAccessPair = new AccessPair();
			evidenceAccessPair.setId(evidence.getId());
			evidenceAccessPair.setVal(evidence.getDescription());
			evidenceAccessPair.set_id(evidence.get_id());
			if (evidenceToBeEdited != null && (evidenceToBeEdited.getParentId() == evidence.getId())) {
				evidenceAccessPair.setIsSelected(true);
			}
			evidenceAccessPairList.add(evidenceAccessPair);

		}
		evidenceNew.setEvidenceType(evidenceTypeAccessPairList);
		evidenceNew.setParentEvidenceNumber(evidenceAccessPairList);
		evidenceNew.setParentType(parentTypeAccessPairList);
		return new ResponseEntity<EvidenceNew>(evidenceNew, HttpStatus.OK);

	}

	/**
	 * This method is used to supply an endpoint that returns a specified
	 * Evidence information
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

		if (evidence.getParentId() != null) {
			Evidence parentEvidence = evidenceRepo.getEvidenceByID(evidence.getParentId());
			evidence.setParent_id(parentEvidence.get_id());
			evidence.setParentType(parentEvidence.getEvidenceType());
		}

		return new ResponseEntity<Evidence>(evidence, HttpStatus.OK);
	}

	/**
	 * This method is used to update evidence isForAnalysis, item type and
	 * identifier values
	 * 
	 * @return ResponseEntity<List<Evidence>>
	 */
	@RequestMapping(value = "/evidences/", method = RequestMethod.PUT)
	@ApiOperation(value = "Update a Evidences by IDs")
	public ResponseEntity<List<Evidence>> updateEvidencesByIDs(@RequestParam(value = "id", required = false) String id,
			@RequestBody Evidence updatedEvidence) throws Exception {
		log.info("PUT /evidences/");
		if (StringUtils.isBlank(id)) {
			return new ResponseEntity<List<Evidence>>(HttpStatus.BAD_REQUEST);
		}
		List<Evidence> evidences = new ArrayList<Evidence>();
		for (Integer evidenceID : NumberUtils.convertToIntegerArray(id, Constants.DEFAULT_DELIMITER)) {
			ResponseEntity<Evidence> updatedEvidenceResponse = updateEvidenceByID(evidenceID, updatedEvidence);
			if (updatedEvidenceResponse.getStatusCode().is2xxSuccessful() && updatedEvidenceResponse.hasBody()) {
				evidences.add(updatedEvidenceResponse.getBody());
			}
		}
		return new ResponseEntity<List<Evidence>>(evidences, HttpStatus.OK);
	}

	/**
	 * This method is used to update evidence isForAnalysis, item type and
	 * identifier values
	 * 
	 * @return ResponseEntity<Evidence>
	 */
	@RequestMapping(value = "/evidences/{evidenceID}/", method = RequestMethod.PUT)
	@ApiOperation(value = "Update a Evidence by ID")
	public ResponseEntity<Evidence> updateEvidenceByID(
			@ApiParam(value = "evidenceID value", required = true) @PathVariable Integer evidenceID,
			@RequestBody Evidence updatedEvidence) throws Exception {

		Boolean updatedIsForAnalysis = updatedEvidence.getIsForAnalysis();
		log.info("PUT /evidences/" + evidenceID + "?isForAnalysis=" + updatedIsForAnalysis);
		Boolean update = false;
		Evidence evidence = evidenceRepo.getEvidenceByID(evidenceID);
		if (evidence.getEvidenceType() != Constants.ITEM_ID) {
			return new ResponseEntity<Evidence>(evidence, HttpStatus.BAD_REQUEST);
		} else if (updatedIsForAnalysis != evidence.getIsForAnalysis()) {
			evidence.setIsForAnalysis(updatedIsForAnalysis);
			update = true;
		}

		if (update) {
			Date date = new Date();
			Timestamp timeStamp = new Timestamp(date.getTime());
			evidence.setUpdatedDate(timeStamp);
			evidenceRepo.saveAndFlush(evidence);
		}
		return new ResponseEntity<Evidence>(evidence, HttpStatus.OK);
	}

	/**
	 * This method is used to add evidence
	 * 
	 * @return ResponseEntity<String>
	 */
	@RequestMapping(value = "/evidences/", method = RequestMethod.POST)
	@ApiOperation(value = "Add new evidence or edit new evidence")
	public ResponseEntity<String> addEvidence(@RequestBody Evidence evidence) throws Exception {
		log.info("POST /evidences/");
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		if (evidence.getId() == null) {
			Integer maxID = evidenceRepo.getMaxEvidenceID();
			Integer max_id = evidenceRepo.getMaxEvidence_ID(evidence.getEvidenceType());
			evidence.setId((maxID != null ? maxID : 0) + 1);
			evidence.setCreatedBy("elab");
			evidence.setUpdatedBy("elab");
			evidence.setUpdatedDate(timeStamp);
			evidence.setCreatedDate(timeStamp);
			evidence.set_id((max_id != null ? maxID : 0) + 1);
			evidenceRepo.saveAndFlush(evidence);
		}
		return new ResponseEntity<String>("Success!!", HttpStatus.CREATED);
	}
}
