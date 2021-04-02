package com.ppolodev.iobuilders.moneytokenizer.application.service;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import com.ppolodev.iobuilders.moneytokenizer.application.port.in.DepositUseCase;
import com.ppolodev.iobuilders.moneytokenizer.application.port.in.GetAllUsersUseCase;
import com.ppolodev.iobuilders.moneytokenizer.application.port.in.GetUserByUsernameUseCase;
import com.ppolodev.iobuilders.moneytokenizer.application.port.in.RegisterUserUseCase;
import com.ppolodev.iobuilders.moneytokenizer.application.port.in.TransferUseCase;
import com.ppolodev.iobuilders.moneytokenizer.application.port.in.WithdrawUseCase;
import com.ppolodev.iobuilders.moneytokenizer.application.port.out.LoadUserPort;
import com.ppolodev.iobuilders.moneytokenizer.application.port.out.PersistUserPort;
import com.ppolodev.iobuilders.moneytokenizer.domain.AccountDTO;
import com.ppolodev.iobuilders.moneytokenizer.domain.UserDTO;

@Service
public class UserService implements RegisterUserUseCase, GetAllUsersUseCase, GetUserByUsernameUseCase, 
DepositUseCase, WithdrawUseCase, TransferUseCase {
	
	Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private PersistUserPort persistUserPort;

	@Autowired
	private LoadUserPort loadUserPort;

	@Override
	public UserDTO registerUser(String username, String password) {
		AccountDTO account = createAccount(password);
		UserDTO user = new UserDTO(username, password, account);
		persistUserPort.saveUser(user);
		return user;
	}

	@Override
	public List<UserDTO> getAllUsers(){
		List<UserDTO> users = loadUserPort.loadUsers();
		return users;
	}

	@Override
	public UserDTO getUserByUsername(String username) {
		UserDTO user = loadUserPort.loadUserByUsername(username);
		return user;
	}

	@Override
	public void deposit(String username, Double amount) {
		UserDTO user = loadUserPort.loadUserByUsername(username);
		if(user != null) {
			Double actualBalance = user.getAccount().getBalance();
			user.getAccount().setBalance(actualBalance + amount);
			persistUserPort.saveUser(user);
		}
	}

	@Override
	public void withdraw(String username, Double amount) {
		UserDTO user = loadUserPort.loadUserByUsername(username);
		if(user != null) {
			Double actualBalance = user.getAccount().getBalance();
			if(amount <= actualBalance) {
				user.getAccount().setBalance(actualBalance - amount);
				persistUserPort.saveUser(user);
			}
		}
	}

	@Override
	public void transfer(String sender, Double amount, String receiver) {
		UserDTO senderDTO = loadUserPort.loadUserByUsername(sender);
		UserDTO receiverDTO = loadUserPort.loadUserByUsername(receiver);
		if(senderDTO != null && receiverDTO != null) {
			Double senderBalance = senderDTO.getAccount().getBalance();
			if(amount <= senderBalance) {
				senderDTO.getAccount().setBalance(senderBalance - amount);
				
				Double receiverBalance = receiverDTO.getAccount().getBalance();
				receiverDTO.getAccount().setBalance(receiverBalance + amount);
				
				persistUserPort.saveUser(senderDTO);
				persistUserPort.saveUser(receiverDTO);
			}

		}
	}

	private AccountDTO createAccount(String password) {
		AccountDTO accountDTO = null;
		String credentialsPath = "src/main/resources/credentials";
		try {
			String walletName = WalletUtils.generateNewWalletFile(password, new File(credentialsPath), false);
			Credentials credentials = WalletUtils.loadCredentials(password, credentialsPath + "/" + walletName);

			accountDTO = new AccountDTO(credentials.getAddress(), credentials.getEcKeyPair().getPrivateKey().toString());
		} catch(Exception e) {
			logger.error(e.toString());
		}
		return accountDTO;
	}
}
