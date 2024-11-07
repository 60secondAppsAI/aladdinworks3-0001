package com.aladdinworks3.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks3.domain.MonitoringSession;
import com.aladdinworks3.dto.MonitoringSessionDTO;
import com.aladdinworks3.dto.MonitoringSessionSearchDTO;
import com.aladdinworks3.dto.MonitoringSessionPageDTO;
import com.aladdinworks3.dto.MonitoringSessionConvertCriteriaDTO;
import com.aladdinworks3.service.GenericService;
import com.aladdinworks3.dto.common.RequestDTO;
import com.aladdinworks3.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface MonitoringSessionService extends GenericService<MonitoringSession, Integer> {

	List<MonitoringSession> findAll();

	ResultDTO addMonitoringSession(MonitoringSessionDTO monitoringSessionDTO, RequestDTO requestDTO);

	ResultDTO updateMonitoringSession(MonitoringSessionDTO monitoringSessionDTO, RequestDTO requestDTO);

    Page<MonitoringSession> getAllMonitoringSessions(Pageable pageable);

    Page<MonitoringSession> getAllMonitoringSessions(Specification<MonitoringSession> spec, Pageable pageable);

	ResponseEntity<MonitoringSessionPageDTO> getMonitoringSessions(MonitoringSessionSearchDTO monitoringSessionSearchDTO);
	
	List<MonitoringSessionDTO> convertMonitoringSessionsToMonitoringSessionDTOs(List<MonitoringSession> monitoringSessions, MonitoringSessionConvertCriteriaDTO convertCriteria);

	MonitoringSessionDTO getMonitoringSessionDTOById(Integer monitoringSessionId);







}





