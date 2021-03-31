package com.ppolodev.iobuilders.moneytokenizer.users.infrastructure;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ppolodev.iobuilders.moneytokenizer.users.application.AccountDTO;
import com.ppolodev.iobuilders.moneytokenizer.users.application.AccountService;

@RestController
@RequestMapping("/moneyTokenizer")
public class AccountController {

	Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;

	@PostMapping(value = "/users")
	public ResponseEntity<AccountDTO> createAccount(@RequestParam final String name) {
		AccountDTO account = null;
		try {
			account = accountService.createAccount(name);
		} catch(Exception e) {
			logger.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(account, HttpStatus.CREATED);
	}

	@GetMapping(value = "/users/{name}")
	public ResponseEntity<AccountDTO> getAccountByName(@PathVariable final String name) {
		AccountDTO account = null;
		try {
			account = accountService.getAccountByName(name);
		} catch(Exception e) {
			logger.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(account, HttpStatus.CREATED);
	}

	@GetMapping(value = "/users/all")
	public ResponseEntity<List<AccountDTO>> getAllAccounts() {
		List<AccountDTO> accounts = new ArrayList<>();
		try {
			accounts = accountService.getAccounts();
		}  catch(Exception e) {
			logger.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}

}
