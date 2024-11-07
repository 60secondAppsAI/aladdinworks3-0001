package com.aladdinworks3.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks3.domain.PowerMeter;
import com.aladdinworks3.dto.PowerMeterDTO;
import com.aladdinworks3.dto.PowerMeterSearchDTO;
import com.aladdinworks3.dto.PowerMeterPageDTO;
import com.aladdinworks3.dto.PowerMeterConvertCriteriaDTO;
import com.aladdinworks3.service.GenericService;
import com.aladdinworks3.dto.common.RequestDTO;
import com.aladdinworks3.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PowerMeterService extends GenericService<PowerMeter, Integer> {

	List<PowerMeter> findAll();

	ResultDTO addPowerMeter(PowerMeterDTO powerMeterDTO, RequestDTO requestDTO);

	ResultDTO updatePowerMeter(PowerMeterDTO powerMeterDTO, RequestDTO requestDTO);

    Page<PowerMeter> getAllPowerMeters(Pageable pageable);

    Page<PowerMeter> getAllPowerMeters(Specification<PowerMeter> spec, Pageable pageable);

	ResponseEntity<PowerMeterPageDTO> getPowerMeters(PowerMeterSearchDTO powerMeterSearchDTO);
	
	List<PowerMeterDTO> convertPowerMetersToPowerMeterDTOs(List<PowerMeter> powerMeters, PowerMeterConvertCriteriaDTO convertCriteria);

	PowerMeterDTO getPowerMeterDTOById(Integer powerMeterId);







}





