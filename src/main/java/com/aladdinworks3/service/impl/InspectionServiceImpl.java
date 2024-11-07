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
import com.aladdinworks3.dao.InspectionDAO;
import com.aladdinworks3.domain.Inspection;
import com.aladdinworks3.dto.InspectionDTO;
import com.aladdinworks3.dto.InspectionSearchDTO;
import com.aladdinworks3.dto.InspectionPageDTO;
import com.aladdinworks3.dto.InspectionConvertCriteriaDTO;
import com.aladdinworks3.dto.common.RequestDTO;
import com.aladdinworks3.dto.common.ResultDTO;
import com.aladdinworks3.service.InspectionService;
import com.aladdinworks3.util.ControllerUtils;





@Service
public class InspectionServiceImpl extends GenericServiceImpl<Inspection, Integer> implements InspectionService {

    private final static Logger logger = LoggerFactory.getLogger(InspectionServiceImpl.class);

	@Autowired
	InspectionDAO inspectionDao;

	


	@Override
	public GenericDAO<Inspection, Integer> getDAO() {
		return (GenericDAO<Inspection, Integer>) inspectionDao;
	}
	
	public List<Inspection> findAll () {
		List<Inspection> inspections = inspectionDao.findAll();
		
		return inspections;	
		
	}

	public ResultDTO addInspection(InspectionDTO inspectionDTO, RequestDTO requestDTO) {

		Inspection inspection = new Inspection();

		inspection.setInspectionId(inspectionDTO.getInspectionId());


		inspection.setDate(inspectionDTO.getDate());


		inspection.setInspectorName(inspectionDTO.getInspectorName());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		inspection = inspectionDao.save(inspection);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Inspection> getAllInspections(Pageable pageable) {
		return inspectionDao.findAll(pageable);
	}

	public Page<Inspection> getAllInspections(Specification<Inspection> spec, Pageable pageable) {
		return inspectionDao.findAll(spec, pageable);
	}

	public ResponseEntity<InspectionPageDTO> getInspections(InspectionSearchDTO inspectionSearchDTO) {
	
			Integer inspectionId = inspectionSearchDTO.getInspectionId(); 
   			String inspectorName = inspectionSearchDTO.getInspectorName(); 
 			String sortBy = inspectionSearchDTO.getSortBy();
			String sortOrder = inspectionSearchDTO.getSortOrder();
			String searchQuery = inspectionSearchDTO.getSearchQuery();
			Integer page = inspectionSearchDTO.getPage();
			Integer size = inspectionSearchDTO.getSize();

	        Specification<Inspection> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, inspectionId, "inspectionId"); 
			
 			
			spec = ControllerUtils.andIfNecessary(spec, inspectorName, "inspectorName"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("inspectorName")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Inspection> inspections = this.getAllInspections(spec, pageable);
		
		//System.out.println(String.valueOf(inspections.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(inspections.getTotalPages()));
		
		List<Inspection> inspectionsList = inspections.getContent();
		
		InspectionConvertCriteriaDTO convertCriteria = new InspectionConvertCriteriaDTO();
		List<InspectionDTO> inspectionDTOs = this.convertInspectionsToInspectionDTOs(inspectionsList,convertCriteria);
		
		InspectionPageDTO inspectionPageDTO = new InspectionPageDTO();
		inspectionPageDTO.setInspections(inspectionDTOs);
		inspectionPageDTO.setTotalElements(inspections.getTotalElements());
		return ResponseEntity.ok(inspectionPageDTO);
	}

	public List<InspectionDTO> convertInspectionsToInspectionDTOs(List<Inspection> inspections, InspectionConvertCriteriaDTO convertCriteria) {
		
		List<InspectionDTO> inspectionDTOs = new ArrayList<InspectionDTO>();
		
		for (Inspection inspection : inspections) {
			inspectionDTOs.add(convertInspectionToInspectionDTO(inspection,convertCriteria));
		}
		
		return inspectionDTOs;

	}
	
	public InspectionDTO convertInspectionToInspectionDTO(Inspection inspection, InspectionConvertCriteriaDTO convertCriteria) {
		
		InspectionDTO inspectionDTO = new InspectionDTO();
		
		inspectionDTO.setInspectionId(inspection.getInspectionId());

	
		inspectionDTO.setDate(inspection.getDate());

	
		inspectionDTO.setInspectorName(inspection.getInspectorName());

	

		
		return inspectionDTO;
	}

	public ResultDTO updateInspection(InspectionDTO inspectionDTO, RequestDTO requestDTO) {
		
		Inspection inspection = inspectionDao.getById(inspectionDTO.getInspectionId());

		inspection.setInspectionId(ControllerUtils.setValue(inspection.getInspectionId(), inspectionDTO.getInspectionId()));

		inspection.setDate(ControllerUtils.setValue(inspection.getDate(), inspectionDTO.getDate()));

		inspection.setInspectorName(ControllerUtils.setValue(inspection.getInspectorName(), inspectionDTO.getInspectorName()));



        inspection = inspectionDao.save(inspection);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public InspectionDTO getInspectionDTOById(Integer inspectionId) {
	
		Inspection inspection = inspectionDao.getById(inspectionId);
			
		
		InspectionConvertCriteriaDTO convertCriteria = new InspectionConvertCriteriaDTO();
		return(this.convertInspectionToInspectionDTO(inspection,convertCriteria));
	}







}
