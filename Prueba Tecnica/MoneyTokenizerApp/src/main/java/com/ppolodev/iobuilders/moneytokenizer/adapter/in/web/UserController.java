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

import com.ppolodev.iobuilders.moneytokenizer.application.port.in.BuyIobTokensUseCase;
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
	
	@Autowired
	private BuyIobTokensUseCase buyIobTokensUseCase;
	
	
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
	public ResponseEntity<String> deposit(@PathVariable String username, @PathVariable Double amount) {
		if(depositUseCase.deposit(username, amount)) {
			return new ResponseEntity<>("Deposit done successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Something went wrong", HttpStatus.METHOD_FAILURE);
		}
	}
	
	@PostMapping(value = "{username}/buyIobTokens/{amount}")
	public ResponseEntity<String> buyIobTokens(@PathVariable String username, @PathVariable Double amount) {
		if(buyIobTokensUseCase.buyIobTokens(username, amount)) {
			return new ResponseEntity<>(String.format("You bought %f IobTokens", amount), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Something went wrong", HttpStatus.METHOD_FAILURE);
		}
	}
	
	@PostMapping(value = "{sender}/transfer/{amount}/{receiver}")
	public ResponseEntity<String> transfer(@PathVariable String sender, @PathVariable Double amount, @PathVariable String receiver) {
		if(transferUseCase.transfer(sender, amount, receiver)) {
			return new ResponseEntity<>(String.format("%f IOB transfered to %s", amount, receiver), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Something went wrong", HttpStatus.METHOD_FAILURE);
		}
	}
	
	@PostMapping(value = "{username}/withdraw/{amount}")
	public ResponseEntity<String> withdraw(@PathVariable String username, @PathVariable Double amount) {
		if(withdrawUseCase.withdraw(username, amount)) {
			return new ResponseEntity<>(String.format("%f tokens sold successfully", amount), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Something went wrong", HttpStatus.METHOD_FAILURE);
		}
	}
}
