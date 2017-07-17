package br.com.pmac.example.springdata;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import br.com.pmac.example.springdata.customer.Customer;
import br.com.pmac.example.springdata.customer.MongoCustomer;
import br.com.pmac.example.springdata.customer.MongoCustomerRepository;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class })
@EnableTransactionManagement
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	DataInitializer initializer;

	@Autowired
	MongoCustomerRepository mongoRepository;

	@PostConstruct
	public void init() {

		Iterable<MongoCustomer> docs = mongoRepository.findAll();

		for (MongoCustomer mongoCustomer : docs) {
			initializer.initializeCustomer(new Customer(mongoCustomer.getFirstname(), mongoCustomer.getLastname()));
		}

	}
}
