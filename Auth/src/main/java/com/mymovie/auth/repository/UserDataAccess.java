package com.mymovie.auth.repository;

import com.mymovie.auth.model.UserData;
import com.mymovie.auth.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDataAccess extends JpaRepository<User, Long> {

	@Query("SELECT u.password FROM User u WHERE u.email = ?1")
	public Optional<String> getPassword(final String email);

	@Query("SELECT u FROM User u WHERE u.email = ?1")
	public Optional<User> findUserByEmail(final String email);

}

