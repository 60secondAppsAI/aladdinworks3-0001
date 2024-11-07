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
import com.aladdinworks3.dao.ClientDAO;
import com.aladdinworks3.domain.Client;
import com.aladdinworks3.dto.ClientDTO;
import com.aladdinworks3.dto.ClientSearchDTO;
import com.aladdinworks3.dto.ClientPageDTO;
import com.aladdinworks3.dto.ClientConvertCriteriaDTO;
import com.aladdinworks3.dto.common.RequestDTO;
import com.aladdinworks3.dto.common.ResultDTO;
import com.aladdinworks3.service.ClientService;
import com.aladdinworks3.util.ControllerUtils;





@Service
public class ClientServiceImpl extends GenericServiceImpl<Client, Integer> implements ClientService {

    private final static Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

	@Autowired
	ClientDAO clientDao;

	


	@Override
	public GenericDAO<Client, Integer> getDAO() {
		return (GenericDAO<Client, Integer>) clientDao;
	}
	
	public List<Client> findAll () {
		List<Client> clients = clientDao.findAll();
		
		return clients;	
		
	}

	public ResultDTO addClient(ClientDTO clientDTO, RequestDTO requestDTO) {

		Client client = new Client();

		client.setClientId(clientDTO.getClientId());


		client.setName(clientDTO.getName());


		client.setContactInfo(clientDTO.getContactInfo());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		client = clientDao.save(client);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Client> getAllClients(Pageable pageable) {
		return clientDao.findAll(pageable);
	}

	public Page<Client> getAllClients(Specification<Client> spec, Pageable pageable) {
		return clientDao.findAll(spec, pageable);
	}

	public ResponseEntity<ClientPageDTO> getClients(ClientSearchDTO clientSearchDTO) {
	
			Integer clientId = clientSearchDTO.getClientId(); 
 			String name = clientSearchDTO.getName(); 
 			String contactInfo = clientSearchDTO.getContactInfo(); 
 			String sortBy = clientSearchDTO.getSortBy();
			String sortOrder = clientSearchDTO.getSortOrder();
			String searchQuery = clientSearchDTO.getSearchQuery();
			Integer page = clientSearchDTO.getPage();
			Integer size = clientSearchDTO.getSize();

	        Specification<Client> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, clientId, "clientId"); 
			
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

		Page<Client> clients = this.getAllClients(spec, pageable);
		
		//System.out.println(String.valueOf(clients.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(clients.getTotalPages()));
		
		List<Client> clientsList = clients.getContent();
		
		ClientConvertCriteriaDTO convertCriteria = new ClientConvertCriteriaDTO();
		List<ClientDTO> clientDTOs = this.convertClientsToClientDTOs(clientsList,convertCriteria);
		
		ClientPageDTO clientPageDTO = new ClientPageDTO();
		clientPageDTO.setClients(clientDTOs);
		clientPageDTO.setTotalElements(clients.getTotalElements());
		return ResponseEntity.ok(clientPageDTO);
	}

	public List<ClientDTO> convertClientsToClientDTOs(List<Client> clients, ClientConvertCriteriaDTO convertCriteria) {
		
		List<ClientDTO> clientDTOs = new ArrayList<ClientDTO>();
		
		for (Client client : clients) {
			clientDTOs.add(convertClientToClientDTO(client,convertCriteria));
		}
		
		return clientDTOs;

	}
	
	public ClientDTO convertClientToClientDTO(Client client, ClientConvertCriteriaDTO convertCriteria) {
		
		ClientDTO clientDTO = new ClientDTO();
		
		clientDTO.setClientId(client.getClientId());

	
		clientDTO.setName(client.getName());

	
		clientDTO.setContactInfo(client.getContactInfo());

	

		
		return clientDTO;
	}

	public ResultDTO updateClient(ClientDTO clientDTO, RequestDTO requestDTO) {
		
		Client client = clientDao.getById(clientDTO.getClientId());

		client.setClientId(ControllerUtils.setValue(client.getClientId(), clientDTO.getClientId()));

		client.setName(ControllerUtils.setValue(client.getName(), clientDTO.getName()));

		client.setContactInfo(ControllerUtils.setValue(client.getContactInfo(), clientDTO.getContactInfo()));



        client = clientDao.save(client);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ClientDTO getClientDTOById(Integer clientId) {
	
		Client client = clientDao.getById(clientId);
			
		
		ClientConvertCriteriaDTO convertCriteria = new ClientConvertCriteriaDTO();
		return(this.convertClientToClientDTO(client,convertCriteria));
	}







}
