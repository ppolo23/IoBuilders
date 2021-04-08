package com.ppolodev.iobuilders.moneytokenizer.application.port.out;

import com.ppolodev.iobuilders.moneytokenizer.domain.AccountDTO;

public interface Web3jPort {
	
	final String iobCrowdsaleAddress = "0x5b1869D9A4C187F2EAa108f3062412ecf0526b24";
	
	final String iobTokenAddress = "";
	
	final String faucetAddress = "0x6cbed15c793ce57650b9877cf6fa156fbef513c4e6134f022a85b1ffdd59b2a1";
	
	Double getTokenBalance(AccountDTO account);
	
	Double getEthBalance(AccountDTO account);

	boolean deposit(AccountDTO account, Double amount);
	
	boolean buyIobTokens(AccountDTO account, Double amount);
	
	boolean withdraw(AccountDTO account, Double amount);
	
	boolean transfer(AccountDTO sender, Double amount, AccountDTO receiver);
}
