package com.ppolodev.iobuilders.moneytokenizer.adapter.out.persistence;

import java.io.IOException;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;

import com.ppolodev.iobuilders.moneytokenizer.application.port.out.IobTokenPort;
import com.ppolodev.iobuilders.moneytokenizer.application.port.out.IobWalletPort;
import com.ppolodev.iobuilders.moneytokenizer.domain.AccountDTO;
import com.ppolodev.iobuilders.moneytokenizer.domain.contracts.IobToken;
import com.ppolodev.iobuilders.moneytokenizer.domain.contracts.IobWallet;

@Service
public class SmartContractRepository implements IobTokenPort, IobWalletPort {

	private Logger logger = LoggerFactory.getLogger(SmartContractRepository.class);
	
	private Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));
	
	@Override
	public Double getEthBalance(AccountDTO account) {
		Double balance = null;
		try {
			Credentials credentials = this.loadCredentials(account);
			EthGetBalance ethGetBalance = web3j.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST).send();
			balance = Convert.fromWei(ethGetBalance.getBalance().toString(), Convert.Unit.ETHER).doubleValue();
		} catch(Exception e) {
			logger.error(e.toString());
		}
		return balance;
	}
	
	@Override
	public Double getTokenBalance(AccountDTO account) {
		Double balance = null;
		try {
			Credentials credentials = this.loadCredentials(account);
			IobToken token = this.loadIobTokenContract(credentials);
			balance = token.balanceOf(credentials.getAddress()).send().doubleValue();
		} catch(Exception e) {
			logger.error(e.toString());
		}
		return balance;
	}

	@Override
	public boolean deposit(AccountDTO account, Double amount) {
		try {
			Credentials credentials = this.loadCredentials(account);
			Credentials faucet = Credentials.create(faucetAddress);
			Transfer.sendFunds(web3j, faucet, credentials.getAddress(), BigDecimal.valueOf(1), Convert.Unit.ETHER).send();
		} catch (Exception e) {
			logger.error(e.toString());
			return false;
		}
		return true;
	}
	
	@Override
	public boolean buyTokens(AccountDTO account, Double amount) {
		try {
			Credentials credentials = this.loadCredentials(account);
			
			IobToken tokenContract = this.loadIobTokenContract(credentials);
			IobWallet walletContract = this.loadIobWalletContract(credentials);
			
			TransactionReceipt allowanceResult = tokenContract.increaseAllowance(walletContract.getContractAddress(), BigDecimal.valueOf(amount).toBigInteger()).send();
			logger.info(allowanceResult.getStatus().toString());
			
			TransactionReceipt buyResult = walletContract.buy(BigDecimal.valueOf(amount).toBigInteger()).send();
			logger.info(buyResult.getStatus().toString());
		} catch (Exception e) {
			logger.error(e.toString());
			return false;
		}
		return true;
	}
	
	@Override
	public boolean transfer(AccountDTO sender, Double amount, AccountDTO receiver) {
		try {
			Credentials credentials = this.loadCredentials(sender);
			Credentials receiverCredentials = this.loadCredentials(receiver);
			IobToken contract = this.loadIobTokenContract(credentials);
			TransactionReceipt result = contract.transfer(receiverCredentials.getAddress(), BigDecimal.valueOf(amount).toBigInteger()).send();
			logger.info(result.getStatus().toString());
		} catch (Exception e) {
			logger.error(e.toString());
			return false;
		}
		return true;
	}
	
	@Override
	public boolean withdraw(AccountDTO account, Double amount) {
		try {
			Credentials credentials = this.loadCredentials(account);
			IobWallet contract = this.loadIobWalletContract(credentials);
			TransactionReceipt result = contract.sell(BigDecimal.valueOf(amount).toBigInteger()).send();
			logger.info(result.getStatus().toString());
		} catch (Exception e) {
			logger.error(e.toString());
			return false;
		}
		return true;
	}

	private Credentials loadCredentials(AccountDTO account) throws IOException, CipherException {
		return  WalletUtils.loadCredentials(account.getPassword(), account.getWalletName());
	}
	
	private IobWallet loadIobWalletContract(Credentials credentials) {
		return IobWallet.load(iobWalletAddress, web3j, credentials, new DefaultGasProvider());
	}
	
	private IobToken loadIobTokenContract(Credentials credentials) {
		return IobToken.load(iobTokenAddress, web3j, credentials, new DefaultGasProvider());
	}
}
