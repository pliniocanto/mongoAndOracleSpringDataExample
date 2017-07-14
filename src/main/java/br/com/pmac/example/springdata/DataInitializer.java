package br.com.pmac.example.springdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.pmac.example.springdata.customer.Customer;
import br.com.pmac.example.springdata.customer.Customer.CustomerId;
import br.com.pmac.example.springdata.customer.CustomerRepository;
import br.com.pmac.example.springdata.order.Order;
import br.com.pmac.example.springdata.order.Order.LineItem;
import br.com.pmac.example.springdata.order.OrderRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DataInitializer {

	private final @NonNull OrderRepository orders;
	private final @NonNull CustomerRepository customers;

	@Transactional("customerTransactionManager")
	public CustomerId initializeCustomer() {
		return customers.save(new Customer("Dave", "Matthews")).getId();
	}

	@Transactional("orderTransactionManager")
	public Order initializeOrder(CustomerId customer) {

		Assert.notNull(customer, "Custoemr identifier must not be null!");

		Order order = new Order(customer);
		order.add(new LineItem("Lakewood Guitar"));

		return orders.save(order);
	}
}
