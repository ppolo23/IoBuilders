package com.ppolodev.iobuilders.moneytokenizer.application.service;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.WalletUtils;

import com.ppolodev.iobuilders.moneytokenizer.application.port.in.BuyIobTokensUseCase;
import com.ppolodev.iobuilders.moneytokenizer.application.port.in.DepositUseCase;
import com.ppolodev.iobuilders.moneytokenizer.application.port.in.GetAllUsersUseCase;
import com.ppolodev.iobuilders.moneytokenizer.application.port.in.GetUserByUsernameUseCase;
import com.ppolodev.iobuilders.moneytokenizer.application.port.in.RegisterUserUseCase;
import com.ppolodev.iobuilders.moneytokenizer.application.port.in.TransferUseCase;
import com.ppolodev.iobuilders.moneytokenizer.application.port.in.WithdrawUseCase;
import com.ppolodev.iobuilders.moneytokenizer.application.port.out.IobTokenPort;
import com.ppolodev.iobuilders.moneytokenizer.application.port.out.IobWalletPort;
import com.ppolodev.iobuilders.moneytokenizer.application.port.out.LoadUserPort;
import com.ppolodev.iobuilders.moneytokenizer.application.port.out.PersistUserPort;
import com.ppolodev.iobuilders.moneytokenizer.domain.AccountDTO;
import com.ppolodev.iobuilders.moneytokenizer.domain.UserDTO;

@Service
public class UserService implements RegisterUserUseCase, GetAllUsersUseCase, GetUserByUsernameUseCase, BuyIobTokensUseCase,
DepositUseCase, WithdrawUseCase, TransferUseCase {
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private PersistUserPort persistUserPort;

	@Autowired
	private LoadUserPort loadUserPort;
	
	@Autowired
	private IobWalletPort iobWalletPort;
	
	@Autowired
	private IobTokenPort iobTokenPort;

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
	public Boolean deposit(String username, Double amount) {
		UserDTO user = loadUserPort.loadUserByUsername(username);
		if(user != null) {
			if(iobTokenPort.deposit(user.getAccount(), amount)) {
				user.getAccount().setEthBalance(iobTokenPort.getEthBalance(user.getAccount()));
				persistUserPort.saveUser(user);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Boolean buyIobTokens(String username, Double amount) {
		UserDTO user = loadUserPort.loadUserByUsername(username);
		if(user != null) {
			if(iobWalletPort.buyTokens(user.getAccount(), amount)) {
				user.getAccount().setEthBalance(iobTokenPort.getEthBalance(user.getAccount()));
				user.getAccount().setTokenBalance(iobTokenPort.getTokenBalance(user.getAccount()));
				persistUserPort.saveUser(user);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Boolean transfer(String sender, Double amount, String receiver) {
		UserDTO senderDTO = loadUserPort.loadUserByUsername(sender);
		UserDTO receiverDTO = loadUserPort.loadUserByUsername(receiver);
		if(senderDTO != null && receiverDTO != null) {
			Double senderBalance = senderDTO.getAccount().getTokenBalance();
			if(amount <= senderBalance) {
				if(iobWalletPort.transfer(senderDTO.getAccount(), amount, receiverDTO.getAccount())) {
					
					senderDTO.getAccount().setTokenBalance(iobTokenPort.getTokenBalance(senderDTO.getAccount()));
					senderDTO.getAccount().setEthBalance(iobTokenPort.getEthBalance(senderDTO.getAccount()));
					
					receiverDTO.getAccount().setTokenBalance(iobTokenPort.getTokenBalance(receiverDTO.getAccount()));
					receiverDTO.getAccount().setEthBalance(iobTokenPort.getEthBalance(receiverDTO.getAccount()));
					
					persistUserPort.saveUser(senderDTO);
					persistUserPort.saveUser(receiverDTO);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean withdraw(String username, Double amount) {
		UserDTO user = loadUserPort.loadUserByUsername(username);
		if(user != null) {
			Double actualBalance = user.getAccount().getTokenBalance();
			if(amount <= actualBalance) {
				if(iobWalletPort.withdraw(user.getAccount(), amount)) {
					user.getAccount().setTokenBalance(iobTokenPort.getTokenBalance(user.getAccount()));
					user.getAccount().setEthBalance(iobTokenPort.getEthBalance(user.getAccount()));
					persistUserPort.saveUser(user);
					return true;
				}
			}
		}
		return false;
	}

	private AccountDTO createAccount(String password) {
		AccountDTO accountDTO = null;
		String credentialsPath = "src/main/resources/credentials";
		try {
			String walletName = WalletUtils.generateNewWalletFile(password, new File(credentialsPath), false);
			accountDTO = new AccountDTO(credentialsPath + "/" + walletName, password);
		} catch(Exception e) {
			logger.error(e.toString());
		}
		return accountDTO;
	}
}
