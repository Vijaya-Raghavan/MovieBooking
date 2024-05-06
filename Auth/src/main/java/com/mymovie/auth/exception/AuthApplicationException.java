package com.mymovie.auth.exception;

public class AuthApplicationException extends Exception {

	public AuthApplicationException(final String message) {
		super(message);
	}

	public AuthApplicationException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public AuthApplicationException(final Throwable cause) {
		super(cause);
	}

	public static AuthApplicationException INTERNAL_ERROR() {
		return new AuthApplicationException("Internal Application Error");
	}
}
