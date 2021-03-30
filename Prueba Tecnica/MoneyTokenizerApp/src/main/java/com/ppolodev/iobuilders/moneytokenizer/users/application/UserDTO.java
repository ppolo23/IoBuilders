package com.ppolodev.iobuilders.moneytokenizer.users.application;

import java.util.List;

public class UserDTO {
	
	private int id;

	private String name;
	
	private List<AccountDTO>accounts;
	
	public UserDTO() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AccountDTO> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountDTO> accounts) {
		this.accounts = accounts;
	}

}
