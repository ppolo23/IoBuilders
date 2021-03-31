package com.ppolodev.iobuilders.moneytokenizer.users.application;

import java.util.List;

public interface AccountService {
	
	final String credentialsPath = "src/main/resources/credentials";

	AccountDTO createAccount(String name);
	
	AccountDTO getAccountByName(String name);
	
	List<AccountDTO> getAccounts();
}
