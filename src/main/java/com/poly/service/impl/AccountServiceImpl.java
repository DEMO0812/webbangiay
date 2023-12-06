package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.been.Account;
import com.poly.dao.AccountDAO;
import com.poly.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	AccountDAO aDao;
	
	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return aDao.findAll();
	}

	@Override
	public Account findById(String username) {
		// TODO Auto-generated method stub
		return aDao.findById(username).orElse(null);
	}

	@Override
	public Account saveProduct(Account account) {
		// TODO Auto-generated method stub
		return aDao.save(account);
	}

	@Override
	public void deleteAccount(String username) {
		aDao.deleteById(username);
		
	}

	// lấy ra tk admin
	@Override
	public List<Account> getAdministrators() {
		return aDao.getAdministrators();
	}

}
