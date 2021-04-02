package com.ppolodev.iobuilders.moneytokenizer.application.port.in;

public interface TransferUseCase {

	void transfer(String sender, Double amount, String receiver);
}
