package br.com.pmac.example.springdata.customer;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(transactionManager = "customerTransactionManager")
@EnableJpaRepositories(entityManagerFactoryRef = "customerEntityManagerFactory", transactionManagerRef = "customerTransactionManager")
public class MongoCustomerRepositoryIntegrationTest {

	@Autowired
	MongoCustomerRepository mongoRepository;

	MongoCustomer dave, oliver, carter;

	@Autowired
	CustomerRepository repository;
	@Autowired
	@Qualifier("customerEntityManagerFactory")
	EntityManager em;

	@Before
	public void setUp() {

		mongoRepository.deleteAll();

		dave = mongoRepository.save(new MongoCustomer("Dave", "Matthews"));
		oliver = mongoRepository.save(new MongoCustomer("Oliver August", "Matthew"));
		carter = mongoRepository.save(new MongoCustomer("Carter", "Beauford"));
	}

	/*@Test
	public void setsIdOnSave() {
		MongoCustomer dave = mongoRepository.save(new MongoCustomer("Dave", "Matthews"));
		assertThat(dave.getId(), is(notNullValue()));
	}*/

	@Test
	public void mustExportCustomerToOracle() {
		Iterable<MongoCustomer> docs = mongoRepository.findAll();
		
		System.out.println(":::::::::::::::::" + docs.spliterator().getExactSizeIfKnown());

		for (MongoCustomer mongoCustomer : docs) {
			repository.save(parseMongoToOracle(mongoCustomer));
		}

	}

	public Customer parseMongoToOracle(MongoCustomer mongoCustomer) {
		return new Customer(mongoCustomer.getFirstname(), mongoCustomer.getLastname());
	}

}
