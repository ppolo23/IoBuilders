package com.ppolodev.iobuilders.moneytokenizer.application.port.out;

import com.ppolodev.iobuilders.moneytokenizer.domain.AccountDTO;

public interface IobWalletPort {
	
	final String iobWalletAddress = "0x5b1869D9A4C187F2EAa108f3062412ecf0526b24";
	
	boolean buyTokens(AccountDTO account, Double amount);
	
	boolean withdraw(AccountDTO account, Double amount);
	
	boolean transfer(AccountDTO sender, Double amount, AccountDTO receiver);
}
