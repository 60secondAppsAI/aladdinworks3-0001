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

import com.aladdinworks3.domain.Client;
import com.aladdinworks3.dto.ClientDTO;
import com.aladdinworks3.dto.ClientSearchDTO;
import com.aladdinworks3.dto.ClientPageDTO;
import com.aladdinworks3.service.ClientService;
import com.aladdinworks3.dto.common.RequestDTO;
import com.aladdinworks3.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/client")
@RestController
public class ClientController {

	private final static Logger logger = LoggerFactory.getLogger(ClientController.class);

	@Autowired
	ClientService clientService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Client> getAll() {

		List<Client> clients = clientService.findAll();
		
		return clients;	
	}

	@GetMapping(value = "/{clientId}")
	@ResponseBody
	public ClientDTO getClient(@PathVariable Integer clientId) {
		
		return (clientService.getClientDTOById(clientId));
	}

 	@RequestMapping(value = "/addClient", method = RequestMethod.POST)
	public ResponseEntity<?> addClient(@RequestBody ClientDTO clientDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = clientService.addClient(clientDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/clients")
	public ResponseEntity<ClientPageDTO> getClients(ClientSearchDTO clientSearchDTO) {
 
		return clientService.getClients(clientSearchDTO);
	}	

	@RequestMapping(value = "/updateClient", method = RequestMethod.POST)
	public ResponseEntity<?> updateClient(@RequestBody ClientDTO clientDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = clientService.updateClient(clientDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
