package com.ppolodev.iobuilders.moneytokenizer.application.port.in;

public interface BuyIobTokensUseCase {
	
	Boolean buyIobTokens(String username, Double amount);
}
