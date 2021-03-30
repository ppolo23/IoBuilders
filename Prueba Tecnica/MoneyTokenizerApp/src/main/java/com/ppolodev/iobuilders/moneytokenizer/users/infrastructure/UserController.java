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

import com.ppolodev.iobuilders.moneytokenizer.users.application.UserDTO;
import com.ppolodev.iobuilders.moneytokenizer.users.application.UserService;

@RestController
@RequestMapping("/moneyTokenizer")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@PostMapping(value = "/users")
	public ResponseEntity<UserDTO> createUser(@RequestParam final String userName) {
		UserDTO user = null;
		try {
			user = userService.createUser(userName);
		} catch(Exception e) {
			logger.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@GetMapping(value = "/users/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable final int id) {
		UserDTO user = null;
		try {
			user = userService.getUserById(id);
		} catch(Exception e) {
			logger.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@GetMapping(value = "/users/all")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<UserDTO> users = new ArrayList<>();
		try {
			users = userService.getUsers();
		}  catch(Exception e) {
			logger.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

}
