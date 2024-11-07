package com.aladdinworks3.dao;

import java.util.List;

import com.aladdinworks3.dao.GenericDAO;
import com.aladdinworks3.domain.Technician;





public interface TechnicianDAO extends GenericDAO<Technician, Integer> {
  
	List<Technician> findAll();
	






}


