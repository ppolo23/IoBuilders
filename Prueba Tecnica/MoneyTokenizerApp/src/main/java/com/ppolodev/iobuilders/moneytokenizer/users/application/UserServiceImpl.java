package com.ppolodev.iobuilders.moneytokenizer.users.application;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.dozermapper.core.Mapper;
import com.ppolodev.iobuilders.moneytokenizer.users.domain.UserEntity;
import com.ppolodev.iobuilders.moneytokenizer.users.domain.UserRepository;
import com.ppolodev.iobuilders.moneytokenizer.users.infrastructure.UserController;

@Service
public class UserServiceImpl implements UserService {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private Mapper mapper; 

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDTO createUser(String userName) {
		UserDTO userDTO = null;
		try {
			UserEntity newUser = userRepository.saveAndFlush(new UserEntity(userName));
			userDTO = mapper.map(newUser, UserDTO.class);
		} catch(Exception e) {
			logger.error(e.toString());
		}
		return userDTO;
	}

	@Override
	public UserDTO getUserById(int id) {
		UserDTO userDTO = null;
		try {
			UserEntity user = userRepository.findById(id);
			userDTO = mapper.map(user, UserDTO.class);
		} catch(Exception e) {
			logger.error(e.toString());
		}
		return userDTO;
	}

	@Override
	public UserDTO getUserByName(String name) {
		UserDTO userDTO = null;
		try {
			UserEntity user = userRepository.findByName(name);
			userDTO = mapper.map(user, UserDTO.class);
		} catch(Exception e) {
			logger.error(e.toString());
		}
		return userDTO;
	}

	@Override
	public List<UserDTO> getUsers(){
		List<UserDTO> usersDTO = null;
		try {
			List<UserEntity> users = userRepository.findAll();
			usersDTO = mapUserEntityListToUserDtoList(users);
		} catch(Exception e) {
			logger.error(e.toString());
		}
		return usersDTO;
	}

	private List<UserDTO> mapUserEntityListToUserDtoList(List<UserEntity> users){
		List<UserDTO> usersDTO = new ArrayList<>();
		for(UserEntity user: users) {
			UserDTO userDto = mapper.map(user, UserDTO.class);
			usersDTO.add(userDto);
		}
		return usersDTO;
	}
}
