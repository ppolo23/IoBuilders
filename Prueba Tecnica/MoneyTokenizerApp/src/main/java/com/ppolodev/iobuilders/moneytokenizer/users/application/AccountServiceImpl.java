package com.ppolodev.iobuilders.moneytokenizer.users.application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthAccounts;

import com.github.dozermapper.core.Mapper;
import com.ppolodev.iobuilders.moneytokenizer.users.domain.AccountEntity;
import com.ppolodev.iobuilders.moneytokenizer.users.domain.AccountRepository;
import com.ppolodev.iobuilders.moneytokenizer.users.infrastructure.AccountController;

@Service
public class AccountServiceImpl implements AccountService {

	Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private Mapper mapper; 
	
	@Autowired
	private Web3j web3j;

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public AccountDTO createAccount(String name) {
		AccountDTO accountDTO = null;
		try {
			AccountEntity account = accountRepository.findByName(name);
			if(account == null) {
				logger.info("Creating new Account...");
				
				String walletName = WalletUtils.generateNewWalletFile("pwd", new File(credentialsPath), false);
				Credentials credentials = WalletUtils.loadCredentials("pwd", credentialsPath + "/" + walletName);
				
				account = new AccountEntity(name,credentials.getAddress(), credentials.getEcKeyPair().getPrivateKey().toString());
				
				account = accountRepository.saveAndFlush(account);
			}
			accountDTO = mapper.map(account, AccountDTO.class);
		} catch(Exception e) {
			logger.error(e.toString());
		}
		return accountDTO;
	}

	@Override
	public AccountDTO getAccountByName(String name) {
		AccountDTO accountDTO = null;
		try {
			AccountEntity account = accountRepository.findByName(name);
			accountDTO = mapper.map(account, AccountDTO.class);
		} catch(Exception e) {
			logger.error(e.toString());
		}
		return accountDTO;
	}

	@Override
	public List<AccountDTO> getAccounts(){
		List<AccountDTO> accountsDTO = null;
		try {
			List<AccountEntity> accounts = accountRepository.findAll();
			accountsDTO = mapUserEntityListToUserDtoList(accounts);
		} catch(Exception e) {
			logger.error(e.toString());
		}
		return accountsDTO;
	}

	private List<AccountDTO> mapUserEntityListToUserDtoList(List<AccountEntity> accounts){
		List<AccountDTO> accountsDTO = new ArrayList<>();
		for(AccountEntity account: accounts) {
			AccountDTO accountDto = mapper.map(account, AccountDTO.class);
			accountsDTO.add(accountDto);
		}
		return accountsDTO;
	}
}
