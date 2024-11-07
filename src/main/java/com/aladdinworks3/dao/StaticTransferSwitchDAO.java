package com.aladdinworks3.dao;

import java.util.List;

import com.aladdinworks3.dao.GenericDAO;
import com.aladdinworks3.domain.StaticTransferSwitch;





public interface StaticTransferSwitchDAO extends GenericDAO<StaticTransferSwitch, Integer> {
  
	List<StaticTransferSwitch> findAll();
	






}


