package com.pablojuice.framework.ui.contact;

import com.pablojuice.framework.base.BaseUITest;
import com.pablojuice.framework.data.DataProviderManager;
import com.pablojuice.framework.data.SpringConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
@ContextConfiguration(classes = SpringConfiguration.class)
public class ContactUsTest extends BaseUITest {

	private DataProviderManager provider;

	@BeforeClass
	public void setup2() {
		provider = new DataProviderManager(applicationContext);
	}

	@Override
	public void test() {
		Assert.assertTrue(true);
	}

	@DataProvider
	private Object[][] userCredentialsProvider() {
		return provider.provideData();
	}
}
