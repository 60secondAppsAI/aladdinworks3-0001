package com.aladdinworks3.dao;

import java.util.List;

import com.aladdinworks3.dao.GenericDAO;
import com.aladdinworks3.domain.Contract;





public interface ContractDAO extends GenericDAO<Contract, Integer> {
  
	List<Contract> findAll();
	






}


