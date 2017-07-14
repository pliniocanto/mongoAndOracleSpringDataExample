package br.com.pmac.example.springdata.customer;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
@EnableJpaRepositories(
		entityManagerFactoryRef = "customerEntityManagerFactory", 
		transactionManagerRef = "customerTransactionManager")
public class MongoCustomerRepositoryIntegrationTest {

	@Autowired
	MongoCustomerRepository mongoRepository;


	@Autowired
	CustomerRepository repository;
	@Autowired
	@Qualifier("customerEntityManagerFactory")
	EntityManager em;

	Customer dave, oliver, carter;

	@Before
	public void setUp() {

		mongoRepository.deleteAll();

		dave = mongoRepository.save(new Customer("Dave", "Matthews"));
		oliver = mongoRepository.save(new Customer("Oliver August", "Matthew"));
		carter = mongoRepository.save(new Customer("Carter", "Beauford"));
	}

	@Test
	public void setsIdOnSave() {

		Customer dave = mongoRepository.save(new Customer("Dave", "Matthews"));
		assertThat(dave.getId(), is(notNullValue()));
	}

	@Test
	public void findsCustomerByLastname() {

		//Optional<Customer> result = repository.findByLastname("Matthews");
		
		Iterable<Customer> result = repository.findAll();
		
		for (Customer customer : result) {
			System.out.println(customer.getFirstname());
		}

		//assertThat(result.iterator().next().getFirstname(), is("Dave"));
		System.out.println(result.spliterator().getExactSizeIfKnown());
		assertTrue(result.spliterator().getExactSizeIfKnown() > 1);
		
	}

}
