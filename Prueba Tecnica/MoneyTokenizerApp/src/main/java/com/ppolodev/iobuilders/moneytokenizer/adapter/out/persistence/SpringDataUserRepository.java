package com.ppolodev.iobuilders.moneytokenizer.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, Long>{

	UserEntity findByUsername(String username);
}
