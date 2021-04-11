package com.ppolodev.iobuilders.moneytokenizer.application.port.in;

public interface WithdrawUseCase {

	boolean withdraw(String username, Double amount);
}
