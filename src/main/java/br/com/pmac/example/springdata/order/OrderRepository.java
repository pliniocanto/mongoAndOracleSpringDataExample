package br.com.pmac.example.springdata.order;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.pmac.example.springdata.customer.Customer.CustomerId;

public interface OrderRepository extends CrudRepository<Order, Long> {
	List<Order> findByCustomer(CustomerId id);
}
