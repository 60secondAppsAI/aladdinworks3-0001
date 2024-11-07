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

import com.aladdinworks3.domain.Technician;
import com.aladdinworks3.dto.TechnicianDTO;
import com.aladdinworks3.dto.TechnicianSearchDTO;
import com.aladdinworks3.dto.TechnicianPageDTO;
import com.aladdinworks3.service.TechnicianService;
import com.aladdinworks3.dto.common.RequestDTO;
import com.aladdinworks3.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/technician")
@RestController
public class TechnicianController {

	private final static Logger logger = LoggerFactory.getLogger(TechnicianController.class);

	@Autowired
	TechnicianService technicianService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Technician> getAll() {

		List<Technician> technicians = technicianService.findAll();
		
		return technicians;	
	}

	@GetMapping(value = "/{technicianId}")
	@ResponseBody
	public TechnicianDTO getTechnician(@PathVariable Integer technicianId) {
		
		return (technicianService.getTechnicianDTOById(technicianId));
	}

 	@RequestMapping(value = "/addTechnician", method = RequestMethod.POST)
	public ResponseEntity<?> addTechnician(@RequestBody TechnicianDTO technicianDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = technicianService.addTechnician(technicianDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/technicians")
	public ResponseEntity<TechnicianPageDTO> getTechnicians(TechnicianSearchDTO technicianSearchDTO) {
 
		return technicianService.getTechnicians(technicianSearchDTO);
	}	

	@RequestMapping(value = "/updateTechnician", method = RequestMethod.POST)
	public ResponseEntity<?> updateTechnician(@RequestBody TechnicianDTO technicianDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = technicianService.updateTechnician(technicianDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
