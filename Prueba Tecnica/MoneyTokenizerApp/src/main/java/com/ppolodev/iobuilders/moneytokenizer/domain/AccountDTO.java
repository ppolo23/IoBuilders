package com.ppolodev.iobuilders.moneytokenizer.domain;

public class AccountDTO {

	private Long id;
	
	private String address;
	
	private String privateKey;
	
	private Double balance;
	
	public AccountDTO() {}
	
	public AccountDTO(String address, String privateKey) {
		this.address = address;
		this.privateKey = privateKey;
		this.balance = 0d;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
}
