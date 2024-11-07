package com.aladdinworks3.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks3.domain.Technician;
import com.aladdinworks3.dto.TechnicianDTO;
import com.aladdinworks3.dto.TechnicianSearchDTO;
import com.aladdinworks3.dto.TechnicianPageDTO;
import com.aladdinworks3.dto.TechnicianConvertCriteriaDTO;
import com.aladdinworks3.service.GenericService;
import com.aladdinworks3.dto.common.RequestDTO;
import com.aladdinworks3.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface TechnicianService extends GenericService<Technician, Integer> {

	List<Technician> findAll();

	ResultDTO addTechnician(TechnicianDTO technicianDTO, RequestDTO requestDTO);

	ResultDTO updateTechnician(TechnicianDTO technicianDTO, RequestDTO requestDTO);

    Page<Technician> getAllTechnicians(Pageable pageable);

    Page<Technician> getAllTechnicians(Specification<Technician> spec, Pageable pageable);

	ResponseEntity<TechnicianPageDTO> getTechnicians(TechnicianSearchDTO technicianSearchDTO);
	
	List<TechnicianDTO> convertTechniciansToTechnicianDTOs(List<Technician> technicians, TechnicianConvertCriteriaDTO convertCriteria);

	TechnicianDTO getTechnicianDTOById(Integer technicianId);







}





