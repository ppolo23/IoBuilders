package com.ppolodev.iobuilders.moneytokenizer.application.port.in;

public interface TransferUseCase {

	Boolean transfer(String sender, Double amount, String receiver);
}
