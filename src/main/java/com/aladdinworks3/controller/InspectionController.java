package com.aladdinworks3.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.aladdinworks3.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.aladdinworks3.domain.Inspection;
import com.aladdinworks3.dto.InspectionDTO;
import com.aladdinworks3.dto.InspectionSearchDTO;
import com.aladdinworks3.dto.InspectionPageDTO;
import com.aladdinworks3.service.InspectionService;
import com.aladdinworks3.dto.common.RequestDTO;
import com.aladdinworks3.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/inspection")
@RestController
public class InspectionController {

	private final static Logger logger = LoggerFactory.getLogger(InspectionController.class);

	@Autowired
	InspectionService inspectionService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Inspection> getAll() {

		List<Inspection> inspections = inspectionService.findAll();
		
		return inspections;	
	}

	@GetMapping(value = "/{inspectionId}")
	@ResponseBody
	public InspectionDTO getInspection(@PathVariable Integer inspectionId) {
		
		return (inspectionService.getInspectionDTOById(inspectionId));
	}

 	@RequestMapping(value = "/addInspection", method = RequestMethod.POST)
	public ResponseEntity<?> addInspection(@RequestBody InspectionDTO inspectionDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = inspectionService.addInspection(inspectionDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/inspections")
	public ResponseEntity<InspectionPageDTO> getInspections(InspectionSearchDTO inspectionSearchDTO) {
 
		return inspectionService.getInspections(inspectionSearchDTO);
	}	

	@RequestMapping(value = "/updateInspection", method = RequestMethod.POST)
	public ResponseEntity<?> updateInspection(@RequestBody InspectionDTO inspectionDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = inspectionService.updateInspection(inspectionDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
