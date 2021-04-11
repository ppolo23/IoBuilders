package com.ppolodev.iobuilders.moneytokenizer.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataAccountRepository extends JpaRepository<AccountEntity, Long> {
	
}