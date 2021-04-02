package com.ppolodev.iobuilders.moneytokenizer.application.port.in;

import java.util.List;

import com.ppolodev.iobuilders.moneytokenizer.domain.UserDTO;

public interface GetAllUsersUseCase {

	List<UserDTO> getAllUsers();
}
