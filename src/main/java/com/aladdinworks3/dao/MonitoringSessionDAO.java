package com.aladdinworks3.dao;

import java.util.List;

import com.aladdinworks3.dao.GenericDAO;
import com.aladdinworks3.domain.MonitoringSession;





public interface MonitoringSessionDAO extends GenericDAO<MonitoringSession, Integer> {
  
	List<MonitoringSession> findAll();
	






}


