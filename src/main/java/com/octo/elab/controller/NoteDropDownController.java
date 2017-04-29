/**
 * 
 */
package com.octo.elab.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.octo.elab.pojo.db.NoteDropDown;
import com.octo.elab.repository.NoteDropDownRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Nithin.Emanuel
 *
 */

@RestController
@RequestMapping(value = ElabController.BasePath + "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "NoteType", description = "Endpoint pertaining to NoteTypes")
public class NoteDropDownController {
	
	private static final Logger log = LoggerFactory.getLogger(NoteDropDownController.class);

	@Resource
	Environment environment;
	
	@Autowired
	NoteDropDownRepository noteDropDownRepo;
	
	@RequestMapping(value = "/noteDropDown", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch all note drop down")
	public List<NoteDropDown> getAllNoteDropDown(){
		List<NoteDropDown> allDropDowns = noteDropDownRepo.getAllNoteDropDown();
		log.info("No of drop down retrieved " + allDropDowns.size());
		return allDropDowns;
	}
	
	@RequestMapping(value = "/noteDropDowns", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch all note drop down")
	public List<NoteDropDown> getFilteredDropDown(@RequestParam(value = "type", required = false) String type,
													@RequestParam(value= "label",required = false) String label){
		List<NoteDropDown> dropDowns = new ArrayList<>();
		
		if(type == null && label == null){
			dropDowns = noteDropDownRepo.getAllNoteDropDown();
			log.info("No of drop down retrieved " + dropDowns.size());
		}
		else if(type != null && label != null){
			dropDowns = noteDropDownRepo.getNoteDropDownByLabelAndType(label, type);
			log.info("No of drop down retrieved " + dropDowns.size());
		}
		else if(type == null && label != null){
			dropDowns = noteDropDownRepo.getNoteDropDownByLabel(label);
			log.info("No of drop down retrieved " + dropDowns.size());
		}
		
		return dropDowns;
	}

}
