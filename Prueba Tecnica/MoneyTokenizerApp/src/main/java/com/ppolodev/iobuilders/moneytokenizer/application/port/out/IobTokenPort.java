package com.ppolodev.iobuilders.moneytokenizer.application.port.out;

import com.ppolodev.iobuilders.moneytokenizer.domain.AccountDTO;

public interface IobTokenPort {
	
	final String iobTokenAddress = "0xe78A0F7E598Cc8b0Bb87894B0F60dD2a88d6a8Ab";
	
	final String faucetAddress = "0x6cbed15c793ce57650b9877cf6fa156fbef513c4e6134f022a85b1ffdd59b2a1";
	
	Double getTokenBalance(AccountDTO account);
	
	Double getEthBalance(AccountDTO account);

	boolean deposit(AccountDTO account, Double amount);
}
