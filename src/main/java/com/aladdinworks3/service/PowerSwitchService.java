package com.aladdinworks3.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks3.domain.PowerSwitch;
import com.aladdinworks3.dto.PowerSwitchDTO;
import com.aladdinworks3.dto.PowerSwitchSearchDTO;
import com.aladdinworks3.dto.PowerSwitchPageDTO;
import com.aladdinworks3.dto.PowerSwitchConvertCriteriaDTO;
import com.aladdinworks3.service.GenericService;
import com.aladdinworks3.dto.common.RequestDTO;
import com.aladdinworks3.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PowerSwitchService extends GenericService<PowerSwitch, Integer> {

	List<PowerSwitch> findAll();

	ResultDTO addPowerSwitch(PowerSwitchDTO powerSwitchDTO, RequestDTO requestDTO);

	ResultDTO updatePowerSwitch(PowerSwitchDTO powerSwitchDTO, RequestDTO requestDTO);

    Page<PowerSwitch> getAllPowerSwitchs(Pageable pageable);

    Page<PowerSwitch> getAllPowerSwitchs(Specification<PowerSwitch> spec, Pageable pageable);

	ResponseEntity<PowerSwitchPageDTO> getPowerSwitchs(PowerSwitchSearchDTO powerSwitchSearchDTO);
	
	List<PowerSwitchDTO> convertPowerSwitchsToPowerSwitchDTOs(List<PowerSwitch> powerSwitchs, PowerSwitchConvertCriteriaDTO convertCriteria);

	PowerSwitchDTO getPowerSwitchDTOById(Integer powerSwitchId);







}





