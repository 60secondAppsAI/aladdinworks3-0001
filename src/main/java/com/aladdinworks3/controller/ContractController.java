package com.aladdinworks3.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.aladdinworks3.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.aladdinworks3.domain.Contract;
import com.aladdinworks3.dto.ContractDTO;
import com.aladdinworks3.dto.ContractSearchDTO;
import com.aladdinworks3.dto.ContractPageDTO;
import com.aladdinworks3.service.ContractService;
import com.aladdinworks3.dto.common.RequestDTO;
import com.aladdinworks3.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/contract")
@RestController
public class ContractController {

	private final static Logger logger = LoggerFactory.getLogger(ContractController.class);

	@Autowired
	ContractService contractService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Contract> getAll() {

		List<Contract> contracts = contractService.findAll();
		
		return contracts;	
	}

	@GetMapping(value = "/{contractId}")
	@ResponseBody
	public ContractDTO getContract(@PathVariable Integer contractId) {
		
		return (contractService.getContractDTOById(contractId));
	}

 	@RequestMapping(value = "/addContract", method = RequestMethod.POST)
	public ResponseEntity<?> addContract(@RequestBody ContractDTO contractDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = contractService.addContract(contractDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/contracts")
	public ResponseEntity<ContractPageDTO> getContracts(ContractSearchDTO contractSearchDTO) {
 
		return contractService.getContracts(contractSearchDTO);
	}	

	@RequestMapping(value = "/updateContract", method = RequestMethod.POST)
	public ResponseEntity<?> updateContract(@RequestBody ContractDTO contractDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = contractService.updateContract(contractDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
