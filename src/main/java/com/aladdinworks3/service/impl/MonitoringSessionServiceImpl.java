package com.aladdinworks3.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.aladdinworks3.dao.GenericDAO;
import com.aladdinworks3.service.GenericService;
import com.aladdinworks3.service.impl.GenericServiceImpl;
import com.aladdinworks3.dao.MonitoringSessionDAO;
import com.aladdinworks3.domain.MonitoringSession;
import com.aladdinworks3.dto.MonitoringSessionDTO;
import com.aladdinworks3.dto.MonitoringSessionSearchDTO;
import com.aladdinworks3.dto.MonitoringSessionPageDTO;
import com.aladdinworks3.dto.MonitoringSessionConvertCriteriaDTO;
import com.aladdinworks3.dto.common.RequestDTO;
import com.aladdinworks3.dto.common.ResultDTO;
import com.aladdinworks3.service.MonitoringSessionService;
import com.aladdinworks3.util.ControllerUtils;





@Service
public class MonitoringSessionServiceImpl extends GenericServiceImpl<MonitoringSession, Integer> implements MonitoringSessionService {

    private final static Logger logger = LoggerFactory.getLogger(MonitoringSessionServiceImpl.class);

	@Autowired
	MonitoringSessionDAO monitoringSessionDao;

	


	@Override
	public GenericDAO<MonitoringSession, Integer> getDAO() {
		return (GenericDAO<MonitoringSession, Integer>) monitoringSessionDao;
	}
	
	public List<MonitoringSession> findAll () {
		List<MonitoringSession> monitoringSessions = monitoringSessionDao.findAll();
		
		return monitoringSessions;	
		
	}

	public ResultDTO addMonitoringSession(MonitoringSessionDTO monitoringSessionDTO, RequestDTO requestDTO) {

		MonitoringSession monitoringSession = new MonitoringSession();

		monitoringSession.setMonitoringSessionId(monitoringSessionDTO.getMonitoringSessionId());


		monitoringSession.setStartDateTime(monitoringSessionDTO.getStartDateTime());


		monitoringSession.setEndDateTime(monitoringSessionDTO.getEndDateTime());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		monitoringSession = monitoringSessionDao.save(monitoringSession);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<MonitoringSession> getAllMonitoringSessions(Pageable pageable) {
		return monitoringSessionDao.findAll(pageable);
	}

	public Page<MonitoringSession> getAllMonitoringSessions(Specification<MonitoringSession> spec, Pageable pageable) {
		return monitoringSessionDao.findAll(spec, pageable);
	}

	public ResponseEntity<MonitoringSessionPageDTO> getMonitoringSessions(MonitoringSessionSearchDTO monitoringSessionSearchDTO) {
	
			Integer monitoringSessionId = monitoringSessionSearchDTO.getMonitoringSessionId(); 
   			String sortBy = monitoringSessionSearchDTO.getSortBy();
			String sortOrder = monitoringSessionSearchDTO.getSortOrder();
			String searchQuery = monitoringSessionSearchDTO.getSearchQuery();
			Integer page = monitoringSessionSearchDTO.getPage();
			Integer size = monitoringSessionSearchDTO.getSize();

	        Specification<MonitoringSession> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, monitoringSessionId, "monitoringSessionId"); 
			
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<MonitoringSession> monitoringSessions = this.getAllMonitoringSessions(spec, pageable);
		
		//System.out.println(String.valueOf(monitoringSessions.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(monitoringSessions.getTotalPages()));
		
		List<MonitoringSession> monitoringSessionsList = monitoringSessions.getContent();
		
		MonitoringSessionConvertCriteriaDTO convertCriteria = new MonitoringSessionConvertCriteriaDTO();
		List<MonitoringSessionDTO> monitoringSessionDTOs = this.convertMonitoringSessionsToMonitoringSessionDTOs(monitoringSessionsList,convertCriteria);
		
		MonitoringSessionPageDTO monitoringSessionPageDTO = new MonitoringSessionPageDTO();
		monitoringSessionPageDTO.setMonitoringSessions(monitoringSessionDTOs);
		monitoringSessionPageDTO.setTotalElements(monitoringSessions.getTotalElements());
		return ResponseEntity.ok(monitoringSessionPageDTO);
	}

	public List<MonitoringSessionDTO> convertMonitoringSessionsToMonitoringSessionDTOs(List<MonitoringSession> monitoringSessions, MonitoringSessionConvertCriteriaDTO convertCriteria) {
		
		List<MonitoringSessionDTO> monitoringSessionDTOs = new ArrayList<MonitoringSessionDTO>();
		
		for (MonitoringSession monitoringSession : monitoringSessions) {
			monitoringSessionDTOs.add(convertMonitoringSessionToMonitoringSessionDTO(monitoringSession,convertCriteria));
		}
		
		return monitoringSessionDTOs;

	}
	
	public MonitoringSessionDTO convertMonitoringSessionToMonitoringSessionDTO(MonitoringSession monitoringSession, MonitoringSessionConvertCriteriaDTO convertCriteria) {
		
		MonitoringSessionDTO monitoringSessionDTO = new MonitoringSessionDTO();
		
		monitoringSessionDTO.setMonitoringSessionId(monitoringSession.getMonitoringSessionId());

	
		monitoringSessionDTO.setStartDateTime(monitoringSession.getStartDateTime());

	
		monitoringSessionDTO.setEndDateTime(monitoringSession.getEndDateTime());

	

		
		return monitoringSessionDTO;
	}

	public ResultDTO updateMonitoringSession(MonitoringSessionDTO monitoringSessionDTO, RequestDTO requestDTO) {
		
		MonitoringSession monitoringSession = monitoringSessionDao.getById(monitoringSessionDTO.getMonitoringSessionId());

		monitoringSession.setMonitoringSessionId(ControllerUtils.setValue(monitoringSession.getMonitoringSessionId(), monitoringSessionDTO.getMonitoringSessionId()));

		monitoringSession.setStartDateTime(ControllerUtils.setValue(monitoringSession.getStartDateTime(), monitoringSessionDTO.getStartDateTime()));

		monitoringSession.setEndDateTime(ControllerUtils.setValue(monitoringSession.getEndDateTime(), monitoringSessionDTO.getEndDateTime()));



        monitoringSession = monitoringSessionDao.save(monitoringSession);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public MonitoringSessionDTO getMonitoringSessionDTOById(Integer monitoringSessionId) {
	
		MonitoringSession monitoringSession = monitoringSessionDao.getById(monitoringSessionId);
			
		
		MonitoringSessionConvertCriteriaDTO convertCriteria = new MonitoringSessionConvertCriteriaDTO();
		return(this.convertMonitoringSessionToMonitoringSessionDTO(monitoringSession,convertCriteria));
	}







}
