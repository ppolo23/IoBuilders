package com.ppolodev.iobuilders.moneytokenizer.users.application;

import java.util.List;

public interface UserService {

	UserDTO createUser(String userName);
	
	UserDTO getUserById(int id);
	
	UserDTO getUserByName(String name);
	
	List<UserDTO> getUsers();
}
