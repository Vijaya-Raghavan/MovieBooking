package com.mymovie.auth.exception;

import java.text.MessageFormat;

public class AuthenticationException extends AuthApplicationException {

	public AuthenticationException(final String message) {
		super(message);
	}

	public static AuthenticationException INVALID_USER_NAME() {
		return new AuthenticationException("Valid username / password is required");
	}

	public static AuthenticationException USER_NOT_FOUND(final String user) {
		return new AuthenticationException(MessageFormat.format("User {0} not found", user));
	}

	public static AuthenticationException USER_ALREADY_REGISTERED(final String user) {
		return new AuthenticationException(MessageFormat.format("User {0} already registered.", user));
	}

	public static AuthenticationException INVALID_USER_CREDENTIALS() {
		return new AuthenticationException("Invalid user credentials");
	}
}
