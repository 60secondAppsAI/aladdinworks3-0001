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

import com.aladdinworks3.domain.PowerSwitch;
import com.aladdinworks3.dto.PowerSwitchDTO;
import com.aladdinworks3.dto.PowerSwitchSearchDTO;
import com.aladdinworks3.dto.PowerSwitchPageDTO;
import com.aladdinworks3.service.PowerSwitchService;
import com.aladdinworks3.dto.common.RequestDTO;
import com.aladdinworks3.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/powerSwitch")
@RestController
public class PowerSwitchController {

	private final static Logger logger = LoggerFactory.getLogger(PowerSwitchController.class);

	@Autowired
	PowerSwitchService powerSwitchService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<PowerSwitch> getAll() {

		List<PowerSwitch> powerSwitchs = powerSwitchService.findAll();
		
		return powerSwitchs;	
	}

	@GetMapping(value = "/{powerSwitchId}")
	@ResponseBody
	public PowerSwitchDTO getPowerSwitch(@PathVariable Integer powerSwitchId) {
		
		return (powerSwitchService.getPowerSwitchDTOById(powerSwitchId));
	}

 	@RequestMapping(value = "/addPowerSwitch", method = RequestMethod.POST)
	public ResponseEntity<?> addPowerSwitch(@RequestBody PowerSwitchDTO powerSwitchDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = powerSwitchService.addPowerSwitch(powerSwitchDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/powerSwitchs")
	public ResponseEntity<PowerSwitchPageDTO> getPowerSwitchs(PowerSwitchSearchDTO powerSwitchSearchDTO) {
 
		return powerSwitchService.getPowerSwitchs(powerSwitchSearchDTO);
	}	

	@RequestMapping(value = "/updatePowerSwitch", method = RequestMethod.POST)
	public ResponseEntity<?> updatePowerSwitch(@RequestBody PowerSwitchDTO powerSwitchDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = powerSwitchService.updatePowerSwitch(powerSwitchDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
