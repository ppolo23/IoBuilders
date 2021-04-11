package com.ppolodev.iobuilders.moneytokenizer.adapter.out.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.dozermapper.core.Mapper;
import com.ppolodev.iobuilders.moneytokenizer.application.port.out.LoadUserPort;
import com.ppolodev.iobuilders.moneytokenizer.application.port.out.PersistUserPort;
import com.ppolodev.iobuilders.moneytokenizer.domain.UserDTO;

@Service
public class UserRepository implements PersistUserPort, LoadUserPort{

	@Autowired
	private SpringDataUserRepository repository;

	@Autowired
	private Mapper mapper;

	@Override
	public void saveUser(UserDTO user) {
		try {
			UserEntity entity = mapper.map(user, UserEntity.class);
			repository.saveAndFlush(entity);
		} catch(Exception e) {

		}
	}

	@Override
	public UserDTO loadUserByUsername(String username) {
		UserDTO dto = null;
		try {
			UserEntity entity = repository.findByUsername(username);
			dto = mapper.map(entity, UserDTO.class);	
		}  catch(Exception e) {

		}
		return dto;
	}

	@Override
	public List<UserDTO> loadUsers(){
		List<UserDTO> dtoList = new ArrayList<>();
		try {
			List<UserEntity> entityList = repository.findAll();
			for(UserEntity entity: entityList) {
				UserDTO dto = mapper.map(entity, UserDTO.class);
				dtoList.add(dto);
			}
		} catch(Exception e) {

		}

		return dtoList;
	}
}
