package com.pablojuice.framework.data.services;

import com.pablojuice.framework.data.entities.BaseEntity;
import com.pablojuice.framework.data.repositories.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class BaseService<R extends BaseRepository, E extends BaseEntity> {

	@Autowired
	private final R repository;

	protected BaseService(R repository) {
		this.repository = repository;
	}

	public E save(E entity) {
		if (entity != null && repository.existsById(new Object())) {
			return (E) repository.save(entity);
		}
		return null;
	}

	public E get(E entity) {
		if (entity != null && repository.existsById(new Object())) {
			return (E) repository.getById(entity.getId());
		}
		return null;
	}

	public List<E> getAll() {
		return repository.findAll();
	}

	public E update(E entity) {
		if (entity != null && repository.existsById(entity.getId())) {
			return (E) repository.save(entity);
		}
		return null;
	}

	public void delete(E entity) {
		if (entity != null) {
			repository.delete(entity);
		}
	}
}
