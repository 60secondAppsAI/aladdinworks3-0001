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
import com.aladdinworks3.dao.PowerSwitchDAO;
import com.aladdinworks3.domain.PowerSwitch;
import com.aladdinworks3.dto.PowerSwitchDTO;
import com.aladdinworks3.dto.PowerSwitchSearchDTO;
import com.aladdinworks3.dto.PowerSwitchPageDTO;
import com.aladdinworks3.dto.PowerSwitchConvertCriteriaDTO;
import com.aladdinworks3.dto.common.RequestDTO;
import com.aladdinworks3.dto.common.ResultDTO;
import com.aladdinworks3.service.PowerSwitchService;
import com.aladdinworks3.util.ControllerUtils;





@Service
public class PowerSwitchServiceImpl extends GenericServiceImpl<PowerSwitch, Integer> implements PowerSwitchService {

    private final static Logger logger = LoggerFactory.getLogger(PowerSwitchServiceImpl.class);

	@Autowired
	PowerSwitchDAO powerSwitchDao;

	


	@Override
	public GenericDAO<PowerSwitch, Integer> getDAO() {
		return (GenericDAO<PowerSwitch, Integer>) powerSwitchDao;
	}
	
	public List<PowerSwitch> findAll () {
		List<PowerSwitch> powerSwitchs = powerSwitchDao.findAll();
		
		return powerSwitchs;	
		
	}

	public ResultDTO addPowerSwitch(PowerSwitchDTO powerSwitchDTO, RequestDTO requestDTO) {

		PowerSwitch powerSwitch = new PowerSwitch();

		powerSwitch.setPowerSwitchId(powerSwitchDTO.getPowerSwitchId());


		powerSwitch.setState(powerSwitchDTO.getState());


		powerSwitch.setControlVoltage(powerSwitchDTO.getControlVoltage());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		powerSwitch = powerSwitchDao.save(powerSwitch);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<PowerSwitch> getAllPowerSwitchs(Pageable pageable) {
		return powerSwitchDao.findAll(pageable);
	}

	public Page<PowerSwitch> getAllPowerSwitchs(Specification<PowerSwitch> spec, Pageable pageable) {
		return powerSwitchDao.findAll(spec, pageable);
	}

	public ResponseEntity<PowerSwitchPageDTO> getPowerSwitchs(PowerSwitchSearchDTO powerSwitchSearchDTO) {
	
			Integer powerSwitchId = powerSwitchSearchDTO.getPowerSwitchId(); 
 			String state = powerSwitchSearchDTO.getState(); 
  			String sortBy = powerSwitchSearchDTO.getSortBy();
			String sortOrder = powerSwitchSearchDTO.getSortOrder();
			String searchQuery = powerSwitchSearchDTO.getSearchQuery();
			Integer page = powerSwitchSearchDTO.getPage();
			Integer size = powerSwitchSearchDTO.getSize();

	        Specification<PowerSwitch> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, powerSwitchId, "powerSwitchId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, state, "state"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("state")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<PowerSwitch> powerSwitchs = this.getAllPowerSwitchs(spec, pageable);
		
		//System.out.println(String.valueOf(powerSwitchs.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(powerSwitchs.getTotalPages()));
		
		List<PowerSwitch> powerSwitchsList = powerSwitchs.getContent();
		
		PowerSwitchConvertCriteriaDTO convertCriteria = new PowerSwitchConvertCriteriaDTO();
		List<PowerSwitchDTO> powerSwitchDTOs = this.convertPowerSwitchsToPowerSwitchDTOs(powerSwitchsList,convertCriteria);
		
		PowerSwitchPageDTO powerSwitchPageDTO = new PowerSwitchPageDTO();
		powerSwitchPageDTO.setPowerSwitchs(powerSwitchDTOs);
		powerSwitchPageDTO.setTotalElements(powerSwitchs.getTotalElements());
		return ResponseEntity.ok(powerSwitchPageDTO);
	}

	public List<PowerSwitchDTO> convertPowerSwitchsToPowerSwitchDTOs(List<PowerSwitch> powerSwitchs, PowerSwitchConvertCriteriaDTO convertCriteria) {
		
		List<PowerSwitchDTO> powerSwitchDTOs = new ArrayList<PowerSwitchDTO>();
		
		for (PowerSwitch powerSwitch : powerSwitchs) {
			powerSwitchDTOs.add(convertPowerSwitchToPowerSwitchDTO(powerSwitch,convertCriteria));
		}
		
		return powerSwitchDTOs;

	}
	
	public PowerSwitchDTO convertPowerSwitchToPowerSwitchDTO(PowerSwitch powerSwitch, PowerSwitchConvertCriteriaDTO convertCriteria) {
		
		PowerSwitchDTO powerSwitchDTO = new PowerSwitchDTO();
		
		powerSwitchDTO.setPowerSwitchId(powerSwitch.getPowerSwitchId());

	
		powerSwitchDTO.setState(powerSwitch.getState());

	
		powerSwitchDTO.setControlVoltage(powerSwitch.getControlVoltage());

	

		
		return powerSwitchDTO;
	}

	public ResultDTO updatePowerSwitch(PowerSwitchDTO powerSwitchDTO, RequestDTO requestDTO) {
		
		PowerSwitch powerSwitch = powerSwitchDao.getById(powerSwitchDTO.getPowerSwitchId());

		powerSwitch.setPowerSwitchId(ControllerUtils.setValue(powerSwitch.getPowerSwitchId(), powerSwitchDTO.getPowerSwitchId()));

		powerSwitch.setState(ControllerUtils.setValue(powerSwitch.getState(), powerSwitchDTO.getState()));

		powerSwitch.setControlVoltage(ControllerUtils.setValue(powerSwitch.getControlVoltage(), powerSwitchDTO.getControlVoltage()));



        powerSwitch = powerSwitchDao.save(powerSwitch);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public PowerSwitchDTO getPowerSwitchDTOById(Integer powerSwitchId) {
	
		PowerSwitch powerSwitch = powerSwitchDao.getById(powerSwitchId);
			
		
		PowerSwitchConvertCriteriaDTO convertCriteria = new PowerSwitchConvertCriteriaDTO();
		return(this.convertPowerSwitchToPowerSwitchDTO(powerSwitch,convertCriteria));
	}







}
