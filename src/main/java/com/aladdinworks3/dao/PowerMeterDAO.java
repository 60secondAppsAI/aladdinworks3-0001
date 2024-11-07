package com.aladdinworks3.dao;

import java.util.List;

import com.aladdinworks3.dao.GenericDAO;
import com.aladdinworks3.domain.PowerMeter;





public interface PowerMeterDAO extends GenericDAO<PowerMeter, Integer> {
  
	List<PowerMeter> findAll();
	






}


