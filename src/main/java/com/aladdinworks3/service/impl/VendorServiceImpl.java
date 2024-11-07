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
import com.aladdinworks3.dao.VendorDAO;
import com.aladdinworks3.domain.Vendor;
import com.aladdinworks3.dto.VendorDTO;
import com.aladdinworks3.dto.VendorSearchDTO;
import com.aladdinworks3.dto.VendorPageDTO;
import com.aladdinworks3.dto.VendorConvertCriteriaDTO;
import com.aladdinworks3.dto.common.RequestDTO;
import com.aladdinworks3.dto.common.ResultDTO;
import com.aladdinworks3.service.VendorService;
import com.aladdinworks3.util.ControllerUtils;





@Service
public class VendorServiceImpl extends GenericServiceImpl<Vendor, Integer> implements VendorService {

    private final static Logger logger = LoggerFactory.getLogger(VendorServiceImpl.class);

	@Autowired
	VendorDAO vendorDao;

	


	@Override
	public GenericDAO<Vendor, Integer> getDAO() {
		return (GenericDAO<Vendor, Integer>) vendorDao;
	}
	
	public List<Vendor> findAll () {
		List<Vendor> vendors = vendorDao.findAll();
		
		return vendors;	
		
	}

	public ResultDTO addVendor(VendorDTO vendorDTO, RequestDTO requestDTO) {

		Vendor vendor = new Vendor();

		vendor.setVendorId(vendorDTO.getVendorId());


		vendor.setName(vendorDTO.getName());


		vendor.setContactInfo(vendorDTO.getContactInfo());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		vendor = vendorDao.save(vendor);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Vendor> getAllVendors(Pageable pageable) {
		return vendorDao.findAll(pageable);
	}

	public Page<Vendor> getAllVendors(Specification<Vendor> spec, Pageable pageable) {
		return vendorDao.findAll(spec, pageable);
	}

	public ResponseEntity<VendorPageDTO> getVendors(VendorSearchDTO vendorSearchDTO) {
	
			Integer vendorId = vendorSearchDTO.getVendorId(); 
 			String name = vendorSearchDTO.getName(); 
 			String contactInfo = vendorSearchDTO.getContactInfo(); 
 			String sortBy = vendorSearchDTO.getSortBy();
			String sortOrder = vendorSearchDTO.getSortOrder();
			String searchQuery = vendorSearchDTO.getSearchQuery();
			Integer page = vendorSearchDTO.getPage();
			Integer size = vendorSearchDTO.getSize();

	        Specification<Vendor> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, vendorId, "vendorId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, contactInfo, "contactInfo"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("contactInfo")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Vendor> vendors = this.getAllVendors(spec, pageable);
		
		//System.out.println(String.valueOf(vendors.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(vendors.getTotalPages()));
		
		List<Vendor> vendorsList = vendors.getContent();
		
		VendorConvertCriteriaDTO convertCriteria = new VendorConvertCriteriaDTO();
		List<VendorDTO> vendorDTOs = this.convertVendorsToVendorDTOs(vendorsList,convertCriteria);
		
		VendorPageDTO vendorPageDTO = new VendorPageDTO();
		vendorPageDTO.setVendors(vendorDTOs);
		vendorPageDTO.setTotalElements(vendors.getTotalElements());
		return ResponseEntity.ok(vendorPageDTO);
	}

	public List<VendorDTO> convertVendorsToVendorDTOs(List<Vendor> vendors, VendorConvertCriteriaDTO convertCriteria) {
		
		List<VendorDTO> vendorDTOs = new ArrayList<VendorDTO>();
		
		for (Vendor vendor : vendors) {
			vendorDTOs.add(convertVendorToVendorDTO(vendor,convertCriteria));
		}
		
		return vendorDTOs;

	}
	
	public VendorDTO convertVendorToVendorDTO(Vendor vendor, VendorConvertCriteriaDTO convertCriteria) {
		
		VendorDTO vendorDTO = new VendorDTO();
		
		vendorDTO.setVendorId(vendor.getVendorId());

	
		vendorDTO.setName(vendor.getName());

	
		vendorDTO.setContactInfo(vendor.getContactInfo());

	

		
		return vendorDTO;
	}

	public ResultDTO updateVendor(VendorDTO vendorDTO, RequestDTO requestDTO) {
		
		Vendor vendor = vendorDao.getById(vendorDTO.getVendorId());

		vendor.setVendorId(ControllerUtils.setValue(vendor.getVendorId(), vendorDTO.getVendorId()));

		vendor.setName(ControllerUtils.setValue(vendor.getName(), vendorDTO.getName()));

		vendor.setContactInfo(ControllerUtils.setValue(vendor.getContactInfo(), vendorDTO.getContactInfo()));



        vendor = vendorDao.save(vendor);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public VendorDTO getVendorDTOById(Integer vendorId) {
	
		Vendor vendor = vendorDao.getById(vendorId);
			
		
		VendorConvertCriteriaDTO convertCriteria = new VendorConvertCriteriaDTO();
		return(this.convertVendorToVendorDTO(vendor,convertCriteria));
	}







}
