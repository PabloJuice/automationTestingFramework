package com.pablojuice.framework.data.provider;

import com.pablojuice.framework.data.entities.BaseEntity;
import com.pablojuice.framework.data.services.UserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;

public class DBProvider implements BaseProvider {

	@Autowired
	private final UserCredentialsService service;

	public DBProvider(UserCredentialsService service) {
		this.service = service;
	}

	@Override
	public Object[][] provide() {
		return (Object[][]) service.getAll().stream().map(entity -> ((BaseEntity) entity).toData()).toArray();
	}
}
