package br.com.pmac.example.springdata.order;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.pmac.example.springdata.customer.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(transactionManager = "orderTransactionManager")
public class OrderRepositoryTests {

	@Autowired
	OrderRepository orders;
	@Autowired
	CustomerRepository customers;

	@Test
	public void persistsAndFindsCustomer() {

		customers.findAll().forEach(customer -> {
			assertThat(orders.findByCustomer(customer.getId()), hasSize(1));
		});
	}
}
