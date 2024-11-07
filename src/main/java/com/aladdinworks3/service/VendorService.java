package com.aladdinworks3.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks3.domain.Vendor;
import com.aladdinworks3.dto.VendorDTO;
import com.aladdinworks3.dto.VendorSearchDTO;
import com.aladdinworks3.dto.VendorPageDTO;
import com.aladdinworks3.dto.VendorConvertCriteriaDTO;
import com.aladdinworks3.service.GenericService;
import com.aladdinworks3.dto.common.RequestDTO;
import com.aladdinworks3.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface VendorService extends GenericService<Vendor, Integer> {

	List<Vendor> findAll();

	ResultDTO addVendor(VendorDTO vendorDTO, RequestDTO requestDTO);

	ResultDTO updateVendor(VendorDTO vendorDTO, RequestDTO requestDTO);

    Page<Vendor> getAllVendors(Pageable pageable);

    Page<Vendor> getAllVendors(Specification<Vendor> spec, Pageable pageable);

	ResponseEntity<VendorPageDTO> getVendors(VendorSearchDTO vendorSearchDTO);
	
	List<VendorDTO> convertVendorsToVendorDTOs(List<Vendor> vendors, VendorConvertCriteriaDTO convertCriteria);

	VendorDTO getVendorDTOById(Integer vendorId);







}





