package com.poly.service;

import java.util.List;

import com.poly.been.Account;


public interface AccountService {
	List<Account> findAll();
	Account findById(String username);
	Account saveProduct(Account account);
	void deleteAccount(String username);
	List<Account> getAdministrators();
}
