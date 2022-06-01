package com.pablojuice.framework.data.provider;

import com.pablojuice.framework.data.entities.BaseEntity;
import com.pablojuice.framework.data.services.BaseService;

public class DBProvider implements BaseProvider {

	private final BaseService service;

	public DBProvider(BaseService service) {
		this.service = service;
	}

	@Override
	public Object[][] provide() {
		return (Object[][]) service.getAll().stream().map(entity -> ((BaseEntity) entity).toData()).toArray();
	}
}
