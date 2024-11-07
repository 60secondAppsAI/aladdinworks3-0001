package com.aladdinworks3.dao;

import java.util.List;

import com.aladdinworks3.dao.GenericDAO;
import com.aladdinworks3.domain.Generator;





public interface GeneratorDAO extends GenericDAO<Generator, Integer> {
  
	List<Generator> findAll();
	






}


