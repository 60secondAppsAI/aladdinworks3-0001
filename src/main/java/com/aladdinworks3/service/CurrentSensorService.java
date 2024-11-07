package com.aladdinworks3.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks3.domain.CurrentSensor;
import com.aladdinworks3.dto.CurrentSensorDTO;
import com.aladdinworks3.dto.CurrentSensorSearchDTO;
import com.aladdinworks3.dto.CurrentSensorPageDTO;
import com.aladdinworks3.dto.CurrentSensorConvertCriteriaDTO;
import com.aladdinworks3.service.GenericService;
import com.aladdinworks3.dto.common.RequestDTO;
import com.aladdinworks3.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CurrentSensorService extends GenericService<CurrentSensor, Integer> {

	List<CurrentSensor> findAll();

	ResultDTO addCurrentSensor(CurrentSensorDTO currentSensorDTO, RequestDTO requestDTO);

	ResultDTO updateCurrentSensor(CurrentSensorDTO currentSensorDTO, RequestDTO requestDTO);

    Page<CurrentSensor> getAllCurrentSensors(Pageable pageable);

    Page<CurrentSensor> getAllCurrentSensors(Specification<CurrentSensor> spec, Pageable pageable);

	ResponseEntity<CurrentSensorPageDTO> getCurrentSensors(CurrentSensorSearchDTO currentSensorSearchDTO);
	
	List<CurrentSensorDTO> convertCurrentSensorsToCurrentSensorDTOs(List<CurrentSensor> currentSensors, CurrentSensorConvertCriteriaDTO convertCriteria);

	CurrentSensorDTO getCurrentSensorDTOById(Integer currentSensorId);







}





