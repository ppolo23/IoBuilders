package com.ppolodev.iobuilders.moneytokenizer.adapter.in.web;

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

import com.ppolodev.iobuilders.moneytokenizer.application.port.in.DepositUseCase;
import com.ppolodev.iobuilders.moneytokenizer.application.port.in.GetAllUsersUseCase;
import com.ppolodev.iobuilders.moneytokenizer.application.port.in.GetUserByUsernameUseCase;
import com.ppolodev.iobuilders.moneytokenizer.application.port.in.RegisterUserUseCase;
import com.ppolodev.iobuilders.moneytokenizer.application.port.in.TransferUseCase;
import com.ppolodev.iobuilders.moneytokenizer.application.port.in.WithdrawUseCase;
import com.ppolodev.iobuilders.moneytokenizer.domain.UserDTO;

@RestController
@RequestMapping("/user")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private RegisterUserUseCase registerUserUseCase;
	
	@Autowired
	private GetAllUsersUseCase getAllUsersUseCase;
	
	@Autowired
	private GetUserByUsernameUseCase getUserByUsernameUseCase;
	
	@Autowired
	private DepositUseCase depositUseCase;
	
	@Autowired
	private WithdrawUseCase withdrawUseCase;
	
	@Autowired
	private TransferUseCase transferUseCase;
	
	
	@PostMapping()
	public void registerUser(@RequestParam String username, @RequestParam String password) {
		registerUserUseCase.registerUser(username, password);
	}
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		List<UserDTO> users = getAllUsersUseCase.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{username}")
	public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username){
		UserDTO user = getUserByUsernameUseCase.getUserByUsername(username);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PostMapping(value = "{username}/deposit/{amount}")
	public void deposit(@PathVariable String username, @PathVariable Double amount) {
		depositUseCase.deposit(username, amount);
	}
	
	@PostMapping(value = "{username}/withdraw/{amount}")
	public void withdraw(@PathVariable String username, @PathVariable Double amount) {
		withdrawUseCase.withdraw(username, amount);
	}
	
	@PostMapping(value = "{sender}/transfer/{amount}/{receiver}")
	public void transfer(@PathVariable String sender, @PathVariable Double amount, @PathVariable String receiver) {
		transferUseCase.transfer(sender, amount, receiver);
	}
}
