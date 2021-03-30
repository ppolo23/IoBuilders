package com.ppolodev.iobuilders.moneytokenizer.users.application;

public class AccountDTO {

	private int id;
	
	private String address;
	
	private Double balance;
	
	public AccountDTO() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
}
