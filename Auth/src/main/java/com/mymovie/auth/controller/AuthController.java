package com.mymovie.auth.controller;

import com.mymovie.auth.exception.AuthApplicationException;
import com.mymovie.auth.exception.AuthenticationException;
import com.mymovie.auth.model.UserData;
import com.mymovie.auth.processor.UserProcessor;
import com.mymovie.auth.validator.AuthValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Users")
public class AuthController {

	@Autowired
	private UserProcessor userProcessor;

	@Autowired
	private AuthValidator validator;

	@PostMapping
	public ResponseEntity<Object> registerUser(@RequestBody final UserData userData) {
		ResponseEntity<Object> response;
		try {
			userProcessor.createUser(userData);
			response = ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (AuthenticationException e) {
			response = ResponseEntity.badRequest().body(e.getMessage());
		} catch (AuthApplicationException e) {
			response = ResponseEntity.internalServerError().body(e.getMessage());
		}
		return response;
	}

	@PutMapping
	public ResponseEntity<Object> updateUser(@RequestHeader(HttpHeaders.AUTHORIZATION) final String auth, @RequestBody final UserData userData) {
		ResponseEntity<Object> response;
		try {
			String user = validator.validateUser(auth);
			userProcessor.updateUser(userData);
			response = ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} catch (AuthenticationException e) {
			response = ResponseEntity.badRequest().body(e.getMessage());
		} catch (AuthApplicationException e) {
			response = ResponseEntity.internalServerError().body(e.getMessage());
		}
		return response;
	}

}

