package com.ppolodev.iobuilders.moneytokenizer.users.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
	
	@Query("SELECT u FROM AccountEntity u WHERE u.name = ?1")
	AccountEntity findByName(String name);
}