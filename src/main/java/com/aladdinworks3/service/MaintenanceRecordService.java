package com.aladdinworks3.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks3.domain.MaintenanceRecord;
import com.aladdinworks3.dto.MaintenanceRecordDTO;
import com.aladdinworks3.dto.MaintenanceRecordSearchDTO;
import com.aladdinworks3.dto.MaintenanceRecordPageDTO;
import com.aladdinworks3.dto.MaintenanceRecordConvertCriteriaDTO;
import com.aladdinworks3.service.GenericService;
import com.aladdinworks3.dto.common.RequestDTO;
import com.aladdinworks3.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface MaintenanceRecordService extends GenericService<MaintenanceRecord, Integer> {

	List<MaintenanceRecord> findAll();

	ResultDTO addMaintenanceRecord(MaintenanceRecordDTO maintenanceRecordDTO, RequestDTO requestDTO);

	ResultDTO updateMaintenanceRecord(MaintenanceRecordDTO maintenanceRecordDTO, RequestDTO requestDTO);

    Page<MaintenanceRecord> getAllMaintenanceRecords(Pageable pageable);

    Page<MaintenanceRecord> getAllMaintenanceRecords(Specification<MaintenanceRecord> spec, Pageable pageable);

	ResponseEntity<MaintenanceRecordPageDTO> getMaintenanceRecords(MaintenanceRecordSearchDTO maintenanceRecordSearchDTO);
	
	List<MaintenanceRecordDTO> convertMaintenanceRecordsToMaintenanceRecordDTOs(List<MaintenanceRecord> maintenanceRecords, MaintenanceRecordConvertCriteriaDTO convertCriteria);

	MaintenanceRecordDTO getMaintenanceRecordDTOById(Integer maintenanceRecordId);







}





