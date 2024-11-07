package com.aladdinworks3.dao;

import java.util.List;

import com.aladdinworks3.dao.GenericDAO;
import com.aladdinworks3.domain.MaintenanceRecord;





public interface MaintenanceRecordDAO extends GenericDAO<MaintenanceRecord, Integer> {
  
	List<MaintenanceRecord> findAll();
	






}


