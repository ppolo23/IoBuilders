package com.ppolodev.iobuilders.moneytokenizer.application.port.out;

import com.ppolodev.iobuilders.moneytokenizer.domain.UserDTO;

public interface PersistUserPort {

	void saveUser(UserDTO user);
}
