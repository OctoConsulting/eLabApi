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

import com.octo.elab.pojo.db.Image;
import com.octo.elab.repository.ImageRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = ElabController.BasePath + "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Image", description = "Endpoint pertaining to Images")
public class ImageController {

	private static final Logger log = LoggerFactory.getLogger(ImageController.class);

	@Resource
	Environment environment;

	@Autowired
	private ImageRepository imageRepo;

	/**
	 * This method is used to fetch all images from database
	 *
	 * @return ResponseEntity<List<Image>>
	 */
	@RequestMapping(value = "/images", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch all Images")
	public ResponseEntity<List<Image>> getImages() throws Exception {
		log.info("GET /images API to fetch all images");
		List<Image> images = imageRepo.getAllImages();
		return new ResponseEntity<List<Image>>(images, HttpStatus.OK);
	}

	/**
	 * This method is used to supply an endpoint that returns a specified
	 * image information
	 *
	 * @param imageID
	 *            The id of the image to be retrieved
	 * @return ResponseEntity<Image>
	 */
	@RequestMapping(value = "/images/{imageID}/", method = RequestMethod.GET)
	@ApiOperation(value = "Fetch a image by ID")
	public ResponseEntity<Image> getImageByID(
			@ApiParam(value = "imageID value", required = true) @PathVariable Integer imageID) throws Exception {
		log.info("GET /images/" + imageID);
		Image image = imageRepo.getImageByID(imageID);
		return new ResponseEntity<Image>(image, HttpStatus.OK);
	}
}
