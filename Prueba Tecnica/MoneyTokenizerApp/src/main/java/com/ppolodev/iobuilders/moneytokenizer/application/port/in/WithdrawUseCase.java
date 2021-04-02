package com.ppolodev.iobuilders.moneytokenizer.application.port.in;

public interface WithdrawUseCase {

	void withdraw(String username, Double amount);
}
