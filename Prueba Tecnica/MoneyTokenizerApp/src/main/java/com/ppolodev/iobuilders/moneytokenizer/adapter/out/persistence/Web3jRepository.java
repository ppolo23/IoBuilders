package com.ppolodev.iobuilders.moneytokenizer.adapter.out.persistence;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

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

import com.ppolodev.iobuilders.moneytokenizer.application.port.out.Web3jPort;
import com.ppolodev.iobuilders.moneytokenizer.domain.AccountDTO;
import com.ppolodev.iobuilders.moneytokenizer.domain.contracts.IobCrowdsale;
import com.ppolodev.iobuilders.moneytokenizer.domain.contracts.IobToken;

@Service
public class Web3jRepository implements Web3jPort {

	private Logger logger = LoggerFactory.getLogger(Web3jRepository.class);
	
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
			IobToken token = this.loadIobToken(credentials);
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
			Credentials c = Credentials.create(faucetAddress);
			Transfer.sendFunds(web3j, c, credentials.getAddress(), BigDecimal.valueOf(1), Convert.Unit.ETHER).send();
		} catch (Exception e) {
			logger.error(e.toString());
			return false;
		}
		return true;
	}
	
	@Override
	public boolean buyIobTokens(AccountDTO account, Double amount) {
		try {
			Credentials credentials = this.loadCredentials(account);
			IobCrowdsale contract = this.loadIobCrowdsale(credentials);
			TransactionReceipt result = contract.buyTokens(credentials.getAddress(), BigInteger.valueOf(1000)).send();
			logger.info(result.getStatus().toString());
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
			IobToken contract = this.loadIobToken(credentials);
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
		return true;
	}

	private Credentials loadCredentials(AccountDTO account) throws IOException, CipherException {
		return  WalletUtils.loadCredentials(account.getPassword(), account.getWalletName());
	}
	
	private IobCrowdsale loadIobCrowdsale(Credentials credentials) {
		return IobCrowdsale.load(iobCrowdsaleAddress, web3j, credentials, new DefaultGasProvider());
	}
	
	private IobToken loadIobToken(Credentials credentials) {
		return IobToken.load(iobTokenAddress, web3j, credentials, new DefaultGasProvider());
	}
}
