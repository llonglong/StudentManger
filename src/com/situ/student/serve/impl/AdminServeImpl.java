package com.situ.student.serve.impl;

import com.situ.student.dao.IAdminDao;
import com.situ.student.dao.impl.AdminDaoImpl;
import com.situ.student.pojo.Admin;
import com.situ.student.serve.IAdminServe;

public class AdminServeImpl implements IAdminServe{
	IAdminDao adminDao = new AdminDaoImpl();

	@Override
	public Admin findByNameAndPassword(String name, String password) {
		// TODO Auto-generated method stub
		return adminDao.findByNameAndPassword(name,password);
	}

}
