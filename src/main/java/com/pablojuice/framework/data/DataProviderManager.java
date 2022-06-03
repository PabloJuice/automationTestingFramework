package com.pablojuice.framework.data;

import com.pablojuice.framework.data.provider.BaseProvider;
import com.pablojuice.framework.data.provider.DBProvider;
import com.pablojuice.framework.data.services.UserCredentialsService;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.DataProvider;


public class DataProviderManager {

	private final BaseProvider provider;

	public DataProviderManager(ApplicationContext context) {
		this.provider = new DBProvider(context.getBean(UserCredentialsService.class));
	}

	@DataProvider
	public Object[][] provideData() {
		return provider.provide();
	}
}


