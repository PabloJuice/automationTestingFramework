package com.pablojuice.framework.data.services;

import com.pablojuice.framework.data.entities.UserCredentials;
import com.pablojuice.framework.data.repositories.UserCredentialsRepository;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialsService extends BaseService<UserCredentialsRepository, UserCredentials> {

	public UserCredentialsService(UserCredentialsRepository repository) {
		super(repository);
	}
}
