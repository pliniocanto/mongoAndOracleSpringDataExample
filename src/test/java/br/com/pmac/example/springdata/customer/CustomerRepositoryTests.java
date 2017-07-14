package br.com.pmac.example.springdata.customer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(transactionManager = "customerTransactionManager")
public class CustomerRepositoryTests {

	@Autowired
	CustomerRepository repository;
	@Autowired
	@Qualifier("customerEntityManagerFactory")
	EntityManager em;

	@Test
	public void findsCustomerByLastname() {

		Optional<Customer> result = repository.findByLastname("Matthews");

		assertThat(result, is(not(Optional.empty())));
		assertThat(result.get().getFirstname(), is("Dave"));
	}
}
