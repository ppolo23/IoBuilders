package com.ppolodev.iobuilders.moneytokenizer.users.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Accounts")
public class AccountEntity {
	
	@Id
	private int id;
	
	private String address;
	
	private Double balance;
	
	public AccountEntity() {}
	
	public AccountEntity(String address) {
		this.address = address;
		this.balance = 0d;
	}

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
