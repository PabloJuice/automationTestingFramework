package com.pablojuice.framework.data;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;

import static com.pablojuice.framework.util.SpringDataUtils.*;

@Configuration
@ComponentScan(basePackages = "com.pablojuice.framework.data.entities.*")
@EnableJpaRepositories(basePackages = "com.pablojuice.framework.data.*")
public class DataProviderManager {
	private

	public static void configure(ApplicationContext context) {
		//ApplicationContext context = new AnnotationConfigApplicationContext(Task6App.class);
		//StudentService studentService = context.getBean(StudentService.class);
	}

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		return generateEntityManagerFactory("com.pablojuice.task_6");
	}

	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
		return generateDataSource();
	}

	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return generateTransactionManager(entityManagerFactory);
	}
}


