package com.ppolodev.iobuilders.moneytokenizer.application.port.out;

import java.util.List;

import com.ppolodev.iobuilders.moneytokenizer.domain.UserDTO;

public interface LoadUserPort {
	
	UserDTO loadUserByUsername(String username);

	List<UserDTO> loadUsers();
}
