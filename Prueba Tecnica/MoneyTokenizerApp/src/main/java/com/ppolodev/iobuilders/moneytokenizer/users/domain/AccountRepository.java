package com.ppolodev.iobuilders.moneytokenizer.users.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findById(int id);
	
	UserEntity findByName(String name);
}