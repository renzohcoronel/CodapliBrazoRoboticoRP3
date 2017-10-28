package org.codapli.utn.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.codapli.utn.controlador.ServoCodapli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class AppConfig {

	@Autowired
	PropertiesApp propertiesApp;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private LocalContainerEntityManagerFactoryBean entityManagerFactory;

	@Bean(name = "servoHombro")
	public ServoCodapli getServoHombro() {
		return new ServoCodapli("HOMBRO", Integer.valueOf(propertiesApp.getProperty("servoHombro")),
				Float.valueOf(propertiesApp.getProperty("homboAngMax")),
				Float.valueOf(propertiesApp.getProperty("homboAngMin")),
				Integer.valueOf(propertiesApp.getProperty("time")).intValue());
	}

	@Bean(name = "servoCodo")
	public ServoCodapli getServoCodo() {
		return new ServoCodapli("CODO", Integer.valueOf(propertiesApp.getProperty("servoCodo")),
				Float.valueOf(propertiesApp.getProperty("codoAngMax")),
				Float.valueOf(propertiesApp.getProperty("codoAngMin")),
				Integer.valueOf(propertiesApp.getProperty("time")).intValue());
	}

	@Bean(name = "servoBase")
	public ServoCodapli getServoBase() {
		return new ServoCodapli("BASE", Integer.valueOf(propertiesApp.getProperty("servoBase")),
				Float.valueOf(propertiesApp.getProperty("baseAngMax")),
				Float.valueOf(propertiesApp.getProperty("baseAngMin")),
				Integer.valueOf(propertiesApp.getProperty("time")).intValue());
	}

	@Bean(name = "servoPinza")
	public ServoCodapli getServoPinza() {
		return new ServoCodapli("PINZA", Integer.valueOf(propertiesApp.getProperty("servoPinza")),
				Float.valueOf(propertiesApp.getProperty("pinzaAngMax")),
				Float.valueOf(propertiesApp.getProperty("pinzaAngMin")),
				Integer.valueOf(propertiesApp.getProperty("time")).intValue());
	}

	// Setting DataBase in this case SQLite
	@Bean
	public DataSource dataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("org.sqlite.JDBC");
		dataSourceBuilder.url("jdbc:sqlite:mydb.db");
		return dataSourceBuilder.build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();

		entityManagerFactory.setDataSource(dataSource);

		// Classpath scanning of @Component, @Service, etc annotated class
		entityManagerFactory.setPackagesToScan("org.codapli.utn");

		// Vendor adapter
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactory.setJpaVendorAdapter(vendorAdapter);

		// Hibernate properties
		Properties additionalProperties = new Properties();
		additionalProperties.put("hibernate.dialect", "org.hibernate.dialect.SQLiteDialect");
		additionalProperties.put("hibernate.show_sql", "true");
		additionalProperties.put("hibernate.hbm2ddl.auto", "update");
		entityManagerFactory.setJpaProperties(additionalProperties);

		return entityManagerFactory;
	}

	/**
	 * Declare the transaction manager.
	 */
	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
		return transactionManager;
	}

	/**
	 * PersistenceExceptionTranslationPostProcessor is a bean post processor
	 * which adds an advisor to any bean annotated with Repository so that any
	 * platform-specific exceptions are caught and then rethrown as one Spring's
	 * unchecked data access exceptions (i.e. a subclass of
	 * DataAccessException).
	 */
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

}
