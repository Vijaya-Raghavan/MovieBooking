package com.mymovie.auth.validator;

import com.mymovie.auth.exception.AuthApplicationException;
import com.mymovie.auth.processor.UserProcessor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthValidator {

	@Autowired
	private UserProcessor userProcessor;

	public String validateUser(final String auth) throws AuthApplicationException {
		String pair=new String(Base64.decodeBase64(auth.substring("Basic ".length())));
		String userName=pair.split(":")[0];
		String password=pair.split(":")[1];
		userProcessor.validate(userName, password);
		return userName;
	}
}
