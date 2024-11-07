package com.aladdinworks3.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks3.domain.Inspection;
import com.aladdinworks3.dto.InspectionDTO;
import com.aladdinworks3.dto.InspectionSearchDTO;
import com.aladdinworks3.dto.InspectionPageDTO;
import com.aladdinworks3.dto.InspectionConvertCriteriaDTO;
import com.aladdinworks3.service.GenericService;
import com.aladdinworks3.dto.common.RequestDTO;
import com.aladdinworks3.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface InspectionService extends GenericService<Inspection, Integer> {

	List<Inspection> findAll();

	ResultDTO addInspection(InspectionDTO inspectionDTO, RequestDTO requestDTO);

	ResultDTO updateInspection(InspectionDTO inspectionDTO, RequestDTO requestDTO);

    Page<Inspection> getAllInspections(Pageable pageable);

    Page<Inspection> getAllInspections(Specification<Inspection> spec, Pageable pageable);

	ResponseEntity<InspectionPageDTO> getInspections(InspectionSearchDTO inspectionSearchDTO);
	
	List<InspectionDTO> convertInspectionsToInspectionDTOs(List<Inspection> inspections, InspectionConvertCriteriaDTO convertCriteria);

	InspectionDTO getInspectionDTOById(Integer inspectionId);







}





