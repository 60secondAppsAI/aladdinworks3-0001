package com.aladdinworks3.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks3.domain.Client;
import com.aladdinworks3.dto.ClientDTO;
import com.aladdinworks3.dto.ClientSearchDTO;
import com.aladdinworks3.dto.ClientPageDTO;
import com.aladdinworks3.dto.ClientConvertCriteriaDTO;
import com.aladdinworks3.service.GenericService;
import com.aladdinworks3.dto.common.RequestDTO;
import com.aladdinworks3.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ClientService extends GenericService<Client, Integer> {

	List<Client> findAll();

	ResultDTO addClient(ClientDTO clientDTO, RequestDTO requestDTO);

	ResultDTO updateClient(ClientDTO clientDTO, RequestDTO requestDTO);

    Page<Client> getAllClients(Pageable pageable);

    Page<Client> getAllClients(Specification<Client> spec, Pageable pageable);

	ResponseEntity<ClientPageDTO> getClients(ClientSearchDTO clientSearchDTO);
	
	List<ClientDTO> convertClientsToClientDTOs(List<Client> clients, ClientConvertCriteriaDTO convertCriteria);

	ClientDTO getClientDTOById(Integer clientId);







}





