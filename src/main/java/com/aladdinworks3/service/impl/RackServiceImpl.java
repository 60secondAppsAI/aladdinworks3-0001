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
import com.aladdinworks3.dao.RackDAO;
import com.aladdinworks3.domain.Rack;
import com.aladdinworks3.dto.RackDTO;
import com.aladdinworks3.dto.RackSearchDTO;
import com.aladdinworks3.dto.RackPageDTO;
import com.aladdinworks3.dto.RackConvertCriteriaDTO;
import com.aladdinworks3.dto.common.RequestDTO;
import com.aladdinworks3.dto.common.ResultDTO;
import com.aladdinworks3.service.RackService;
import com.aladdinworks3.util.ControllerUtils;





@Service
public class RackServiceImpl extends GenericServiceImpl<Rack, Integer> implements RackService {

    private final static Logger logger = LoggerFactory.getLogger(RackServiceImpl.class);

	@Autowired
	RackDAO rackDao;

	


	@Override
	public GenericDAO<Rack, Integer> getDAO() {
		return (GenericDAO<Rack, Integer>) rackDao;
	}
	
	public List<Rack> findAll () {
		List<Rack> racks = rackDao.findAll();
		
		return racks;	
		
	}

	public ResultDTO addRack(RackDTO rackDTO, RequestDTO requestDTO) {

		Rack rack = new Rack();

		rack.setRackId(rackDTO.getRackId());


		rack.setLocation(rackDTO.getLocation());


		rack.setCapacity(rackDTO.getCapacity());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		rack = rackDao.save(rack);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Rack> getAllRacks(Pageable pageable) {
		return rackDao.findAll(pageable);
	}

	public Page<Rack> getAllRacks(Specification<Rack> spec, Pageable pageable) {
		return rackDao.findAll(spec, pageable);
	}

	public ResponseEntity<RackPageDTO> getRacks(RackSearchDTO rackSearchDTO) {
	
			Integer rackId = rackSearchDTO.getRackId(); 
 			String location = rackSearchDTO.getLocation(); 
 			Integer capacity = rackSearchDTO.getCapacity(); 
 			String sortBy = rackSearchDTO.getSortBy();
			String sortOrder = rackSearchDTO.getSortOrder();
			String searchQuery = rackSearchDTO.getSearchQuery();
			Integer page = rackSearchDTO.getPage();
			Integer size = rackSearchDTO.getSize();

	        Specification<Rack> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, rackId, "rackId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, location, "location"); 
			
			spec = ControllerUtils.andIfNecessary(spec, capacity, "capacity"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("location")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Rack> racks = this.getAllRacks(spec, pageable);
		
		//System.out.println(String.valueOf(racks.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(racks.getTotalPages()));
		
		List<Rack> racksList = racks.getContent();
		
		RackConvertCriteriaDTO convertCriteria = new RackConvertCriteriaDTO();
		List<RackDTO> rackDTOs = this.convertRacksToRackDTOs(racksList,convertCriteria);
		
		RackPageDTO rackPageDTO = new RackPageDTO();
		rackPageDTO.setRacks(rackDTOs);
		rackPageDTO.setTotalElements(racks.getTotalElements());
		return ResponseEntity.ok(rackPageDTO);
	}

	public List<RackDTO> convertRacksToRackDTOs(List<Rack> racks, RackConvertCriteriaDTO convertCriteria) {
		
		List<RackDTO> rackDTOs = new ArrayList<RackDTO>();
		
		for (Rack rack : racks) {
			rackDTOs.add(convertRackToRackDTO(rack,convertCriteria));
		}
		
		return rackDTOs;

	}
	
	public RackDTO convertRackToRackDTO(Rack rack, RackConvertCriteriaDTO convertCriteria) {
		
		RackDTO rackDTO = new RackDTO();
		
		rackDTO.setRackId(rack.getRackId());

	
		rackDTO.setLocation(rack.getLocation());

	
		rackDTO.setCapacity(rack.getCapacity());

	

		
		return rackDTO;
	}

	public ResultDTO updateRack(RackDTO rackDTO, RequestDTO requestDTO) {
		
		Rack rack = rackDao.getById(rackDTO.getRackId());

		rack.setRackId(ControllerUtils.setValue(rack.getRackId(), rackDTO.getRackId()));

		rack.setLocation(ControllerUtils.setValue(rack.getLocation(), rackDTO.getLocation()));

		rack.setCapacity(ControllerUtils.setValue(rack.getCapacity(), rackDTO.getCapacity()));



        rack = rackDao.save(rack);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public RackDTO getRackDTOById(Integer rackId) {
	
		Rack rack = rackDao.getById(rackId);
			
		
		RackConvertCriteriaDTO convertCriteria = new RackConvertCriteriaDTO();
		return(this.convertRackToRackDTO(rack,convertCriteria));
	}







}