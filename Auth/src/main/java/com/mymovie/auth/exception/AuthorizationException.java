package com.mymovie.auth.exception;

import java.text.MessageFormat;

public class AuthorizationException extends AuthApplicationException {


	public AuthorizationException(final String message) {
		super(message);
	}

	public static AuthorizationException NOT_AUTHORIZED() {
		return new AuthorizationException("User not authorized to perform the task.");
	}
}