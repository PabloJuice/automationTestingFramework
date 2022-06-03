package com.pablojuice.framework.data.services;

import com.pablojuice.framework.data.entities.UserCredentials;
import com.pablojuice.framework.data.repositories.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCredentialsService {
	@Autowired
	protected final UserCredentialsRepository repository;

	protected UserCredentialsService(UserCredentialsRepository repository) {
		this.repository = repository;
	}

	public UserCredentials save(UserCredentials entity) {
		if (entity != null && repository.existsById(entity.getId())) {
			return repository.save(entity);
		}
		return null;
	}

	public UserCredentials get(UserCredentials entity) {
		if (entity != null && repository.existsById(entity.getId())) {
			return repository.getById(entity.getId());
		}
		return null;
	}

	public List<UserCredentials> getAll() {
		return repository.findAll();
	}

	public UserCredentials update(UserCredentials entity) {
		if (entity != null && repository.existsById(entity.getId())) {
			return repository.save(entity);
		}
		return null;
	}

	public void delete(UserCredentials entity) {
		if (entity != null) {
			repository.delete(entity);
		}
	}
}
