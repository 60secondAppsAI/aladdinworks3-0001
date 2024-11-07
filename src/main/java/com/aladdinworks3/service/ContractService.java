package com.aladdinworks3.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks3.domain.Contract;
import com.aladdinworks3.dto.ContractDTO;
import com.aladdinworks3.dto.ContractSearchDTO;
import com.aladdinworks3.dto.ContractPageDTO;
import com.aladdinworks3.dto.ContractConvertCriteriaDTO;
import com.aladdinworks3.service.GenericService;
import com.aladdinworks3.dto.common.RequestDTO;
import com.aladdinworks3.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ContractService extends GenericService<Contract, Integer> {

	List<Contract> findAll();

	ResultDTO addContract(ContractDTO contractDTO, RequestDTO requestDTO);

	ResultDTO updateContract(ContractDTO contractDTO, RequestDTO requestDTO);

    Page<Contract> getAllContracts(Pageable pageable);

    Page<Contract> getAllContracts(Specification<Contract> spec, Pageable pageable);

	ResponseEntity<ContractPageDTO> getContracts(ContractSearchDTO contractSearchDTO);
	
	List<ContractDTO> convertContractsToContractDTOs(List<Contract> contracts, ContractConvertCriteriaDTO convertCriteria);

	ContractDTO getContractDTOById(Integer contractId);







}





