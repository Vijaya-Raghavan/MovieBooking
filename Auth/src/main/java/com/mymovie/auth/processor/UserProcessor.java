package com.mymovie.auth.processor;

import com.mymovie.auth.exception.AuthApplicationException;
import com.mymovie.auth.exception.AuthenticationException;
import com.mymovie.auth.model.UserData;
import com.mymovie.auth.repository.UserDataAccess;
import com.mymovie.auth.repository.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserProcessor {

	private static final Logger LOG = LoggerFactory.getLogger(UserProcessor.class);

	@Autowired
	private UserDataAccess userDataAccess;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserData getUser(final String email) throws AuthApplicationException {
		try {
			UserData userData = null;
			if (StringUtils.isNotBlank(email)) {
				Optional<User> user = userDataAccess.findUserByEmail(email.trim());
				if (user.isPresent()) {
					userData = new UserData();
					User userInfo = user.get();
					userData.setId(userInfo.getId());
					userData.setEmail(userInfo.getEmail());
					userData.setFirstName(userInfo.getFirstName());
					userData.setLastName(userInfo.getLastName());
					userData.setGender(userInfo.getGender());
					userData.setPhone(userInfo.getPhone());
				} else {
					throw AuthenticationException.USER_NOT_FOUND(email);
				}
			} else {
				throw AuthenticationException.INVALID_USER_NAME();
			}
			return userData;
		} catch (AuthenticationException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw e;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw AuthApplicationException.INTERNAL_ERROR();
		}
	}
	public UserData createUser(final UserData userData) throws AuthApplicationException {
		try {
			User user = null;
			if (userData != null) {
				Optional<User> data = userDataAccess.findUserByEmail(userData.getEmail().trim());
				if (!data.isPresent()) {
					user = new User();
					user.setEmail(userData.getEmail());
					user.setFirstName(userData.getFirstName());
					user.setLastName(userData.getLastName());
					user.setGender(userData.getGender());
					user.setPhone(userData.getPhone());

					userDataAccess.save(user);
				} else {
					throw AuthenticationException.USER_ALREADY_REGISTERED(userData.getEmail());
				}
			} else {
				throw AuthenticationException.INVALID_USER_NAME();
			}
			return userData;
		} catch (AuthenticationException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw e;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw AuthApplicationException.INTERNAL_ERROR();
		}
	}

	public UserData updateUser(final UserData userData) throws AuthApplicationException {
		try {
			User user = null;
			if (userData != null) {
				Optional<User> data = userDataAccess.findUserByEmail(userData.getEmail().trim());
				if (data.isPresent()) {
					user = data.get();
					user.setId(userData.getId());
					user.setEmail(userData.getEmail());
					user.setFirstName(userData.getFirstName());
					user.setLastName(userData.getLastName());
					user.setGender(userData.getGender());
					user.setPhone(userData.getPhone());

					userDataAccess.save(user);
				} else {
					throw AuthenticationException.USER_NOT_FOUND(userData.getEmail());
				}
			} else {
				throw AuthenticationException.INVALID_USER_NAME();
			}
			return userData;
		} catch (AuthenticationException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw e;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw AuthApplicationException.INTERNAL_ERROR();
		}
	}

	public String getPassword(final String email) throws AuthApplicationException {
		try {
			String password = null;
			if (StringUtils.isNotBlank(email)) {
				Optional<String> passwordData = userDataAccess.getPassword(email.trim());
				if (passwordData.isPresent()) {
					password = passwordData.get();
				} else {
					throw AuthenticationException.USER_NOT_FOUND(email);
				}
			} else {
				throw AuthenticationException.INVALID_USER_NAME();
			}
			return password;
		} catch (AuthenticationException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw e;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw AuthApplicationException.INTERNAL_ERROR();
		}
	}

	public void validate(final String userName, final String password) throws AuthApplicationException {
		try {
			String actualPassword = getPassword(userName);
			if (!passwordEncoder.matches(password, actualPassword)) {
				throw AuthenticationException.INVALID_USER_CREDENTIALS();
			}
		} catch (AuthenticationException e) {
			LOG.error(e.getMessage(), e.getCause());
			throw e;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw AuthApplicationException.INTERNAL_ERROR();
		}
	}
}
