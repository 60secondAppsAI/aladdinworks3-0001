package com.aladdinworks3.dao;

import java.util.List;

import com.aladdinworks3.dao.GenericDAO;
import com.aladdinworks3.domain.Vendor;





public interface VendorDAO extends GenericDAO<Vendor, Integer> {
  
	List<Vendor> findAll();
	






}


