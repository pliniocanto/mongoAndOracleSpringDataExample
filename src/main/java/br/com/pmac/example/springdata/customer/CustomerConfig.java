package br.com.pmac.example.springdata.customer;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import oracle.jdbc.pool.OracleDataSource;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "customerEntityManagerFactory", transactionManagerRef = "customerTransactionManager")
class CustomerConfig {

	@Bean
	PlatformTransactionManager customerTransactionManager() throws Exception {
		return new JpaTransactionManager(customerEntityManagerFactory().getObject());
	}

	@Bean
	LocalContainerEntityManagerFactoryBean customerEntityManagerFactory() throws Exception {

		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

		factoryBean.setDataSource(customerDataSource());
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		factoryBean.setPackagesToScan(CustomerConfig.class.getPackage().getName());

		return factoryBean;
	}

	/*@Bean
	DataSource customerDataSource() {

		return new EmbeddedDatabaseBuilder().//
				setType(EmbeddedDatabaseType.HSQL).//
				setName("customers").//
				build();
	}*/

	@Bean
	DataSource customerDataSource() throws Exception {

		OracleDataSource dataSource = new OracleDataSource();
		dataSource.setUser("user");
		dataSource.setPassword("pwd");
		dataSource.setURL("jdbc:oracle:thin:@//0.0.0.0:1521/serv");
		//dataSource.setImplicitCachingEnabled(true);
		//dataSource.setFastConnectionFailoverEnabled(true);
		return dataSource;
	}
}
