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

import com.aladdinworks3.domain.MonitoringSession;
import com.aladdinworks3.dto.MonitoringSessionDTO;
import com.aladdinworks3.dto.MonitoringSessionSearchDTO;
import com.aladdinworks3.dto.MonitoringSessionPageDTO;
import com.aladdinworks3.service.MonitoringSessionService;
import com.aladdinworks3.dto.common.RequestDTO;
import com.aladdinworks3.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/monitoringSession")
@RestController
public class MonitoringSessionController {

	private final static Logger logger = LoggerFactory.getLogger(MonitoringSessionController.class);

	@Autowired
	MonitoringSessionService monitoringSessionService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<MonitoringSession> getAll() {

		List<MonitoringSession> monitoringSessions = monitoringSessionService.findAll();
		
		return monitoringSessions;	
	}

	@GetMapping(value = "/{monitoringSessionId}")
	@ResponseBody
	public MonitoringSessionDTO getMonitoringSession(@PathVariable Integer monitoringSessionId) {
		
		return (monitoringSessionService.getMonitoringSessionDTOById(monitoringSessionId));
	}

 	@RequestMapping(value = "/addMonitoringSession", method = RequestMethod.POST)
	public ResponseEntity<?> addMonitoringSession(@RequestBody MonitoringSessionDTO monitoringSessionDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = monitoringSessionService.addMonitoringSession(monitoringSessionDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/monitoringSessions")
	public ResponseEntity<MonitoringSessionPageDTO> getMonitoringSessions(MonitoringSessionSearchDTO monitoringSessionSearchDTO) {
 
		return monitoringSessionService.getMonitoringSessions(monitoringSessionSearchDTO);
	}	

	@RequestMapping(value = "/updateMonitoringSession", method = RequestMethod.POST)
	public ResponseEntity<?> updateMonitoringSession(@RequestBody MonitoringSessionDTO monitoringSessionDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = monitoringSessionService.updateMonitoringSession(monitoringSessionDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
