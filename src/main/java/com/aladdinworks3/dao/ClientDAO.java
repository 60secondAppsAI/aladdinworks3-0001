package com.aladdinworks3.dao;

import java.util.List;

import com.aladdinworks3.dao.GenericDAO;
import com.aladdinworks3.domain.Client;





public interface ClientDAO extends GenericDAO<Client, Integer> {
  
	List<Client> findAll();
	






}


