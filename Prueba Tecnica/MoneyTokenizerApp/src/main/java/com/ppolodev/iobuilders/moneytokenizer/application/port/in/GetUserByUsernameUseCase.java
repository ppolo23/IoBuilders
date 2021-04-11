package com.ppolodev.iobuilders.moneytokenizer.application.port.in;

import com.ppolodev.iobuilders.moneytokenizer.domain.UserDTO;

public interface GetUserByUsernameUseCase {

	UserDTO getUserByUsername(String username);
}
