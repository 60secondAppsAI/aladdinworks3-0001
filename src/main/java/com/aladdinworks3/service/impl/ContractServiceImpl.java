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
import com.aladdinworks3.dao.ContractDAO;
import com.aladdinworks3.domain.Contract;
import com.aladdinworks3.dto.ContractDTO;
import com.aladdinworks3.dto.ContractSearchDTO;
import com.aladdinworks3.dto.ContractPageDTO;
import com.aladdinworks3.dto.ContractConvertCriteriaDTO;
import com.aladdinworks3.dto.common.RequestDTO;
import com.aladdinworks3.dto.common.ResultDTO;
import com.aladdinworks3.service.ContractService;
import com.aladdinworks3.util.ControllerUtils;





@Service
public class ContractServiceImpl extends GenericServiceImpl<Contract, Integer> implements ContractService {

    private final static Logger logger = LoggerFactory.getLogger(ContractServiceImpl.class);

	@Autowired
	ContractDAO contractDao;

	


	@Override
	public GenericDAO<Contract, Integer> getDAO() {
		return (GenericDAO<Contract, Integer>) contractDao;
	}
	
	public List<Contract> findAll () {
		List<Contract> contracts = contractDao.findAll();
		
		return contracts;	
		
	}

	public ResultDTO addContract(ContractDTO contractDTO, RequestDTO requestDTO) {

		Contract contract = new Contract();

		contract.setContractId(contractDTO.getContractId());


		contract.setStartDate(contractDTO.getStartDate());


		contract.setEndDate(contractDTO.getEndDate());


		contract.setTerms(contractDTO.getTerms());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		contract = contractDao.save(contract);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Contract> getAllContracts(Pageable pageable) {
		return contractDao.findAll(pageable);
	}

	public Page<Contract> getAllContracts(Specification<Contract> spec, Pageable pageable) {
		return contractDao.findAll(spec, pageable);
	}

	public ResponseEntity<ContractPageDTO> getContracts(ContractSearchDTO contractSearchDTO) {
	
			Integer contractId = contractSearchDTO.getContractId(); 
     			String terms = contractSearchDTO.getTerms(); 
 			String sortBy = contractSearchDTO.getSortBy();
			String sortOrder = contractSearchDTO.getSortOrder();
			String searchQuery = contractSearchDTO.getSearchQuery();
			Integer page = contractSearchDTO.getPage();
			Integer size = contractSearchDTO.getSize();

	        Specification<Contract> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, contractId, "contractId"); 
			
 			
 			
			spec = ControllerUtils.andIfNecessary(spec, terms, "terms"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("terms")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Contract> contracts = this.getAllContracts(spec, pageable);
		
		//System.out.println(String.valueOf(contracts.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(contracts.getTotalPages()));
		
		List<Contract> contractsList = contracts.getContent();
		
		ContractConvertCriteriaDTO convertCriteria = new ContractConvertCriteriaDTO();
		List<ContractDTO> contractDTOs = this.convertContractsToContractDTOs(contractsList,convertCriteria);
		
		ContractPageDTO contractPageDTO = new ContractPageDTO();
		contractPageDTO.setContracts(contractDTOs);
		contractPageDTO.setTotalElements(contracts.getTotalElements());
		return ResponseEntity.ok(contractPageDTO);
	}

	public List<ContractDTO> convertContractsToContractDTOs(List<Contract> contracts, ContractConvertCriteriaDTO convertCriteria) {
		
		List<ContractDTO> contractDTOs = new ArrayList<ContractDTO>();
		
		for (Contract contract : contracts) {
			contractDTOs.add(convertContractToContractDTO(contract,convertCriteria));
		}
		
		return contractDTOs;

	}
	
	public ContractDTO convertContractToContractDTO(Contract contract, ContractConvertCriteriaDTO convertCriteria) {
		
		ContractDTO contractDTO = new ContractDTO();
		
		contractDTO.setContractId(contract.getContractId());

	
		contractDTO.setStartDate(contract.getStartDate());

	
		contractDTO.setEndDate(contract.getEndDate());

	
		contractDTO.setTerms(contract.getTerms());

	

		
		return contractDTO;
	}

	public ResultDTO updateContract(ContractDTO contractDTO, RequestDTO requestDTO) {
		
		Contract contract = contractDao.getById(contractDTO.getContractId());

		contract.setContractId(ControllerUtils.setValue(contract.getContractId(), contractDTO.getContractId()));

		contract.setStartDate(ControllerUtils.setValue(contract.getStartDate(), contractDTO.getStartDate()));

		contract.setEndDate(ControllerUtils.setValue(contract.getEndDate(), contractDTO.getEndDate()));

		contract.setTerms(ControllerUtils.setValue(contract.getTerms(), contractDTO.getTerms()));



        contract = contractDao.save(contract);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ContractDTO getContractDTOById(Integer contractId) {
	
		Contract contract = contractDao.getById(contractId);
			
		
		ContractConvertCriteriaDTO convertCriteria = new ContractConvertCriteriaDTO();
		return(this.convertContractToContractDTO(contract,convertCriteria));
	}







}
