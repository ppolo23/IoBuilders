package com.ppolodev.iobuilders.moneytokenizer.domain;

public class UserDTO {
	
	private Long id;
	
	private String username;
	
	private String password;
	
	private AccountDTO account;
	
	public UserDTO() {}
	
	public UserDTO(String username, String password, AccountDTO account) {
		this.username = username;
		this.password = password;
		this.account = account;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AccountDTO getAccount() {
		return account;
	}

	public void setAccount(AccountDTO account) {
		this.account = account;
	}

}
