package com.aladdinworks3.dao;

import java.util.List;

import com.aladdinworks3.dao.GenericDAO;
import com.aladdinworks3.domain.Inspection;





public interface InspectionDAO extends GenericDAO<Inspection, Integer> {
  
	List<Inspection> findAll();
	






}


