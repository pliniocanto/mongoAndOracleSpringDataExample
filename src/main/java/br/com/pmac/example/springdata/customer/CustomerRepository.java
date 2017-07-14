package br.com.pmac.example.springdata.customer;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	Optional<Customer> findByLastname(String lastname);
}
