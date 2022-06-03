package com.pablojuice.framework.data;

import com.pablojuice.framework.data.repositories.BaseRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;

import static com.pablojuice.framework.util.SpringDataUtils.*;

@Configuration
@ComponentScan(
		basePackages = "com.pablojuice.framework.data.*"
)
@EnableJpaRepositories(
		basePackages = "com.pablojuice.framework.data.*",
		excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = BaseRepository.class)}
)
public class SpringConfiguration {
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		return generateEntityManagerFactory("com.pablojuice.framework.data.entities");
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
