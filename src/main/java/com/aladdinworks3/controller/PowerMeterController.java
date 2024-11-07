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

import com.aladdinworks3.domain.PowerMeter;
import com.aladdinworks3.dto.PowerMeterDTO;
import com.aladdinworks3.dto.PowerMeterSearchDTO;
import com.aladdinworks3.dto.PowerMeterPageDTO;
import com.aladdinworks3.service.PowerMeterService;
import com.aladdinworks3.dto.common.RequestDTO;
import com.aladdinworks3.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/powerMeter")
@RestController
public class PowerMeterController {

	private final static Logger logger = LoggerFactory.getLogger(PowerMeterController.class);

	@Autowired
	PowerMeterService powerMeterService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<PowerMeter> getAll() {

		List<PowerMeter> powerMeters = powerMeterService.findAll();
		
		return powerMeters;	
	}

	@GetMapping(value = "/{powerMeterId}")
	@ResponseBody
	public PowerMeterDTO getPowerMeter(@PathVariable Integer powerMeterId) {
		
		return (powerMeterService.getPowerMeterDTOById(powerMeterId));
	}

 	@RequestMapping(value = "/addPowerMeter", method = RequestMethod.POST)
	public ResponseEntity<?> addPowerMeter(@RequestBody PowerMeterDTO powerMeterDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = powerMeterService.addPowerMeter(powerMeterDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/powerMeters")
	public ResponseEntity<PowerMeterPageDTO> getPowerMeters(PowerMeterSearchDTO powerMeterSearchDTO) {
 
		return powerMeterService.getPowerMeters(powerMeterSearchDTO);
	}	

	@RequestMapping(value = "/updatePowerMeter", method = RequestMethod.POST)
	public ResponseEntity<?> updatePowerMeter(@RequestBody PowerMeterDTO powerMeterDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = powerMeterService.updatePowerMeter(powerMeterDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
