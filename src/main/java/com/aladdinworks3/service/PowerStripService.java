package com.aladdinworks3.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks3.domain.PowerStrip;
import com.aladdinworks3.dto.PowerStripDTO;
import com.aladdinworks3.dto.PowerStripSearchDTO;
import com.aladdinworks3.dto.PowerStripPageDTO;
import com.aladdinworks3.dto.PowerStripConvertCriteriaDTO;
import com.aladdinworks3.service.GenericService;
import com.aladdinworks3.dto.common.RequestDTO;
import com.aladdinworks3.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PowerStripService extends GenericService<PowerStrip, Integer> {

	List<PowerStrip> findAll();

	ResultDTO addPowerStrip(PowerStripDTO powerStripDTO, RequestDTO requestDTO);

	ResultDTO updatePowerStrip(PowerStripDTO powerStripDTO, RequestDTO requestDTO);

    Page<PowerStrip> getAllPowerStrips(Pageable pageable);

    Page<PowerStrip> getAllPowerStrips(Specification<PowerStrip> spec, Pageable pageable);

	ResponseEntity<PowerStripPageDTO> getPowerStrips(PowerStripSearchDTO powerStripSearchDTO);
	
	List<PowerStripDTO> convertPowerStripsToPowerStripDTOs(List<PowerStrip> powerStrips, PowerStripConvertCriteriaDTO convertCriteria);

	PowerStripDTO getPowerStripDTOById(Integer powerStripId);







}





