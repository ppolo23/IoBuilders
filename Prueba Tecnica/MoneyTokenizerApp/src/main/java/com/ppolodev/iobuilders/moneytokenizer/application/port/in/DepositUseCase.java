package com.ppolodev.iobuilders.moneytokenizer.application.port.in;

public interface DepositUseCase {

	Boolean deposit(String username, Double amount);
}
