package com.aladdinworks3.dao;

import java.util.List;

import com.aladdinworks3.dao.GenericDAO;
import com.aladdinworks3.domain.PowerSwitch;





public interface PowerSwitchDAO extends GenericDAO<PowerSwitch, Integer> {
  
	List<PowerSwitch> findAll();
	






}


